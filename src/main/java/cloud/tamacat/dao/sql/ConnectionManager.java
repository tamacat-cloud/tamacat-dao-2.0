/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;

import cloud.tamacat.di.DI;
import cloud.tamacat.log.Log;
import cloud.tamacat.log.LogFactory;
import cloud.tamacat.dao.pool.ObjectActivateException;
import cloud.tamacat.dao.pool.PoolableObjectFactory;
import cloud.tamacat.dao.pool.impl.StackObjectPool;
import cloud.tamacat.util.ResourceNotFoundException;

public class ConnectionManager extends StackObjectPool<Connection> {

	static final Log LOG = LogFactory.getLog(ConnectionManager.class);
	
	private static final String XML = "db.xml";
	
    private static final HashMap<String, ConnectionManager> MANAGER = new HashMap<>();

    public synchronized static ConnectionManager getInstance(String name) {
        ConnectionManager cm = MANAGER.get(name);
        if (cm == null) {
            cm = new ConnectionManager(name);
            MANAGER.put(name, cm);
        }
        return cm;
    }
    
    public synchronized static Collection<ConnectionManager> getAllInstances() {
    	return MANAGER.values();
    }
    
    public synchronized static void closeAll() {
    	for (ConnectionManager cm : MANAGER.values()) {
    		cm.release();
    	}
    	MANAGER.clear();
    	
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while( drivers.hasMoreElements() ){
		    Driver driver = drivers.nextElement();
		    try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				LOG.warn(e);
			}
		}
    }

    private ConnectionManager(String name) {
        super(new ConnectionFactory(name));
    }
    
    static class ConnectionFactory implements PoolableObjectFactory<Connection> {
        private JdbcConfig config;

        public ConnectionFactory(String name) {
            config = DI.configure(XML).getBean(name, JdbcConfig.class);
            if (config == null) {
            	throw new ResourceNotFoundException(XML + " of key [" + name + "] is not found.");
            }
        }

        @Override
        public int getMaxPools() {
        	return config.getMaxPools();
        }
        
        @Override
        public int getInitPools() {
        	return config.getInitPools();
        }
        
        @Override
        public void activate(Connection object) throws ObjectActivateException {
            config.activate(object);
        }

        @Override
        public Connection create() {
            return config.getConnection();
        }

        @Override
        public void destroy(Connection object) {
            if (object != null) {
                try {
                    object.close();
                } catch (SQLException e) {
                }
            }
        }

        @Override
        public boolean validate(Connection object)
                throws ObjectActivateException {
            return object != null ? true : false;
        }
    }
}

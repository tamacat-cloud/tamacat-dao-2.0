/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.pool.impl;

import cloud.tamacat.dao.pool.ObjectActivateException;
import cloud.tamacat.dao.pool.PoolableObjectFactory;

public class ConnectionFactory implements PoolableObjectFactory<Connection> {

	int maxPools;
	int minPools;
	
	@Override
    public void activate(Connection object) {
        if (object == null) throw new ObjectActivateException();
    }

    @Override
    public Connection create() {
        return new Connection();
    }

    @Override
    public boolean validate(Connection object) {
        return object != null ? true : false;
    }

    @Override
    public void destroy(Connection object) {
    }

	@Override
	public int getMaxPools() {
		return maxPools;
	}
	
	public int getInitPools() {
		return minPools;
	}
	public void setMaxPools(int maxPools) {
		this.maxPools = maxPools;
	}
}

/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.sql;

import java.sql.Connection;

import cloud.tamacat.dao.pool.ObjectActivateException;

public interface JdbcConfig {

	Connection getConnection();

	String getUrl();

	String getDriverClass();

	void activate(Connection con) throws ObjectActivateException;

	/**
	 * Get a maximum number of pools.
	 * 
	 * @return maximum pooling size
	 * @since 1.3-20160325
	 */
	int getMaxPools();

	/**
	 * Get a number of initial pools.
	 * 
	 * @return initial pooling size
	 * @since 1.3-20160325
	 */
	int getInitPools();
}

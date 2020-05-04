module cloud.tamacat.dao {
	exports cloud.tamacat.dao;
	exports cloud.tamacat.dao.event;
	exports cloud.tamacat.dao.exception;
	exports cloud.tamacat.dao.meta;
	exports cloud.tamacat.dao.orm;
	exports cloud.tamacat.dao.util;
	exports cloud.tamacat.dao.sql;

	opens cloud.tamacat.dao.impl;
	opens cloud.tamacat.dao.pool;
	opens cloud.tamacat.dao.pool.impl;
	opens cloud.tamacat.dao.sql;

	opens cloud.tamacat.mock.sql;

	requires transitive cloud.tamacat.core;
	
	requires transitive java.json;
	requires transitive java.logging;
	requires transitive java.naming;
	requires transitive java.sql;
}
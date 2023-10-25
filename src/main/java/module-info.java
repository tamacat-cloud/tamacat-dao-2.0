open module tamacat.dao {
	
	exports cloud.tamacat.dao;
	exports cloud.tamacat.dao.event;
	exports cloud.tamacat.dao.exception;
	exports cloud.tamacat.dao.meta;
	exports cloud.tamacat.dao.orm;
	exports cloud.tamacat.dao.util;
	exports cloud.tamacat.dao.sql;
	
	//for unit test
	exports cloud.tamacat.dao.test;
	
	requires java.xml;
	requires jakarta.xml.bind;
	
	requires transitive tamacat.core;
	requires transitive com.google.gson;
	requires transitive org.slf4j;
	requires transitive java.logging;
	requires transitive java.naming;
	requires transitive java.sql;
}
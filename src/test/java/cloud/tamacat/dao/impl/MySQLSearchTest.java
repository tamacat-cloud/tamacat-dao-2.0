/*
 * Copyright (c) 2007 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class MySQLSearchTest {

	@Test
	public void testMySQLSearch() {
		new MySQLSearch();
		MySQLSearch.MySQLValueConvertFilter filter = new MySQLSearch.MySQLValueConvertFilter();
		
		assertEquals("te''st", filter.convertValue("te'st"));
		assertEquals("te\\\\st", filter.convertValue("te\\st"));
	}

}

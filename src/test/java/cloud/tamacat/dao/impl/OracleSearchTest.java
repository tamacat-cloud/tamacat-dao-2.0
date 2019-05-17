/*
 * Copyright (c) 2007 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import cloud.tamacat.dao.impl.OracleSearch;

public class OracleSearchTest {

	@Test
	public void testOracleSearch() {
		new OracleSearch();
		OracleSearch.OracleValueConvertFilter filter = new OracleSearch.OracleValueConvertFilter();
		
		assertEquals("te''st", filter.convertValue("te'st"));
	}
}

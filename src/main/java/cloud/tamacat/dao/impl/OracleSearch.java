/*
 * Copyright (c) 2011 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.impl;

import cloud.tamacat.dao.Search;

public class OracleSearch extends Search {
	
    static class OracleValueConvertFilter implements Search.ValueConvertFilter {
        public String convertValue(String value) {
            return value.replace("'", "''");
        }
    }

    public OracleSearch() {
        super(new OracleValueConvertFilter());
    }
}

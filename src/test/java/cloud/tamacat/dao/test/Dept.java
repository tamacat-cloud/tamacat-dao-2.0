/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.test;

import cloud.tamacat.dao.meta.Table;
import cloud.tamacat.dao.meta.Tables;
import cloud.tamacat.dao.meta.Column;
import cloud.tamacat.dao.meta.Columns;
import cloud.tamacat.dao.orm.MapBasedORMappingBean;

public class Dept extends MapBasedORMappingBean<Dept> {
	private static final long serialVersionUID = 1L;
	
    public static final Column DEPT_ID = Columns.create("dept_id").primaryKey(true);
    public static final Column DEPT_NAME = Columns.create("dept_name");
    public static final Table TABLE = Tables.create("dept").registerColumn(DEPT_ID, DEPT_NAME);
    
	public Dept() {
		defaultTableName = TABLE.getTableNameWithSchema();
	}
}

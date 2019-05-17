/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.test;

import cloud.tamacat.dao.meta.Column;
import cloud.tamacat.dao.meta.Columns;
import cloud.tamacat.dao.meta.DataType;
import cloud.tamacat.dao.meta.Table;
import cloud.tamacat.dao.meta.Tables;
import cloud.tamacat.dao.orm.MapBasedORMappingBean;

public class Data extends MapBasedORMappingBean<Data> {
	private static final long serialVersionUID = 1L;

	public static final Column ID = Columns.create("id").primaryKey(true).autoGenerateId(true);
	public static final Column NAME = Columns.create("name");
	public static final Column NUM1 = Columns.create("num1").type(DataType.NUMERIC);
	public static final Column NUM2 = Columns.create("num2").type(DataType.FLOAT);
	public static final Column UPDATE_DATE = Columns.create("update_date").type(DataType.TIME);
	
	public static final Table TABLE = Tables.create("data")
		.registerColumn(ID, NAME, NUM1, NUM2, UPDATE_DATE);
	
	public Data() {
		defaultTableName = TABLE.getTableNameWithSchema();
	}
}

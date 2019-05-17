/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.test;

import cloud.tamacat.dao.meta.Table;
import cloud.tamacat.dao.meta.Tables;
import cloud.tamacat.dao.meta.Column;
import cloud.tamacat.dao.meta.Columns;
import cloud.tamacat.dao.meta.DataType;
import cloud.tamacat.dao.orm.MapBasedORMappingBean;

public class UserStat extends MapBasedORMappingBean<UserStat> {

	private static final long serialVersionUID = 1L;
	
	public static final Column USER_ID = Columns.create("user_id").primaryKey(true);
	public static final Column TOTAL_SCORE = Columns.create("total_score").type(DataType.FUNCTION).functionName("sum(score)");
	
	public static final Table TABLE = Tables.create("user_stat")
		.registerColumn(USER_ID, TOTAL_SCORE);
	
	public UserStat() {
		defaultTableName = TABLE.getTableNameWithSchema();
	}
}

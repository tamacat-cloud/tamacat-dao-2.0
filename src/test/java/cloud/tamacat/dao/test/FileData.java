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

public class FileData extends MapBasedORMappingBean<FileData> {

	private static final long serialVersionUID = 1L;

	public static final Column FILE_ID = Columns.create("file_id").primaryKey(true).autoGenerateId(true);
	public static final Column FILE_NAME = Columns.create("file_name");
	public static final Column SIZE = Columns.create("size").type(DataType.NUMERIC);
	public static final Column CONTENT_TYPE = Columns.create("content_type");
	public static final Column DATA = Columns.create("data").type(DataType.OBJECT);
	public static final Column UPDATE_DATE = Columns.create("update_date").type(DataType.DATE).autoTimestamp(true);

	public static final Table TABLE = Tables.create("file")
		.registerColumn(FILE_ID, FILE_NAME, SIZE, CONTENT_TYPE, DATA, UPDATE_DATE);
}

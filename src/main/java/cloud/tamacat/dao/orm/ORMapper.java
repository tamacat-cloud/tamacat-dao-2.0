/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import cloud.tamacat.dao.exception.DaoException;
import cloud.tamacat.dao.meta.Column;
import cloud.tamacat.dao.meta.DataType;
import cloud.tamacat.dao.util.MappingUtils;
import cloud.tamacat.util.ClassUtils;

public class ORMapper<T extends ORMappingSupport<T>> {

	private Class<T> type;

	public ORMapper() {
	}

	public void setPrototype(Class<T> type) {
		this.type = type;
	}

	public T getMappedObject() {
		return ClassUtils.newInstance(type);
	}

	public T mapping(Collection<Column> columns, ResultSet rs) {
		T data = getMappedObject();
		try {
			int index = 1;
			for (Column column : columns) {
				DataType type = column.getType();
				data.mapping(column, MappingUtils.mapping(type, rs, index));
				index++;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return data;
	}
}

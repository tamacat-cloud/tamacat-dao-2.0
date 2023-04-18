/*
 * Copyright (c) 2023 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.impl;

import cloud.tamacat.dao.Search.Conditions;

public enum MySQLCondition implements Conditions {
	
	REGEXP(" regexp ", "#{value1}"),
	RLIKE(" rlike ", "#{value1}");

	private final String replaceHolder;
	private final String condition;

	private MySQLCondition(String condition, String replaceHolder) {
		this.condition = condition;
		this.replaceHolder = replaceHolder;
	}

	public String getReplaceHolder() {
		return replaceHolder;
	}

	public String getCondition() {
		return condition;
	}
}

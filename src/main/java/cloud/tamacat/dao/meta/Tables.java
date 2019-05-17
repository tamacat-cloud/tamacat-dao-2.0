/*
 * Copyright (c) 2015 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.meta;

public class Tables {

	public static Table create(String... name) {
		return new DefaultTable(name);
	}

}

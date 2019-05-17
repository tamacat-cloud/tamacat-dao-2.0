/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.test;

import cloud.tamacat.dao.Condition;
import cloud.tamacat.dao.DaoFactory;
import cloud.tamacat.dao.Search;
import cloud.tamacat.dao.Sort;

public class User_test {

	public static void main(String[] args) {
		UserDao dao = DaoFactory.create(UserDao.class);
		try {
			Search search = dao.createSearch();
			search.and(User.AGE, Condition.EQUAL, "';select * from dual --'");
			Sort sort = dao.createSort();
			dao.searchList(search, sort);
		} finally {
			dao.release();
		}
	}
}

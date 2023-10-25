/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.test;

import org.junit.Test;
import cloud.tamacat.dao.DaoFactory;
import cloud.tamacat.dao.Query;
import junit.framework.TestCase;

public class TestDaoTest extends TestCase {

	@Test
	public void test() {
		TestDao dao = DaoFactory.create(TestDao.class);
		assertNotNull(dao);
		Query<User> query = dao.createQuery().select(User.TABLE.columns());
		dao.search(query);
		assertEquals(
			"SELECT users.user_id,users.password,users.dept_id,users.update_date,users.age FROM users",
			dao.getExecutedQuery().get(0)
		);
		dao.release();
	}

}

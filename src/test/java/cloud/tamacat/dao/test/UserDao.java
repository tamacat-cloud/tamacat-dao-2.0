/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.test;

import java.util.Collection;

import cloud.tamacat.dao.Condition;
import cloud.tamacat.dao.DaoAdapter;
import cloud.tamacat.dao.Query;
import cloud.tamacat.dao.Search;
import cloud.tamacat.dao.Sort;

public class UserDao extends DaoAdapter<User> {

    public UserDao() {}

    public User search(User data) {
        Query<User> query = createQuery()
            .select(User.TABLE.getColumns())
            .where(param(User.USER_ID, Condition.EQUAL, data.getValue(User.USER_ID)));
        return super.search(query);
    }

    public Collection<User> searchList(Search search, Sort sort) {
        Query<User> query = createQuery()
            .select(User.TABLE.columns()).and(search, sort);
        return super.searchList(query, search.getStart(), search.getMax());
    }

    @Override
    protected String getInsertSQL(User data) {
        Query<User> query = createQuery()
        	.addUpdateColumns(User.TABLE.columns());
        return query.getInsertSQL(data);
    }

    @Override
    protected String getUpdateSQL(User data) {
        Query<User> query = createQuery()
        	.addUpdateColumns(User.TABLE.columns())
        	.where(param(User.USER_ID, Condition.EQUAL, data.val(User.USER_ID))
        );
        return query.getUpdateSQL(data);
    }

    @Override
    protected String getDeleteSQL(User data) {
        Query<User> query = createQuery()
        	.addUpdateColumn(User.USER_ID);
        return query.getDeleteSQL(data);
    }
    
    public int createTable() {
    	return executeUpdate(
    		"CREATE TABLE users (user_id varchar(32),"
    		+ "password varchar(20), dept_id varchar(32))");
    }
    
    public int dropTable() {
    	return executeUpdate("DROP TABLE users");
    }
}

/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.test;

import java.util.ArrayList;

import cloud.tamacat.dao.orm.MapBasedORMappingBean;

public class UserList extends ArrayList<MapBasedORMappingBean<User>> {

    private static final long serialVersionUID = 1L;

    public UserList() {
    }
}

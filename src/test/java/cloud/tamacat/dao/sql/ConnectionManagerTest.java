/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.sql;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import cloud.tamacat.log.Log;
import cloud.tamacat.log.LogFactory;

public class ConnectionManagerTest {

    static Log LOG = LogFactory.getLog(ConnectionManagerTest.class);
    ConnectionManager cm;

    @Before
    public void setUp() throws Exception {
        cm = ConnectionManager.getInstance("default");
    }

    @After
    public void tearDown() throws Exception {
        cm.release();
    }

    @Test
    public void testGetInstance() {
        Connection con = cm.getObject();
        LOG.debug(con.toString());
        cm.free(con);
        
        Connection c2 = cm.getObject();
        LOG.debug(c2.toString());
        cm.free(c2);
    }

}

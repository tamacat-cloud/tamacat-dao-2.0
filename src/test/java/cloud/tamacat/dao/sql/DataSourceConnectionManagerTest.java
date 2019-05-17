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

public class DataSourceConnectionManagerTest {

    static Log LOG = LogFactory.getLog(DataSourceConnectionManagerTest.class);
    ConnectionManager cm;

    @Before
    public void setUp() throws Exception {
        //DataSource ds = new MockDataSourceImpl();
        //MockDataSourceRegister.getInstance().registDataSource("jdbc/test", ds);
        //cm = ConnectionManager.getInstance("ds");
    }

    @After
    public void tearDown() throws Exception {
        if (cm != null) cm.release();
    }

    @Test
    public void testGetInstance() {
        if (cm != null) {
            Connection con = cm.getObject();
            LOG.info(con.toString());
            cm.free(con);
        }
    }

}

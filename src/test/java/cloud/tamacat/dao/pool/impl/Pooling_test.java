/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.pool.impl;

import cloud.tamacat.dao.pool.impl.StackObjectPool;

public class Pooling_test {

    public static void main(String[] args) {
        StackObjectPool<Connection> pool
            = new StackObjectPool<Connection>(new ConnectionFactory());

        Connection con = pool.getObject();
        Connection con2 = pool.getObject();

        System.out.println(pool.getNumberOfActiveObjects());
        pool.free(con);
        pool.free(con2);
        System.out.println(pool.getNumberOfActiveObjects());

        System.out.println(pool.getNumberOfPooledObjects());

        pool.release();
    }

}

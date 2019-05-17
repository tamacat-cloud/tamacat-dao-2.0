/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.pool;

public interface ObjectPool<T> {

    T getObject();

    void free(T object);

    void release();

    void setMaxPoolObject(int max);

    int getNumberOfMaxPoolObjects();

    int getNumberOfPooledObjects();

    int getNumberOfActiveObjects();
}

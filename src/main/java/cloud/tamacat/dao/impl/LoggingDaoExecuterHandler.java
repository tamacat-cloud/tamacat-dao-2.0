/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.impl;

import cloud.tamacat.dao.event.DaoEvent;
import cloud.tamacat.dao.event.DaoExecuteHandler;
import cloud.tamacat.log.LogFactory;

public class LoggingDaoExecuterHandler implements DaoExecuteHandler {

	public LoggingDaoExecuterHandler() {}
	
	@Override
	public void handleAfterExecuteQuery(DaoEvent event) {
		//none.
	}

	@Override
	public int handleAfterExecuteUpdate(DaoEvent event) {
		return event.getResult();
	}

	@Override
	public void handleBeforeExecuteQuery(DaoEvent event) {
		try {
			LogFactory.getLog(event.getCallerDao()).debug(event.getQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleBeforeExecuteUpdate(DaoEvent event) {
		try {
			LogFactory.getLog(event.getCallerDao()).debug(event.getQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

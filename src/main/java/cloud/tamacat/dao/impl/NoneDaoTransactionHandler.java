/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.impl;

import cloud.tamacat.dao.event.DaoEvent;
import cloud.tamacat.dao.event.DaoTransactionHandler;

public class NoneDaoTransactionHandler implements DaoTransactionHandler {

	@Override
	public void handleAfterCommit(DaoEvent event) {
	}

	@Override
	public void handleAfterRollback(DaoEvent event) {
	}

	@Override
	public void handleBeforeCommit(DaoEvent event) {
	}

	@Override
	public void handleBeforeRollback(DaoEvent event) {
	}

	@Override
	public void handleException(DaoEvent event, Throwable cause) {
	}

	@Override
	public void handleTransantionEnd(DaoEvent event) {
	}

	@Override
	public void handleTransantionStart(DaoEvent event) {
	}

	@Override
	public void handleRelease(DaoEvent event) {
	}
}

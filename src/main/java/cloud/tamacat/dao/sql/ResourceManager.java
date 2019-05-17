/*
 * Copyright (c) 2008 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.sql;

import java.util.Stack;
import cloud.tamacat.log.Log;
import cloud.tamacat.log.LogFactory;

public class ResourceManager {

	static final Log LOG = LogFactory.getLog(ResourceManager.class);
	
	private ThreadLocal<Stack<LifecycleSupport>> manager = new ThreadLocal<>();
	
	private static final ResourceManager SELF = new ResourceManager();
	private ResourceManager() {}
	
	private synchronized Stack<LifecycleSupport> getThreadObject() {
		Stack<LifecycleSupport> targets = manager.get();
		if (targets == null) {
			targets = new Stack<LifecycleSupport>();
			manager.set(targets);
		}
		return targets;
	}
	
	public static void set(LifecycleSupport target) {
		if (target != null) {
			SELF.getThreadObject().add(target);
			LOG.trace("set: " + target);
		}
	}
	
	public static void release() {
		Stack<LifecycleSupport> targets = SELF.getThreadObject();
		try {
			while (targets.size() > 0) {
				LifecycleSupport target = targets.pop();
				if (target != null) {
					target.stop();
					LOG.trace("release: " + target.toString());
				}
			}
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			if (SELF.manager != null) {
				SELF.manager.remove();
			}
		}
		LOG.trace("release.");
	}
}

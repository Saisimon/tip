package net.saisimon.main.concurrent;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock extends ReentrantLock {

	private static final long serialVersionUID = 1L;
	
	public String getOwnerName() {
		if (this.getOwner() == null) {
			return "None";
		} else {
			return this.getOwner().getName();
		}
	}
	
	public Collection<Thread> getThreads() {
		return this.getQueuedThreads();
	}
	
}

package net.saisimon.main.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class MyLockTask implements Runnable {
	
	private Lock lock;
	private String name;
	
	public MyLockTask(Lock lock, String name) {
		super();
		this.lock = lock;
		this.name = name;
	}

	@Override
	public void run() {
		lock.lock();
		System.out.printf("Task: %s: Take the lock\n",name);
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.printf("Task: %s: Free the lock\n",name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}

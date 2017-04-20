package net.saisimon.main.concurrent;

import java.util.concurrent.locks.Lock;

public class LockTask implements Runnable {
	
	private Lock lock;
	
	public LockTask(Lock lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			lock.lock();
			System.out.printf("%s: Get the Lock.\n", Thread.currentThread().getName());
			try {
				Thread.sleep(500);
				System.out.printf("%s: Free the Lock.\n", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

}

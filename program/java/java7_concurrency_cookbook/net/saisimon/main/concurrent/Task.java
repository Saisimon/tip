package net.saisimon.main.concurrent;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
	
	private long milliseconds;
	
	public Task(long milliseconds) {
		super();
		this.milliseconds = milliseconds;
	}

	@Override
	public void run() {
		System.out.printf("%s: Begin\n", Thread.currentThread().getName());
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: End\n", Thread.currentThread().getName());
	}

}

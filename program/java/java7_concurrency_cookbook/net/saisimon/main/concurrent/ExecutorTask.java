package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ExecutorTask implements Runnable {
	
	private Date initDate;
	private String name;
	
	public ExecutorTask(String name) {
		this.name = name;
		this.initDate = new Date();
	}

	@Override
	public void run() {
		System.out.printf("%s : Task %s Create on %s\n", Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s : Tash %s Start on %s\n", Thread.currentThread().getName(), name, new Date());
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %dseconds\n", Thread.currentThread().getName(), name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s : Tash %s Finish on %s\n", Thread.currentThread().getName(), name, new Date());
	}

}

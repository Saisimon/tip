package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {
	
	// 使用本地线程变量
	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {

		@Override
		protected Date initialValue() {
			return new Date();
		}
		
	};

	@Override
	public void run() {
		System.out.printf("Start Safe Thread : %s : %s\n", Thread.currentThread().getId(), startDate.get());
		try {
			TimeUnit.SECONDS.sleep((long) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Safe Thread Finished : %s : %s\n", Thread.currentThread().getId(), startDate.get());
	}

}

package net.saisimon.main.concurrent;

import java.util.concurrent.Semaphore;

public class PrintQueueSemaphore {
	
	// 信号量
	private Semaphore semaphore;
	
	public PrintQueueSemaphore() {
		// 单一信号
		semaphore = new Semaphore(1);
	}
	
	public void printJob(Object document) {
		try {
			semaphore.acquire();
			long duration = (long)(Math.random() * 10000);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",Thread.currentThread().getName(), duration / 1000);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}

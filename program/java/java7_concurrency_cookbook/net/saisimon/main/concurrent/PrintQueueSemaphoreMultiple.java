package net.saisimon.main.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueueSemaphoreMultiple {
	
	// 打印机状态数组
	private boolean[] freePrinters;
	private Lock lockPrinters;
	private Semaphore semaphore;
	
	public PrintQueueSemaphoreMultiple() {
		// 多个信号
		semaphore = new Semaphore(3);
		freePrinters = new boolean[3];
		for (int i = 0; i < freePrinters.length; i++) {
			freePrinters[i] = true;
		}
		lockPrinters = new ReentrantLock();
	}
	
	public void printJob(Object document) {
		try {
			// 获得 semaphore 的访问
			semaphore.acquire();
			// 获取空闲打印机
			int assignedPrinter = getPrinter();
			// 模拟打印
			long duration = (long)(Math.random() * 10);
			System.out.printf("%s: PrintQueue: Printing a Job in Printer%d during %d seconds\n",
					Thread.currentThread().getName(), assignedPrinter, duration);
			TimeUnit.SECONDS.sleep(duration);
			// 设置打印机空闲
			freePrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放 semaphore
			semaphore.release();
		}
	}

	private int getPrinter() {
		int ret = -1;
		// 获取锁
		lockPrinters.lock();
		try {
			for (int i = 0; i < freePrinters.length; i++) {
				if (freePrinters[i]) {
					ret = i;
					// 设置打印机繁忙
					freePrinters[i] = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			lockPrinters.unlock();
		}
		return ret;
	}
}

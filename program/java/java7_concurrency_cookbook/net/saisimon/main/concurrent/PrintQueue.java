package net.saisimon.main.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	
	private final Lock queueLock = new ReentrantLock();
	
	public void printJob(Object document) {
		queueLock.lock();
		try {
			long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + " PrintQueue : Printing a job during " + (duration / 1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}
	
}

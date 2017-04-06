package net.saisimon.main.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 读/写锁在它们的构造器中也有公平参数
public class PrintQueue {
	
	// 默认为非公平模式，随机选择线程
//	private final Lock queueLock = new ReentrantLock();
	
	// 开启公平模式，选择等待时间最长的线程
	private final Lock queueLock = new ReentrantLock(true);
	
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
		
		queueLock.lock();
		try {
			long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + " PrintQueue : Printing another job during " + (duration / 1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}
	
}

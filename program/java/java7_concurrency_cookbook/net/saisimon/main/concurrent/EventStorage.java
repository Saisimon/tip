package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {
	
	private int maxSize;
	private List<Date> storage;
	
	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<>();
	}
	
	public synchronized void set() {
		// 当达到最大值时，该线程等待
		while (storage.size() == maxSize) {
			try {
				// 如果在synchronized代码块外部调用wait()方法，JVM会抛出IllegalMonitorStateException异常
				// 当线程调用wait()方法，JVM让这个线程睡眠，并且释放控制 synchronized代码块的对象
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		((LinkedList<Date>)storage).offer(new Date());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Set : %d\n", storage.size());
		// 为了唤醒线程，你必 须在由相同对象保护的synchronized代码块中调用notify()或notifyAll()方法
		notifyAll();
	}
	
	public synchronized void get() {
		// 当什么都没有时，该线程等待
		while (storage.size() == 0) {
			try {
				// 如果在synchronized代码块外部调用wait()方法，JVM会抛出IllegalMonitorStateException异常
				// 当线程调用wait()方法，JVM让这个线程睡眠，并且释放控制 synchronized代码块的对象
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Get: %d: %s\n",storage.size(), ((LinkedList<Date>)storage).poll());
		// 为了唤醒线程，你必 须在由相同对象保护的synchronized代码块中调用notify()或notifyAll()方法
		notifyAll();
	}
	
}

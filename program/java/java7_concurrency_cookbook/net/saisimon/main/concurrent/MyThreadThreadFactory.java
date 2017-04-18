package net.saisimon.main.concurrent;

import java.util.concurrent.ThreadFactory;

public class MyThreadThreadFactory implements ThreadFactory {
	
	private int counter;
	private String prefix;
	
	public MyThreadThreadFactory(String prefix) {
		this.prefix = prefix;
		counter = 1;
	}

	@Override
	public Thread newThread(Runnable r) {
		MyThread thread = new MyThread(r, prefix + "-" + counter);
		counter++;
		return thread;
	}
	
}

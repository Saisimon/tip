package net.saisimon.main.concurrent;

import java.util.concurrent.ConcurrentLinkedDeque;

public class AddTask implements Runnable {
	
	private ConcurrentLinkedDeque<String> list;
	
	public AddTask(ConcurrentLinkedDeque<String> list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 10000; i++) {
			list.add(name + " : Element " + i);
		}
	}

}

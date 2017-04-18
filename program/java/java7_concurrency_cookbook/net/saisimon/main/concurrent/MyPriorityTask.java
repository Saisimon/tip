package net.saisimon.main.concurrent;

import java.util.concurrent.TimeUnit;

public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {
	
	private int priority;
	private String name;
	
	public MyPriorityTask(int priority, String name) {
		this.priority = priority;
		this.name = name;
	}
	
	public int getPriority() {
		return priority;
	}

	@Override
	public void run() {
		System.out.printf("MyPriorityTask: %s Priority : %d\n", name, priority);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 实现 Comparable 接口
	@Override
	public int compareTo(MyPriorityTask o) {
		if (this.getPriority() < o.getPriority()) {
			return 1;
		} else if (this.getPriority() > o.getPriority()) {
			return -1;
		} else {
			return 0;
		}
	}

}

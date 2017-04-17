package net.saisimon.main.concurrent;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements Runnable {
	
	public TaskLocalRandom() {
		// 返回当前线程的ThreadLocalRandom对象
		ThreadLocalRandom.current();
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
		}
	}

}

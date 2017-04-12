package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class DelayedTask implements Runnable {
	
	private int id;
	private DelayQueue<DelayedEvent> queue;

	public DelayedTask(int id, DelayQueue<DelayedEvent> queue) {
		super();
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime() + id * 1000);
		System.out.printf("Thread %s: %s\n", id, delay);
		for (int i = 0; i < 100; i++) {
			DelayedEvent event = new DelayedEvent(delay);
			queue.add(event);
		}
	}

}

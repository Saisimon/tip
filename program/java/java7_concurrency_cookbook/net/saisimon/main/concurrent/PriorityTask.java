package net.saisimon.main.concurrent;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityTask implements Runnable {
	
	private int id;
	private PriorityBlockingQueue<PriorityEvent> queue;
	
	public PriorityTask(int id, PriorityBlockingQueue<PriorityEvent> queue) {
		super();
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			PriorityEvent event = new PriorityEvent(id, i);
			queue.add(event);
		}
	}

}

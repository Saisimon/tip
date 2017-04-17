package net.saisimon.main.concurrent;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Incrementer implements Runnable {
	
	private AtomicIntegerArray vector;
	
	public Incrementer(AtomicIntegerArray vector) {
		super();
		this.vector = vector;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			vector.getAndIncrement(i);
		}
	}

}

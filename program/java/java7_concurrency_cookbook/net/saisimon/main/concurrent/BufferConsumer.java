package net.saisimon.main.concurrent;

import java.util.Random;

public class BufferConsumer implements Runnable {
	
	private Buffer buffer;
	
	public BufferConsumer(Buffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (buffer.hasPendingLines()) {
			String line = buffer.get();
			processLine(line);
		}
	}

	private void processLine(String line) {
		try {
			Random rand = new Random();
			Thread.sleep(rand.nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

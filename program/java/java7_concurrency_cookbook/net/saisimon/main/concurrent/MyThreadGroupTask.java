package net.saisimon.main.concurrent;

import java.util.Random;

public class MyThreadGroupTask implements Runnable {

	@Override
	public void run() {
		int result = 0;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			// 可能产生除零异常
			result = 1000 / (int)(random.nextDouble() * 1000);
			System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%s Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}
	}

}

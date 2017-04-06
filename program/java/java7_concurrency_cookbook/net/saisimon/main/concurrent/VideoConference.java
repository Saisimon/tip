package net.saisimon.main.concurrent;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {
	
	private CountDownLatch countDownLatch;
	
	public VideoConference(int number) {
		countDownLatch = new CountDownLatch(number);
	}
	
	public void arrive(String name) {
		System.out.printf("%s has arrived.\n", name);
		countDownLatch.countDown();
		System.out.printf("VideoConference: Waiting for %d participants.\n", countDownLatch.getCount());
	}

	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n", countDownLatch.getCount());
		try {
			// 当计数器减至 0 时，唤醒所有线程，对于 CountDownLatch 的任何操作都会无效
			countDownLatch.await();
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

package net.saisimon.main.concurrent;

import java.util.concurrent.TimeUnit;

public class MyThreadTask implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

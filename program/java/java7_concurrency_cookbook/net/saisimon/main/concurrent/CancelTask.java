package net.saisimon.main.concurrent;

import java.util.concurrent.Callable;

public class CancelTask implements Callable<String> {
	
	@Override
	public String call() throws Exception {
		while (true) {
			System.out.printf("Task : Test\n");
			Thread.sleep(100);
		}
	}

}

package net.saisimon.main.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ExecutorAllTask implements Callable<ExecutorResult> {
	
	private String name;
	
	public ExecutorAllTask(String name) {
		super();
		this.name = name;
	}

	@Override
	public ExecutorResult call() throws Exception {
		System.out.printf("%s: Staring\n", name);
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int value = 0;
		for (int i = 0; i < 5; i++) {
			value += (int) (Math.random() * 100);
		}
		ExecutorResult result = new ExecutorResult();
		result.setName(name);
		result.setValue(value);
		System.out.println(this.name + ": Ends");
		return result;
	}

}

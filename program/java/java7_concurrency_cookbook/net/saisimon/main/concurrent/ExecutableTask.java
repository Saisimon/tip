package net.saisimon.main.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ExecutableTask implements Callable<String> {
	
	private String name;
	
	public ExecutableTask(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String call() throws Exception {
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Hello World, I`m " + name;
	}

	public String getName() {
		return name;
	}
	
}

package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.concurrent.Callable;

public class ScheduledTask implements Callable<String> {
	
	private String name;
	
	public ScheduledTask(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.printf("%s Start at : %s\n", name, new Date());
		return "Hello World";
	}

}

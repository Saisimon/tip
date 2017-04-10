package net.saisimon.main.concurrent;

import java.util.Date;

public class ScheduledRunnable implements Runnable {
	
	private String name;
	
	public ScheduledRunnable(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s Start at : %s\n", name, new Date());
	}

}

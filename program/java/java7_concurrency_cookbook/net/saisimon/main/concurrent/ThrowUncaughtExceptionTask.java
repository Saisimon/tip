package net.saisimon.main.concurrent;

public class ThrowUncaughtExceptionTask implements Runnable {

	@Override
	public void run() {
		Integer.parseInt("number");
		System.out.println("OK");
	}

}

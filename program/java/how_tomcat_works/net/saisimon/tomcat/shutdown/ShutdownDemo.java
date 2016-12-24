package net.saisimon.tomcat.shutdown;

import java.io.IOException;

public class ShutdownDemo {
	
	public void start() {
		ShutdownHook hook = new ShutdownHook();
		Runtime.getRuntime().addShutdownHook(hook);
	}
	
	public static void main(String[] args) {
		ShutdownDemo sd = new ShutdownDemo();
		sd.start();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ShutdownHook extends Thread {

		@Override
		public void run() {
			System.out.println("Shutdown");
		}
		
	}
	
}

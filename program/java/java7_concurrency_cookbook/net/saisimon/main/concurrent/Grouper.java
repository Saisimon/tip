package net.saisimon.main.concurrent;

public class Grouper implements Runnable {
	
	private Results results;
	
	public Grouper(Results results) {
		super();
		this.results = results;
	}

	@Override
	public void run() {
		int finalResult = 0;
		System.out.printf("Grouper: Processing results...\n");
		int[] data = results.getData();
		for (int i : data) {
			finalResult += i;
		}
		System.out.printf("Grouper: Total result: %d.\n",finalResult);
	}

}

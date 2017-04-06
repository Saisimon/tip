package net.saisimon.main.concurrent;

public class JobSemaphoreMultiple implements Runnable {
	
	private PrintQueueSemaphoreMultiple printQueue;
	
	public JobSemaphoreMultiple(PrintQueueSemaphoreMultiple printQueue) {
		super();
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
	}

}

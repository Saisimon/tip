package net.saisimon.main.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class MyWorkThread extends ForkJoinWorkerThread {
	
	private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();

	protected MyWorkThread(ForkJoinPool pool) {
		super(pool);
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
		taskCounter.set(0);
	}

	@Override
	protected void onTermination(Throwable exception) {
		System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
		super.onTermination(exception);
	}
	
	public void addTask() {
		int counter = taskCounter.get().intValue();
		counter++;
		taskCounter.set(counter);
	}
	
	public int getCounter() {
		return taskCounter.get();
	}

}

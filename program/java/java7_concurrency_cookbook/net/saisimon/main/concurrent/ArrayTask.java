package net.saisimon.main.concurrent;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ArrayTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	
	private int[] array;
	private int start, end;
	
	public ArrayTask(int[] array, int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		System.out.printf("Task: Start from %d to %d\n", start, end);
		if (end - start < 10) {
			if (start < 3 && end > 3) {
				throw new RuntimeException("This task throws an Exception: Task from " + start + " to " + end);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			int middle = (start + end) / 2;
			ArrayTask t1 = new ArrayTask(array, start, middle + 1);
			ArrayTask t2 = new ArrayTask(array, middle + 1, end);
			invokeAll(t1, t2);
		}
		System.out.printf("Task: End form %d to %d\n", start, end);
		return 0;
	}

}

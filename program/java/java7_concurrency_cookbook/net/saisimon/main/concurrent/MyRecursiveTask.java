package net.saisimon.main.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;
	
	private int[] array;
	private int start, end;
	
	public MyRecursiveTask(int[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		MyWorkThread thread = (MyWorkThread) Thread.currentThread();
		thread.addTask();
		long result = 0;
		if (end - start < 100) {
			for (int i = start; i < end; i++) {
				result += array[i];
			}
		} else {
			int middle = (start + end) / 2;
			MyRecursiveTask t1 = new MyRecursiveTask(array, start, middle + 1);
			MyRecursiveTask t2 = new MyRecursiveTask(array, middle + 1, end);
			invokeAll(t1, t2);
			result = addResults(t1, t2);
		}
		return result;
	}
	
	private long addResults(MyRecursiveTask t1, MyRecursiveTask t2) {
		long value = 0;
		try {
			value = t1.get().longValue() + t2.get().longValue();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return value;
	}

}

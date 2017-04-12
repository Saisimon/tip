package net.saisimon.main.concurrent;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SearchNumberTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private static final int NOT_FOUND=-1;
	
	private int[] numbers;
	private int start, end;
	private TaskManager manager;
	private int number;
	
	public SearchNumberTask(int[] numbers, int start, int end, TaskManager manager, int number) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.manager = manager;
		this.number = number;
	}

	@Override
	protected Integer compute() {
		System.out.printf("Task: %d:%d\n", start, end);
		int result = 0;
		if (end - start > 10) {
			result = launchTasks();
		} else {
			result = lookForNumber();
		}
		return result;
	}

	private int lookForNumber() {
		for (int i = start; i < end; i++) {
			if (numbers[i] == number) {
				System.out.printf("Task: Number %d found in position %d\n", number, i);
				manager.cancelTasks(this);
				return i;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return NOT_FOUND;
	}

	private int launchTasks() {
		int middle = (start + end) / 2;
		SearchNumberTask t1 = new SearchNumberTask(numbers, start, middle + 1, manager, number);
		SearchNumberTask t2 = new SearchNumberTask(numbers, middle + 1, end, manager, number);
		manager.addTask(t1);
		manager.addTask(t2);
		t1.fork();
		t2.fork();
		int result = t1.join();
		if (result != NOT_FOUND) {
			return result;
		}
		return t2.join();
	}

	public void writeCancelMessage() {
		System.out.printf("Task: Canceled task from %d to %d\n", start, end);
	}

}

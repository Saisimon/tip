package net.saisimon.main.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class TaskManager {
	
	private List<ForkJoinTask<Integer>> tasks;

	public TaskManager() {
		tasks = new ArrayList<>();
	}

	public void cancelTasks(ForkJoinTask<Integer> cancelTask) {
		for (ForkJoinTask<Integer> forkJoinTask : tasks) {
			if (forkJoinTask != cancelTask) {
				// 取消一个还未执行的任务，如果任务已经开始它的执行，那么调用cancel()方法对它没有影响
				forkJoinTask.cancel(true);
				((SearchNumberTask)forkJoinTask).writeCancelMessage();
			}
		}
	}

	public void addTask(ForkJoinTask<Integer> task) {
		tasks.add(task);
	}
	
}

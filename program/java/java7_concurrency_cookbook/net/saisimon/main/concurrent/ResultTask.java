package net.saisimon.main.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String> {
	
	private String name;
	
	public ResultTask(Callable<String> callable) {
		super(callable);
		this.name = ((ExecutableTask)callable).getName();
	}

	// 在建立返回值和改变任务的状态为isDone状态后，done()方法被FutureTask类内部调用
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.printf("%s: Has been canceled\n", name);
		} else {
			System.out.printf("%s: Has finished\n", name);
		}
	}
	
}

package net.saisimon.main.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServer {
	
	private ThreadPoolExecutor executor;
	
	public ExecutorServer() {
		// 使用 Executors 工厂方法实例化 ThreadPoolExecutor 对象
//		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}
	
	public void executeTask(ExecutorTask task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Task Count: %d\n", executor.getTaskCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}
	
	public void endServer() {
		// 关闭线程池执行器
		executor.shutdown();
	}
	
}

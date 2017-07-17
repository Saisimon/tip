package net.saisimon.design.command;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Saisimon
 * 
 * 	任务调度
 *
 */
public class TaskSchedule {
	
	/**
	 * 请求集合
	 */
	private Vector<AbstractTask> tasks = new Vector<>();
	private long sleeptime = 10000000000L;
	
	/**
	 * 添加一个请求
	 * 
	 * @param taskEntry 请求
	 */
	public void addTask(AbstractTask taskEntry) {
		tasks.add(taskEntry);
		taskEntry.setTimeLastDone(System.currentTimeMillis());
		if (sleeptime > taskEntry.getTimeInterval()) {
			sleeptime = taskEntry.getTimeInterval();
		}
	}
	
	/**
	 * 移除一个请求
	 * 
	 * @param taskEntry 请求
	 */
	public void removeTask(AbstractTask taskEntry) {
		tasks.remove(taskEntry);
	}
	
	/**
	 * 按照次序依次执行请求的任务
	 */
	public void schedulePermorm() {
		try {
			Thread.sleep(sleeptime);
			Enumeration<AbstractTask> enumeration = tasks.elements();
			while (enumeration.hasMoreElements()) {
				AbstractTask taskEntry = enumeration.nextElement();
				if (taskEntry.getTimeInterval() + taskEntry.getTimeLastDone() <= System.currentTimeMillis()) {
					taskEntry.taskPerform();
					taskEntry.setTimeLastDone(System.currentTimeMillis());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TaskSchedule taskSchedule = new TaskSchedule();
		// 不同的请求
		AbstractTask t1 = new DickScanTask(10000);
		AbstractTask t2 = new BackupTask(5000);
		// 添加请求
		taskSchedule.addTask(t1);
		taskSchedule.addTask(t2);
		while (true) {
			// 执行请求
			taskSchedule.schedulePermorm();
		}
	}
	
}

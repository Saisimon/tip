package net.saisimon.main.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class MyThreadFactory implements ThreadFactory {
	
	private int count;
	private String name;
	private List<String> stats;
	
	public MyThreadFactory(String name) {
		this.name = name;
		this.count = 0;
		this.stats = new ArrayList<>();
	}

	// 实现接口 ThreadFactory 中的 newThread 方法
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, name + "_Thread_" + count);
		count++;
		stats.add(String.format("Create Thread %d with Name %s on %s", thread.getId(), name, new Date()));
		return thread;
	}
	
	public String getStatistics() {
		return stats.parallelStream().collect(Collectors.joining("\n"));
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getStats() {
		return stats;
	}
	public void setStats(List<String> stats) {
		this.stats = stats;
	}
	
}

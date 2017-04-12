package net.saisimon.main.concurrent;

public class PriorityEvent implements Comparable<PriorityEvent> {
	
	private int thread;
	private int priority;
	
	public PriorityEvent(int thread, int priority) {
		super();
		this.thread = thread;
		this.priority = priority;
	}
	
	// 实现 compareTo 方法来做排序
	@Override
	public int compareTo(PriorityEvent o) {
		if (this.priority > o.priority) {
			return -1;
		} else if (this.priority < o.priority) {
			return 1;
		} else {
			return 0;
		}
	}

	public int getThread() {
		return thread;
	}
	public int getPriority() {
		return priority;
	}
	
}

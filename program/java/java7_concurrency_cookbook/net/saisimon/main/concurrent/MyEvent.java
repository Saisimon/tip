package net.saisimon.main.concurrent;

public class MyEvent implements Comparable<MyEvent> {
	
	private String thread;
	private int priority;
	
	public MyEvent(String thread, int priority) {
		super();
		this.thread = thread;
		this.priority = priority;
	}
	
	@Override
	public int compareTo(MyEvent o) {
		if (this.getPriority() > o.getPriority()) {
			return -1;
		} else if (this.getPriority() < o.getPriority()) {
			return 1;
		} else {
			return 0;
		}
	}

	public String getThread() {
		return thread;
	}

	public int getPriority() {
		return priority;
	}
	
}

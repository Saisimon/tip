package net.saisimon.main.concurrent;

import java.util.Date;

public class MyThread extends Thread {
	
	private Date creationDate;
	private Date startDate;
	private Date finishDate;
	
	public MyThread(Runnable r, String name) {
		super(r, name);
		setCreationDate();
	}
	
	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(getName()).append(": Creation Date: ")
				.append(creationDate).append(" : Running time: ")
				.append(getExecutionTime()).append(" Milliseconds.");
		return buffer.toString();
	}

	public void setCreationDate() {
		this.creationDate = new Date();
	}

	public void setStartDate() {
		this.startDate = new Date();
	}

	public void setFinishDate() {
		this.finishDate = new Date();
	}
	
	public long getExecutionTime() {
		return finishDate.getTime() - startDate.getTime();
	}
	
}

package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

public abstract class MyWorkTask extends ForkJoinTask<Void> {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public MyWorkTask(String name) {
		super();
		this.name = name;
	}

	@Override
	public Void getRawResult() {
		return null;
	}

	@Override
	protected void setRawResult(Void value) {
		
	}

	@Override
	protected boolean exec() {
		Date startDate = new Date();
		compute();
		Date endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n", name, diff);
		return true;
	}
	
	protected abstract void compute();
	
	public String getName() {
		return name;
	}

}

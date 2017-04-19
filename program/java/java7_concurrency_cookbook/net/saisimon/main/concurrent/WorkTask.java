package net.saisimon.main.concurrent;

public class WorkTask extends MyWorkTask {
	
	private static final long serialVersionUID = 1L;
	
	private int[] array;
	private int start, end;
	
	public WorkTask(String name, int[] array, int start, int end) {
		super(name);
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (end - start > 100) {
			int middle = (start + end) / 2;
			WorkTask t1 = new WorkTask(this.getName() + "A", array, start, middle + 1);
			WorkTask t2 = new WorkTask(this.getName() + "B", array, middle + 1, end);
			invokeAll(t1, t2);
		} else {
			for (int i = start; i < end; i++) {
				array[i]++;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

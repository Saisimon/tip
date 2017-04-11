package net.saisimon.main.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class LineTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	
	private String[] line;
	private int start, end;
	private String word;
	
	public LineTask(String[] line, int start, int end, String word) {
		super();
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		int result = 0;
		if (end - start < 10) {
			result = count(line, start, end, word);
		} else {
			int middle = (start + end) / 2;
			LineTask t1 = new LineTask(line, start, middle + 1, word);
			LineTask t2 = new LineTask(line, middle + 1, end, word);
			invokeAll(t1, t2);
			try {
				result = groupResults(t1.get(), t2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private int groupResults(Integer n1, Integer n2) {
		return n1 + n2;
	}

	private int count(String[] line, int start, int end, String word) {
		int counter = 0;
		for (int i = start; i < end; i++) {
			if (line[i].equals(word)) {
				counter++;
			}
		}
//		try {
//			Thread.sleep(20);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return counter;
	}

}

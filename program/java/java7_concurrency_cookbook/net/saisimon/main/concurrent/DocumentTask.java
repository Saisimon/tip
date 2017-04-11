package net.saisimon.main.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	
	private String[][] document;
	private int start, end;
	private String word;
	
	public DocumentTask(String[][] document, int start, int end, String word) {
		super();
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		int result = 0;
		if (end - start < 10) {
			result = processLines(document, start, end, word);
		} else {
			int middle = (start + end) / 2;
			DocumentTask t1 = new DocumentTask(document, start, middle + 1, word);
			DocumentTask t2 = new DocumentTask(document, middle + 1, end, word);
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

	private int processLines(String[][] document, int start, int end, String word) {
		List<LineTask> lineTasks = new ArrayList<>();
		for (int i = start; i < end; i++) {
			LineTask task = new LineTask(document[i], 0, document[i].length, word);
			lineTasks.add(task);
		}
		invokeAll(lineTasks);
		int result = 0;
		for (LineTask lineTask : lineTasks) {
			try {
				result += lineTask.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}

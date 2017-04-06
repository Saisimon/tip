package net.saisimon.main.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {
	
	private int firstRow;
	private int lastRow;
	private MatrixMock matrixMock;
	private Results results;
	private int number;
	private CyclicBarrier cyclicBarrier;
	private CountDownLatch countDownLatch;
	
	public Searcher(int firstRow, int lastRow, MatrixMock matrixMock, Results results, int number,
			CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
		super();
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.matrixMock = matrixMock;
		this.results = results;
		this.number = number;
		this.cyclicBarrier = cyclicBarrier;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		int counter;
		System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
		for (int i = firstRow; i < lastRow; i++) {
			int[] row = matrixMock.getRow(i);
			counter = 0;
			for (int j = 0; j < row.length; j++) {
				if (row[j] == number) {
					counter++;
				}
			}
			results.setDate(i, counter);
		}
		System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());
		countDownLatch.countDown();
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}

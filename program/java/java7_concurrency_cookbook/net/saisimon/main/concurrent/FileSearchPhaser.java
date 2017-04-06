package net.saisimon.main.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearchPhaser implements Runnable {
	
	private String initPath;
	private String end;
	private List<String> results;
	// Phaser类提供的机制是在每个步骤的结尾同步线程
	private Phaser phaser;
	
	public FileSearchPhaser(String initPath, String end, Phaser phaser) {
		super();
		this.initPath = initPath;
		this.end = end;
		this.phaser = phaser;
		this.results = new ArrayList<>();
	}
	
	private void directoryProcess(File file) {
		File[] fileList = file.listFiles();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					directoryProcess(fileList[i]);
				} else {
					fileProcess(fileList[i]);
				}
			}
		}
	}
	
	private void fileProcess(File file) {
		if (file.getName().endsWith(end)) {
			results.add(file.getAbsolutePath());
		}
	}
	
	private void filterResults() {
		List<String> newResults = new ArrayList<>();
		long actualDate = new Date().getTime();
		for (String path : results) {
			File file = new File(path);
			if (actualDate - file.lastModified() < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
				newResults.add(path);
			}
		}
		results = newResults;
	}
	
	private boolean checkResults() {
		if (results.isEmpty()) {
			System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
			System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
			// arriveAndDeregister() 方法通知此线程已经结束actual phase，并离开 phased 操作。
			phaser.arriveAndDeregister();
			return false;
		} else {
			System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), 
					phaser.getPhase(), results.size());
			// arriveAndAwaitAdvance() 方法并通知 actual phase，它会被阻塞直到 phased 操作的全部参与线程结束 actual phase。
			phaser.arriveAndAwaitAdvance();
			return true;
		}
	}
	
	private void showInfo() {
		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}
		phaser.arriveAndAwaitAdvance();
	}


	@Override
	public void run() {
		phaser.arriveAndAwaitAdvance();
		System.out.printf("%s: Starting.\n", Thread.currentThread().getName());
		File file = new File(initPath);
		if (file.isDirectory()) {
			directoryProcess(file);
		}
		if (!checkResults()) {
			return;
		}
		filterResults();
		if (!checkResults()) {
			return;
		}
		showInfo();
		phaser.arriveAndDeregister();
		System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
	}

}

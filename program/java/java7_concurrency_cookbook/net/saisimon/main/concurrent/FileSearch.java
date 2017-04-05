package net.saisimon.main.concurrent;

import java.io.File;

public class FileSearch implements Runnable {
	
	/**
	 * 路径
	 */
	private String initPath;
	/**
	 * 查找的文件名
	 */
	private String fileName;
	
	public FileSearch(String initPath, String fileName) {
		super();
		this.initPath = initPath;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s : The Search has been Interrupted\n", Thread.currentThread().getName());
			}
		}
	}
	
	// 在指定文件夹中查询文件
	private void directoryProcess(File file) throws InterruptedException {
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
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

	private void fileProcess(File file) throws InterruptedException {
		if (file.getName().equalsIgnoreCase(fileName)) {
			System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

}

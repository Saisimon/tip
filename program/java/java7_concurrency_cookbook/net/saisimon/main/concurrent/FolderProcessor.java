package net.saisimon.main.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = 1L;
	
	private String path;
	private String extension;
	
	public FolderProcessor(String path, String extension) {
		super();
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		List<String> list = new ArrayList<>();
		List<FolderProcessor> processors = new ArrayList<>();
		File file = new File(path);
		File[] content = file.listFiles();
		if (content != null) {
			for (File f : content) {
				if (f.isDirectory()) {
					FolderProcessor processor = new FolderProcessor(f.getAbsolutePath(), extension);
					// 使用fork()方法异步地执行
					processor.fork();
					processors.add(processor);
				} else {
					if (checkFile(f.getName())) {
						list.add(f.getAbsolutePath());
					}
				}
			}
			if (processors.size() > 50) {
				System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), processors.size());
			}
			addResultsFromTasks(list, processors);
		}
		return list;
	}

	private void addResultsFromTasks(List<String> list, List<FolderProcessor> processors) {
		for (FolderProcessor folderProcessor : processors) {
			// 等待任务执行的完成，并且返回任务的结果
			// get()和join()有两个主要的区别：
			// ·join()方法不能被中断。如果你中断调用join()方法的线程，这个方法将抛出InterruptedException异常
			// ·如果任务抛出任何未受检异常，get()方法将返回一个ExecutionException异常，而join()方法将返回一个RuntimeException异常
			list.addAll(folderProcessor.join());
		}
	}

	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}

}

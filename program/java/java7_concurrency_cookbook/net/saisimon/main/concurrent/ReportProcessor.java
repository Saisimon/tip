package net.saisimon.main.concurrent;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {
	
	private CompletionService<String> service;
	private boolean end;
	
	public ReportProcessor(CompletionService<String> service) {
		super();
		this.service = service;
		end = false;
	}

	@Override
	public void run() {
		while (!end) {
			try {
				// 获取CompletionService执行的下个已完成任务的Future对象
				Future<String> result = service.poll(20, TimeUnit.SECONDS);
				if (result != null) {
					String report = result.get();
					System.out.printf("ReportReceiver: Report Received:%s\n", report);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
	
}

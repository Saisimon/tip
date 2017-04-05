package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {
	
	private Deque<Event> deque;
	
	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		// 设置为守护线程，守护线程的优先级非常低，通常在程序里没有其他线程运行时才会执行它。
		// 当守护线程是程序里唯一在运行的线程时，JVM会结束守护线程并终止程序
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long difference = 0L;
		boolean delete = false;
		if (deque.isEmpty()) {
			return;
		}
		do {
			Event event = deque.getLast();
			difference = date.getTime() - event.getDate().getTime();
			if (difference > 10000) {
				System.out.printf("Cleaner : %s\n", event.getEvent());
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);
		if (delete) {
			System.out.printf("Cleaner : Size of the queue : %d\n", deque.size());
		}
	}

}

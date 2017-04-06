package net.saisimon.main.concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
	
	private LinkedList<String> buffer;
	private int maxSize;
	private ReentrantLock lock;
	private Condition lines;
	private Condition space;
	private boolean pendingLines;
	
	public Buffer(int maxSize) {
		this.maxSize = maxSize;
		buffer = new LinkedList<>();
		lock = new ReentrantLock();
		lines = lock.newCondition();
		space = lock.newCondition();
		pendingLines = true;
	}
	
	// 往缓冲区中添加数据
	public void insert(String line) {
		// 获得锁
		lock.lock();
		try {
			// 当缓冲区达到最大值，space 条件下的线程等待
			while (buffer.size() == maxSize) {
				space.await();
			}
			buffer.offer(line);
			System.out.printf("%s : Insert Line : %d\n", Thread.currentThread().getName(), buffer.size());
			// 唤醒 lines 条件下的线程
			lines.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// finally 中释放锁
			lock.unlock();
		}
	}
	
	// 获取缓冲区中的数据
	public String get() {
		String line = null;
		// 获得锁
		lock.lock();
		try {
			// 当缓冲区为空时，lines 条件下的线程等待
			while (buffer.size() == 0 && hasPendingLines()) {
				lines.await();
			}
			if (hasPendingLines()) {
				line = buffer.poll();
				System.out.printf("%s : Insert Line : %d\n", Thread.currentThread().getName(), buffer.size());
				// 唤醒 space 条件下的线程
				space.signalAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// finally 中释放锁
			lock.unlock();
		}
		return line;
	}
	
	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}
	
	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}
	
}

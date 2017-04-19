package net.saisimon.main.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {

	private static final long serialVersionUID = 1L;
	
	private AtomicInteger counter;
	private LinkedBlockingQueue<E> transfered;
	private ReentrantLock lock;
	
	public MyPriorityTransferQueue() {
		counter = new AtomicInteger(0);
		transfered = new LinkedBlockingQueue<>();
		lock = new ReentrantLock();
	}

	@Override
	public boolean tryTransfer(E e) {
		lock.lock();
		boolean value;
		if (counter.get() == 0) {
			value = false;
		} else {
			put(e);
			value = true;
		}
		lock.unlock();
		return value;
	}

	@Override
	public void transfer(E e) throws InterruptedException {
		lock.lock();
		if (counter.get() != 0) {
			put(e);
			lock.unlock();
		} else {
			transfered.add(e);
			lock.unlock();
			synchronized (e) {
				e.wait();
			}
		}
	}

	@Override
	public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		lock.lock();
		if (counter.get() != 0) {
			put(e);
			lock.unlock();
			return true;
		} else {
			transfered.add(e);
			long waitTime = TimeUnit.MILLISECONDS.convert(timeout, unit);
			lock.unlock();
			e.wait(waitTime);
			lock.lock();
			if (transfered.contains(e)) {
				transfered.remove(e);
				lock.unlock();
				return false;
			} else {
				lock.unlock();
				return true;
			}
		}
	}

	@Override
	public boolean hasWaitingConsumer() {
		return counter.get() != 0;
	}

	@Override
	public int getWaitingConsumerCount() {
		return counter.get();
	}

	@Override
	public E take() throws InterruptedException {
		lock.lock();
		counter.incrementAndGet();
		E value = transfered.poll();
		if (value == null) {
			lock.unlock();
			value = super.take();
			lock.lock();
		} else {
			synchronized (value) {
				value.notify();
			}
		}
		counter.decrementAndGet();
		lock.unlock();
		return value;
	}
	
}

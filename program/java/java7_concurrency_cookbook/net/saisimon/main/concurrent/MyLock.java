package net.saisimon.main.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {
	
	private MyAbstractQueuedSynchronizer sync;
	
	public MyLock() {
		sync = new MyAbstractQueuedSynchronizer();
	}

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		try {
			return sync.tryAcquireNanos(1, 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.new ConditionObject();
	}

}

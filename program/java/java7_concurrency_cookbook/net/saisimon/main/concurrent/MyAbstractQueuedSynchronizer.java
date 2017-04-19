package net.saisimon.main.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {

	private static final long serialVersionUID = 1L;
	
	private AtomicInteger state;
	
	public MyAbstractQueuedSynchronizer() {
		state = new AtomicInteger(0);
	}

	// 尝试访问临界区时，调用这个方法
	@Override
	protected boolean tryAcquire(int arg) {
		return state.compareAndSet(0, 1);
	}

	// 尝试释放临界区的访问，调用这个方法
	@Override
	protected boolean tryRelease(int arg) {
		return state.compareAndSet(1, 0);
	}
	
}

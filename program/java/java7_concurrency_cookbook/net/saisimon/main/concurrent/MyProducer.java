package net.saisimon.main.concurrent;

public class MyProducer implements Runnable {
	
	private MyPriorityTransferQueue<MyEvent> buffer;
	
	public MyProducer(MyPriorityTransferQueue<MyEvent> buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			buffer.put(new MyEvent(Thread.currentThread().getName(), i));
		}
	}

}

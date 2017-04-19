package net.saisimon.main.concurrent;

public class MyConsumer implements Runnable {
	
	private MyPriorityTransferQueue<MyEvent> buffer;
	
	public MyConsumer(MyPriorityTransferQueue<MyEvent> buffer) {
		super();
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1002; i++) {
			try {
				MyEvent event = buffer.take();
				System.out.printf("Consumer: %s: %d\n", event.getThread(), event.getPriority());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

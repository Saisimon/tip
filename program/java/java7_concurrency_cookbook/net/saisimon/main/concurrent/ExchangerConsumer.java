package net.saisimon.main.concurrent;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerConsumer implements Runnable {
	
	private List<String> buffer;
	private Exchanger<List<String>> exchanger;
	
	public ExchangerConsumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Consumer : Cycle %d\n", cycle);
			try {
				// 交换 buffer 对象
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int j = 0; j < 10; j++) {
				String message = buffer.get(0);
				System.out.printf("Consumer : %s\n", message);
				buffer.remove(0);
			}
		}
	}

}

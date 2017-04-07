package net.saisimon.main.concurrent;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerProducer implements Runnable {
	
	private List<String> buffer;
	private Exchanger<List<String>> exchanger;
	
	public ExchangerProducer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Producer : Cycle %d\n", cycle);
			for (int j = 0; j < 10; j++) {
				String message = "Event : " + ((10 * i) + j);
				System.out.printf("Producer : %s\n", message);
				buffer.add(message);
			}
			try {
				// 交换 buffer 对象
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Producer : %d\n", buffer.size());
			cycle++;
		}
	}

}

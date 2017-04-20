package net.saisimon.main.concurrent;

public class SensorB implements Runnable {
	
	private ParkingCounter counter;
	
	public SensorB(ParkingCounter counter) {
		super();
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < 500; i++) {
			counter.carInOrOut();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

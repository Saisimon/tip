package net.saisimon.main.concurrent;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingCounter extends AtomicInteger {

	private static final long serialVersionUID = 1L;
	
	private int maxNumber;
	
	public ParkingCounter(int maxNumber) {
		set(0);
		this.maxNumber = maxNumber;
	}
	
	public boolean carIn() {
		for (;;) {
			int value = get();
			if (value == maxNumber) {
				System.out.printf("ParkingCounter: The parking lot is full.\n");
				return false;
			} else {
				int newValue = value + 1;
				boolean changed = compareAndSet(value, newValue);
				if (changed) {
					System.out.printf("ParkingCounter: A car has entered.\n");
					return true;
				}
			}
		}
	}
	
	public boolean carOut() {
		for (;;) {
			int value = get();
			if (value == 0) {
				System.out.printf("ParkingCounter: The parking lot is empty.\n");
				return false;
			} else {
				int newValue = value - 1;
				boolean changed = compareAndSet(value, newValue);
				if (changed) {
					System.out.printf("ParkingCounter: A car has gone out.\n");
					return true;
				}
			}
		}
	}
	
	public void carInOrOut() {
		Random rand = new Random();
		if (rand.nextBoolean() || rand.nextBoolean()) {
			carIn();
		} else {
			carOut();
		}
	}

}

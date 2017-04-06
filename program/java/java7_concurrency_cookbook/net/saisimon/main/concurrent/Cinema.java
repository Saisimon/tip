package net.saisimon.main.concurrent;

public class Cinema {
	
	// 两个独立属性，一个线程在访问其中一个属性时，另一个线程可以访问另一个属性，相互之间是没有影响的
	private long vacanciesCinema1;
	private long vacanciesCinema2;
	
	// 通过不同的对象引用来来修饰不同的代码块
	private final Object controlCinema1 = new Object(), controlCinema2 = new Object();
	
	public Cinema() {
		vacanciesCinema1 = 20;
		vacanciesCinema2 = 20;
	}
	
	public boolean sellTickets1(int number) {
		synchronized (controlCinema1) {
			if (number < vacanciesCinema1) {
				vacanciesCinema1 -= number;
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean sellTickets2(int number) {
		synchronized (controlCinema2) {
			if (number < vacanciesCinema2) {
				vacanciesCinema2 -= number;
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean returnTickets1(int number) {
		synchronized (controlCinema1) {
			vacanciesCinema1 += number;
			return true;
		}
	}
	
	public boolean returnTickets2(int number) {
		synchronized (controlCinema2) {
			vacanciesCinema2 += number;
			return true;
		}
	}

	public long getVacanciesCinema1() {
		return vacanciesCinema1;
	}
	public long getVacanciesCinema2() {
		return vacanciesCinema2;
	}
	
}

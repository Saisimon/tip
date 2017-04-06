package net.saisimon.main.concurrent;

public class Account {
	
	private double balance;
	
	public Account(double balance) {
		this.balance = balance;
	}

	// 使用synchronized关键字将这个方法转换成临界区,只有一个线程能改变balance的值
	public synchronized void addAccount(double amount) {
		double temp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		temp += amount;
		balance = temp;
	}
	
	// 使用synchronized关键字将这个方法转换成临界区,只有一个线程能改变balance的值
	public synchronized void subtractAmount(double amount) {
		double temp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		temp -= amount;
		balance = temp;
	}

	public double getBalance() {
		return balance;
	}
	
}

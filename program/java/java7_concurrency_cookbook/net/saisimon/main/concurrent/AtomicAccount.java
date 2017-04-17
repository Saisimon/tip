package net.saisimon.main.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicAccount {
	
	private AtomicLong balance;
	
	public AtomicAccount() {
		balance = new AtomicLong();
	}
	
	public long getBalance() {
		return balance.get();
	}
	
	public void setBalance(long balance) {
		this.balance.set(balance);
	}
	
	public void addAmount(long amount) {
		this.balance.getAndAdd(amount);
	}
	
	public void subtractAmount(long amount) {
		this.balance.getAndAdd(-amount);
	}
	
}

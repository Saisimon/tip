package net.saisimon.main.concurrent;

public class AtomicCompany implements Runnable {
	
	private AtomicAccount account;
	
	public AtomicCompany(AtomicAccount account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.addAmount(1000);
		}
	}

}

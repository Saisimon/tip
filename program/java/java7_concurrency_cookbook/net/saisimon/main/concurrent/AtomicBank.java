package net.saisimon.main.concurrent;

public class AtomicBank implements Runnable {
	
	private AtomicAccount account;
	
	public AtomicBank(AtomicAccount account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.subtractAmount(1000);
		}
	}

}

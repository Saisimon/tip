package net.saisimon.main.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {
	
	private String name;

	public UserValidator(String name) {
		super();
		this.name = name;
	}
	
	public boolean validate(String name, String password) {
		Random rand = new Random();
		long duration = (long) (Math.random() * 10);
		System.out.printf("Validator %s: Validating a user during %d seconds\n", name, duration);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return rand.nextBoolean();
	}
	
	public String getName() {
		return name;
	}
	
}

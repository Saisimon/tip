package net.saisimon.main.concurrent;

import java.util.concurrent.Callable;

public class TaskValidator implements Callable<String> {
	
	private UserValidator validator;
	private String name;
	private String password;
	
	public TaskValidator(UserValidator validator, String name, String password) {
		super();
		this.validator = validator;
		this.name = name;
		this.password = password;
	}

	@Override
	public String call() throws Exception {
		if (!validator.validate(name, password)) {
			System.out.printf("%s: The user has not been found\n", validator.getName());
			throw new Exception("Error validating user");
		}
		System.out.printf("%s: The user has been found\n",validator.getName());
		return validator.getName();
	}

}

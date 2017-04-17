package net.saisimon.main.concurrent;

public class Contact {
	
	private String name;
	private String phone;
	
	public Contact(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	
}

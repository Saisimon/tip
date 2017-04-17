package net.saisimon.main.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;

public class ContactTask implements Runnable {
	
	private ConcurrentSkipListMap<String, Contact> map;
	private String id;
	
	public ContactTask(ConcurrentSkipListMap<String, Contact> map, String id) {
		super();
		this.map = map;
		this.id = id;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			Contact contact = new Contact(id, String.valueOf(i + 1000));
			map.put(id + contact.getPhone(), contact);
		}
	}

}

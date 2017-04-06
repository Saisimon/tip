package net.saisimon.main.concurrent;

public class Results {
	
	private int[] data;
	
	public Results(int size) {
		data = new int[size];
	}
	
	public void setDate(int position, int value) {
		data[position] = value;
	}
	
	public int[] getData() {
		return data;
	}
	
}

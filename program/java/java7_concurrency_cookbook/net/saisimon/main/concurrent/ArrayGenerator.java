package net.saisimon.main.concurrent;

import java.util.Random;

public class ArrayGenerator {
	
	public int[] generateArray(int size) {
		int[] array = new int[size];
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(10);
		}
		return array;
	}
	
}

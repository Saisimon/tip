package net.saisimon.main.concurrent;

import java.util.Random;

public class MatrixMock {
	
	private int[][] data;
	
	public MatrixMock(int size, int length, int number) {
		int count = 0;
		data = new int[size][length];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = rand.nextInt(100);
				if (data[i][j] == number) {
					count++;
				}
			}
		}
		System.out.printf("Mock: There are %d ocurrences of %d in generated data.\n", count, number);
	}
	
	public int[] getRow(int row) {
		if (row >= 0 && row < data.length) {
			return data[row];
		}
		return null;
	}
	
	public int[][] getData() {
		return data;
	}
	
}

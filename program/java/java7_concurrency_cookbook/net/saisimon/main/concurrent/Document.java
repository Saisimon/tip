package net.saisimon.main.concurrent;

import java.util.Random;

public class Document {
	
	private String[] words = { "the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main" };
	
	public String[][] generateDocument(int numLines, int numWords, String word) {
		int counter = 0;
		String[][] document = new String[numLines][numWords];
		Random rand = new Random();
		for (int i = 0; i < document.length; i++) {
			for (int j = 0; j < document[i].length; j++) {
				int idx = rand.nextInt(words.length);
				document[i][j] = words[idx];
				if (words[idx].equals(word)) {
					counter++;
				}
			}
		}
		System.out.println("Document: The word appears " + counter + " times in the document");
		return document;
	}
	
}

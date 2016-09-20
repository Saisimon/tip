package net.saisimon.qlcoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class NewWord {
	
	private static final String[] SS = {"，","。","！","《","》","：","；","‘","’","“","”","【","】","（","）","、","？","—","　"};
	
	public void read(String path) throws IOException {
		Map<String, Integer> letterMap = new HashMap<>();
		Map<String, Integer> wordMap = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		try (FileInputStream fis = new FileInputStream(new File(path))) {
			byte[] bs = new byte[1024];
			byte[] temp = new byte[3];
			int len = 0;
			while(fis.read(bs) != -1) {
				for (int i = 0; i < bs.length; i++) {
					byte b = bs[i];
					if (b < 0) {
						temp[++len - 1] = b;
						if (len == 3) {
							len = 0;
							String str = new String(temp, "utf-8");
							if (!checkLetter(str)) {
								continue;
							}
							if (letterMap.containsKey(str)) {
								int c = letterMap.get(str);
								letterMap.put(str, c + 1);
							} else {
								letterMap.put(str, 1);
							}
							sb.append(str);
							if (sb.length() == 2) {
								String word = sb.toString();
								if (wordMap.containsKey(word)) {
									int c = wordMap.get(word);
									wordMap.put(word, c + 1);
								} else {
									wordMap.put(word, 1);
								}
								sb.delete(0, 1);
							}
						}
					} else {
						sb.setLength(0);
						len = 0;
					}
				}
			}
		} catch (IOException e) {
			throw e;
		}
		double result = 0;
		String maxWord = "";
		for (String key : wordMap.keySet()) {
			Integer count = wordMap.get(key);
			if (count < 100) {
				continue;
			}
			String firstKey = key.substring(0, 1);
			String secondKey = key.substring(1);
			Integer firstCount = letterMap.get(firstKey);
			Integer secondCount = letterMap.get(secondKey);
			double temp = (count / 194285.0) / ((firstCount * secondCount) / (194285.0 * 194285.0));
			if (temp > result) {
				maxWord = key;
				result = temp;
			}
		}
		System.out.println(maxWord);
	}
	
	private boolean checkLetter(String str) throws UnsupportedEncodingException {
		if (str == null || str.trim().equals("")) {
			return false;
		}
		for (int i = 0; i < SS.length; i++) {
			if (str.equals(SS[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		NewWord nw = new NewWord();
		String path = "d:/tmp/santi.txt";
		try {
			nw.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
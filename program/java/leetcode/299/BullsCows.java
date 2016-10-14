package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 299. Bulls and Cows
 * 	You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. 
 * 	Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). 
 * 	Your friend will use successive guesses and hints to eventually derive the secret number.
 * 
 * 	For example:
 * 		Secret number:  "1807"
 * 		Friend's guess: "7810"
 * 
 * 	Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * 	
 * 	Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 
 * 	In the above example, your function should return "1A3B".
 * 
 * 	Please note that both secret number and friend's guess may contain duplicate digits
 * 	For example:
 * 		Secret number:  "1123"
 * 		Friend's guess: "0111"
 * 
 * 	In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 * 
 * 	You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 * 
 * @author Saisimon
 *
 */
public class BullsCows {
	// 两次遍历，效率相对较低
	public String getHintA(String secret, String guess) {
		int a = 0, b = 0;
		// secret 中的字符列表，排除与 guess 相同的字符
		List<Character> list = new ArrayList<>();
		// guess 中的字符列表，排除与 secret 相同的字符
		List<Character> tmp = new ArrayList<>();
		// 遍历 secret
        for (int i = 0; i < secret.length(); i++) {
        	if (secret.charAt(i) == guess.charAt(i)) {
        		a++;
        	} else {
        		list.add(secret.charAt(i));
        		tmp.add(guess.charAt(i));
        	}
        }
        // 遍历 guess 字符列表，与 secret 字符列表比较
        for (Character c : tmp) {
			if (list.contains(c)) {
				b++;
				list.remove(c);
			}
		}
        return a + "A" + b + "B";
    }
	
	// 一次遍历，利用一个数组来存储字符出现的次数
	public String getHintB(String secret, String guess) {
		int bulls = 0;
		int cows = 0;
		// 存储字符出现的次数的数组, 小于 0 表示 guess 出现过，大于 0 表示 secret 出现过
		int[] numbers = new int[10];
		for (int i = 0; i < secret.length(); i++) {
			int s = Character.getNumericValue(secret.charAt(i));
			int g = Character.getNumericValue(guess.charAt(i));
			if (s == g) {
				bulls++;
			} else {
				// secret 出现的字符 guess 已经出现过 cows加一
				if (numbers[s] < 0) {
					cows++;
				}
				// guess 出现的字符 secret 已经出现过 cows加一
				if (numbers[g] > 0) {
					cows++;
				}
				// 表明 secret 当前字符出现
				numbers[s]++;
				// 表明 guess 当前字符出现
				numbers[g]--;
			}
		}
		return bulls + "A" + cows + "B";
	}
}

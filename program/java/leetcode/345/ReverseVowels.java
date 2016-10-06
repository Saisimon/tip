package net.saisimon.leetcode;

/**
 * 345. Reverse Vowels of a String
 * 	Write a function that takes a string as input and reverse only the vowels of a string.
 * 	Example 1:
 * 	Given s = "hello", return "holle".
 * 	Example 2:
 * 	Given s = "leetcode", return "leotcede".
 * 
 * @author Saisimon
 *
 */
public class ReverseVowels {
	
	private static char[] VOWELS = {'a','e','i','o','u'};
	
	public String reverseVowels(String s) {
		char[] cs = s.toCharArray();
		int start = 0;
		int end = cs.length - 1;
		boolean sflag = false;
		boolean eflag = false;
		while(start < end) {
			sflag = isVowel(cs[start]);
			eflag = isVowel(cs[end]);
			if (sflag && eflag) {
				swap(cs, start, end);
				start++;
				end--;
			} else if (sflag) {
				end--;
			} else if (eflag) {
				start++;
			} else {
				start++;
				end--;
			}
		}
        return new String(cs);
    }
	
	// 交换元素
	private void swap(char[] cs, int a, int b) {
		char temp = cs[a];
		cs[a] = cs[b];
		cs[b] = temp;
	}
	
	// 是否为元音字符
	private boolean isVowel(char c) {
		for (int i = 0; i < VOWELS.length; i++) {
			if (Character.toLowerCase(c) == VOWELS[i]) {
				return true;
			}
		}
		return false;
	}
}

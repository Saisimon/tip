package net.saisimon.leetcode;

/**
 * 409. Longest Palindrome
 * 	Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 * 	This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 	
 * 	Note:
 * 		Assume the length of given string will not exceed 1,010.
 * 
 * 	Example:
 * 		Input: "abccccdd"
 * 		Output: 7
 * 	 	Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * 
 * @author Saisimon
 *
 */
public class LongestPalindrome {
	public int longestPalindrome(String s) {
        if (s == null || s.trim().equals("")) {
        	return 0;
        }
        // 存放字母字符出现次数，大小写一共52个字母
     	int[] letters = new int[52];
        int res = 0;
        // 输入的字符数组
        char[] cs = s.toCharArray();
        // 遍历字符数组获取字母出现的次数
        for (int i = 0; i < cs.length; i++) {
			if (cs[i] >= 'a' && cs[i] <= 'z') {
				letters[cs[i] - 'a' + 26]++;
			} else if (cs[i] >= 'A' && cs[i] <= 'Z') {
				letters[cs[i] - 'A']++;
			}
		}
        // 是否存在有字母出现了奇数次
        boolean flag = false;
        for (int i = 0; i < letters.length; i++) {
			if (letters[i] % 2 == 0) {
				// 偶数提供所有个字母
				res += letters[i];
			} else {	
				// 	存在有字母出现奇数次
				flag = true;
				// 奇数提供其下最大的偶数个字母
				res += (letters[i] - 1);
			}
		}
        if (flag) {
        	// 存在字母出现奇数次，回文字符串长度为奇数，长度加一
        	res++;
        }
        return res;
    }
}

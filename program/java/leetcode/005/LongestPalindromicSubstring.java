package net.saisimon.leetcode;

/**
 * <h1>5. Longest Palindromic Substring</h1>
 * 	<p>Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 
 * 	<p>Example:
 * 		<br>&nbsp; Input: "babad"
 * 		<br>&nbsp; Output: "bab"
 * 		<br>&nbsp; Note: "aba" is also a valid answer.
 * 
 * 	<p>Example:
 * 		<br>&nbsp; Input: "cbbd"
 * 		<br>&nbsp; Output: "bb"
 * 
 * @author Saisimon
 *
 */
public class LongestPalindromicSubstring {
	
	private int head = 0;
	private int tail = 0;
	
	public String longestPalindrome(String s) {
		if (s == null || s.isEmpty()) {
        	return s;
        }
		for (int i = 0; i < s.length(); i++) {
			find(i, i, s);
			find(i, i + 1, s);
		}
		return s.substring(head, tail + 1);
	}
	
	// 中心开花
	private void find(int start, int end, String s) {
		while (start >= 0 && end < s.length()) {
			if (s.charAt(start) == s.charAt(end)) {
				if (tail - head < end - start) {
					head = start;
					tail = end;
				}
			} else {
				break;
			}
			start--;
			end++;
		}
	}
	
}

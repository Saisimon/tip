package net.saisimon.leetcode;

/**
 * <h1>392. Is Subsequence</h1>
 * 	<p>Given a string s and a string t, check if s is subsequence of t.
 * 	
 * 	<p>You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * 
 * 	<p>A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * 	
 * 	<p>Example 1:
 * 		<br>&nbsp; s = "abc", t = "ahbgdc"
 * 		<br>&nbsp; Return true.
 * 
 * 	<p>Example 2:
 * 		<br>&nbsp; s = "axc", t = "ahbgdc"
 * 		<br>&nbsp; Return false.
 * 
 * 	<p>Follow up:
 * 		<br>&nbsp; If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 * 
 * @author Saisimon
 *
 */
public class IsSubsequence {
	public boolean isSubsequence(String s, String t) {
		// 特殊情况
		if (s.length() == 0) {
			return true;
		} else if (s.length() > t.length()) {
        	return false;
        }
		// 依次遍历 t 
        for (int i = 0, j = 0; i < t.length(); i++) {
        	// 当匹配到字符时，j 向前移动一步 
			if (s.charAt(j) == t.charAt(i)) {
				j++;
			}
			// j 到达 s 的末尾时，说明完全匹配，返回真
			if (j == s.length()) {
				return true;
			}
		}
        // 循环结束还没有完全匹配，则返回假
        return false;
    }
}

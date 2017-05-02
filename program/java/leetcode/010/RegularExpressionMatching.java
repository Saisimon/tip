package net.saisimon.leetcode;

/**
 * <h1>10. Regular Expression Matching</h1>
 * 	<p>Implement regular expression matching with support for '.' and '*'.
 * 	
 * 	<p>'.' Matches any single character.
 * 	<p>'*' Matches zero or more of the preceding element.
 *
 *	<p>The matching should cover the entire input string (not partial).
 *
 *	<p>The function prototype should be:
 *		<br>&nbsp; bool isMatch(String s, String p)
 *
 *	<p>Some examples:
 *		<br>&nbsp; isMatch("aa","a") → false
 *		<br>&nbsp; isMatch("aa","aa") → true
 *		<br>&nbsp; isMatch("aaa","aa") → false
 *		<br>&nbsp; isMatch("aa", "a*") → true
 *		<br>&nbsp; isMatch("aa", ".*") → true
 *		<br>&nbsp; isMatch("ab", ".*") → true
 *		<br>&nbsp; isMatch("aab", "c*a*b") → true
 * 
 * @author Saisimon
 *
 */
public class RegularExpressionMatching {
	
	public boolean isMatch(String s, String p) {
		int i = 0, j = 0;
		while (i < s.length() && j < p.length()) {
			if (p.charAt(j) == '*') {
    			return false; // 当前字符为 *，正则表达式错误，返回 false
    		} else if (j + 1 < p.length() && p.charAt(j + 1) == '*') { // 下一个字符为 * 时
    			if (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) { // 当前字符能模糊匹配上
    				int temp = i; // 当前字符可以匹配到哪一位
					while (temp < s.length() && (p.charAt(j) == s.charAt(temp) || p.charAt(j) == '.')) {
						temp++;
					}
					for (int x = i; x <= temp; x++) {
						// 递归判断子串是否能匹配
						if (isMatch(s.substring(x), p.substring(j + 2))) {
							return true;
						}
					}
					// 全部都不能匹配，则返回 false
					return false;
    			}
    			// 不能匹配则跳过
				j += 2;
			} else if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) { // 能精确匹配
				i++;
				j++;
			} else { // 否则返回 false
				return false;
			}
		}
		if (i != s.length()) { // 待匹配字符串未匹配完全，则返回 false
			return false;
		} else {
			// 判断正则表达式剩余部分是否存在不能匹配的情况
			while (j < p.length()) {
				if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
					j += 2;
				} else {
					return false;
				}
			}
			return true;
		}
    }
	
}

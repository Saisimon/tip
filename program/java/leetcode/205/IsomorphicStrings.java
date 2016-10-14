package net.saisimon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. Isomorphic Strings
 * 	Given two strings s and t, determine if they are isomorphic.
 * 	Two strings are isomorphic if the characters in s can be replaced to get t.
 * 	All occurrences of a character must be replaced with another character while preserving the order of characters. 
 * 	No two characters may map to the same character but a character may map to itself.
 * 	
 * 	For example:
 * 		Given "egg", "add", return true.
 * 		Given "foo", "bar", return false.
 * 		Given "paper", "title", return true.
 * 
 * 	Note:
 * 		You may assume both s and t have the same length.
 * 
 * @author Saisimon
 *
 */
public class IsomorphicStrings {
	public boolean isIsomorphic(String s, String t) {
		// 保证两个字符串不为空且长度一致
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		return check(s, t) && check(t, s);
    }
	
	private boolean check(String s, String t) {
		Map<Character, Character> data = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
			if (data.containsKey(s.charAt(i))) {
				Character tmp = data.get(s.charAt(i));
				if (t.charAt(i) != tmp.charValue()) {
					return false;
				}
			} else {
				data.put(s.charAt(i), t.charAt(i));
			}
		}
        return true;
	}
	
	// 使用 数组存储字符
	public boolean isIsomorphicB(String s, String t) {
		// 前半部分存 s 字符的位置，后半部分存 t 字符的位置
		int[] m = new int[512];
		for (int i = 0; i < s.length(); i++) {
			// 当前半部分存的 s 字符的位置和后半部分存的 t 字符的位置不一致时，说明两个字符串不是同构的，返回假
			if (m[s.charAt(i)] != m[t.charAt(i) + 256]) {
				return false;
			}
			// 存储 s 和 t 当前字符的位置
			m[s.charAt(i)] = m[t.charAt(i) + 256] = i + 1;
		}
		// 全部一致，则两个字符串是同构的，返回真
		return true;
	}
}

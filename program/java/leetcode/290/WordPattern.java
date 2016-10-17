package net.saisimon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. Word Pattern
 * 	Given a pattern and a string str, find if str follows the same pattern.
 * 	Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * 	
 * 	Examples:
 * 		pattern = "abba", str = "dog cat cat dog" should return true.
 * 		pattern = "abba", str = "dog cat cat fish" should return false.
 * 		pattern = "aaaa", str = "dog cat cat dog" should return false.
 * 		pattern = "abba", str = "dog dog dog dog" should return false.
 * 	
 * 	Notes:
 * 		You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 * 
 * @author Saisimon
 *
 */
public class WordPattern {	
	// 使用 HashMap 存储对应关系
	public boolean wordPattern(String pattern, String str) {
		if (pattern == null && str == null) {
			return true;
		} else if (pattern == null || str == null) {
			return false;
		}
		String[] tmp = str.split(" ");
		char[] cs = pattern.toCharArray();
		// 当长度不匹配是，返回假
		if (tmp.length != cs.length) {
			return false;
		}
		// 对应关系
		Map<Character, String> data = new HashMap<>();
		// 遍历字符数组，判断对应关系是否正确
		for (int i = 0; i < cs.length; i++) {
			if (data.containsKey(cs[i])) {
				// 对应关系不符
				if (!tmp[i].equals(data.get(cs[i]))) {
					return false;
				}
			} else if (data.containsValue(tmp[i])){
				// 对应关系不符
				return false;
			} else {
				data.put(cs[i], tmp[i]);
			}
		}
		return true;
    }
}

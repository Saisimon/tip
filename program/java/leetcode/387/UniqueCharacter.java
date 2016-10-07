package net.saisimon.leetcode;

/**
 * 387. First Unique Character in a String
 * 	Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * 	Examples:
 * 	s = "leetcode"
 *	return 0.
 *	s = "loveleetcode",
 *	return 2.
 * 
 * @author Saisimon
 *
 */
public class UniqueCharacter {
	public int firstUniqChar(String s) {
        char[] cs = s.toCharArray();
        // 存储重复出现字符的出现次数
        int[] arr = new int[26];
        out:for (int i = 0; i < cs.length; i++) {
        	// 当前字符是否出现过
        	if (arr[cs[i] - 'a'] != 0) {
				continue;
			}
        	// 遍历当前字符后的字符数组，进行比较
			for (int j = i + 1; j < cs.length; j++) {
				if (cs[i] == cs[j]) {
					arr[cs[i] - 'a']++;
					continue out;
				}
			}
			return i;
		}
        // 未发现唯一字符
        return -1;
    }
	
}

package net.saisimon.leetcode;

/**
 * 242. Valid Anagram
 * 	Given two strings s and t, write a function to determine if t is an anagram of s.
 * 	For example,
 * 		s = "anagram", t = "nagaram", return true.
 * 		s = "rat", t = "car", return false.
 * 	Note:
 * 		You may assume the string contains only lowercase alphabets.
 * 
 * @author Saisimon
 *
 */
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
        int[] sarr = new int[26];
        int[] tarr = new int[26];
        for (int i = 0; i < s.length(); i++) {
        	sarr[s.charAt(i) - 'a']++;
		}
        for (int i = 0; i < t.length(); i++) {
        	tarr[t.charAt(i) - 'a']++;
		}
        for (int i = 0; i < 26; i++) {
        	if (sarr[i] != tarr[i]) {
        		return false;
        	}
        }
		return true;
    }
}

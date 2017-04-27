package net.saisimon.leetcode;

/**
 * <h1>3. Longest Substring Without Repeating Characters</h1>
 * 	<p>Given a string, find the length of the longest substring without repeating characters.
 * 	
 * 	<p>Examples:
 * 		<br>&nbsp; Given "abcabcbb", the answer is "abc", which the length is 3.
 * 		<br>&nbsp; Given "bbbbb", the answer is "b", with the length of 1.
 * 		<br>&nbsp; Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * @author Saisimon
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
	
	public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        if (s != null) {
        	int[] letters = new int[128];
        	int count = 0;
        	for (int i = 0; i < s.length(); i++) {
        		if (letters[s.charAt(i)] == 0) {
        			letters[s.charAt(i)] = i + 1;
        			count++;
        		} else {
        			longest = Math.max(count, longest);
        			count = 0;
        			i = letters[s.charAt(i)] - 1;
        			for (int j = 0; j < letters.length; j++) {
        				letters[j] = 0;
        			}
        		}
            }
        	longest = Math.max(count, longest);
        }
        return longest;
    }
	
}

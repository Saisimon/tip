package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>30. Substring with Concatenation of All Words</h1>
 * 	<p>You are given a string, s, and a list of words, words, that are all of the same length. 
 * 	<p>Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * 	
 * 	<p>For example, given:
 * 		<br>&nbsp; s: "barfoothefoobarman"
 * 		<br>&nbsp; words: ["foo", "bar"]
 * 		<br>&nbsp; You should return the indices: [0,9].
 * 		<br>&nbsp; (order does not matter).
 * 
 * @author Saisimon
 *
 */
public class SubstringConcatenationAllWords {
	
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (null != s && null != words && 0 != words.length) {
			Map<String, Integer> wordMap = new HashMap<>();
			int count = 0;
			for (String word : words) {
				if (wordMap.containsKey(word)) {
					wordMap.put(word, wordMap.get(word) + 1);
					count++;
				} else {
					wordMap.put(word, 1);
				}
			}
			int start = 0;
			int size = words[0].length();
			while (start + size * words.length <= s.length()) {
				int idx = start;
				String sub = s.substring(idx, idx + size);
				if ("".equals(sub)) {
					res.add(start);
					start++;
				} else {
					int temp = count + wordMap.size();
					Map<String, Integer> recordMap = new HashMap<>(wordMap);
					Map<String, Integer> positionMap = new HashMap<>();
					while (null != recordMap.get(sub)) {
						if (0 == recordMap.get(sub)) {
							break;
						} else {
							recordMap.put(sub, recordMap.get(sub) - 1);
							if (positionMap.get(sub) != null) {
								positionMap.put(sub, idx);
							}
							temp--;
						}
						idx += size;
						if (idx + size > s.length()) {
							break;
						}
						sub = s.substring(idx, idx + size);
					}
					Integer position = positionMap.get(sub);
					if (temp == 0) {
						res.add(start);
						start++;
					} else if (position != null) {
						if (start == position) {
							start = position + 1;
						} else {
							start = position;
						}
					} else {
						start++;
					}
				}
			}
		}
		return res;
    }
	
}

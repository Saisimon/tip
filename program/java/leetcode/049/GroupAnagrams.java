package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>49. Group Anagrams</h1>
 * 	<p>Given an array of strings, group anagrams together.
 * 	
 * 	<p>For example
 * 		<br>&nbsp; given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 * 	
 * 	<p>Return:
 * 		<br>&nbsp; [
 * 			<br>&nbsp;&nbsp; ["ate", "eat","tea"],
 * 			<br>&nbsp;&nbsp; ["nat","tan"],
 * 			<br>&nbsp;&nbsp; ["bat"]
 *		<br>&nbsp; ]
 *
 *	<p>Note: 
 *		<br>&nbsp;All inputs will be in lower-case.
 * 
 * @author Saisimon
 *
 */
public class GroupAnagrams {
	
	public List<List<String>> groupAnagramsA(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		if (strs != null && strs.length > 0) {
			Map<String, List<String>> map = new HashMap<>();
			for (String str : strs) {
				char[] cs = str.toCharArray();
				Arrays.sort(cs);
				String temp = new String(cs);
				List<String> list = map.getOrDefault(temp, new ArrayList<>());
				list.add(str);
				map.put(temp, list);
			}
			for (List<String> list : map.values()) {
				res.add(list);
			}
		}
		return res;
	}
	
	public List<List<String>> groupAnagramsB(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		if (strs != null && strs.length > 0) {
			Map<String, Integer> map = new HashMap<>();
			for (String str : strs) {
				char[] cs = str.toCharArray();
				Arrays.sort(cs);
				String temp = new String(cs);
				int idx = map.getOrDefault(temp, -1);
				List<String> list = null;
				if (idx == -1) {
					map.put(temp, res.size());
					list = new ArrayList<>();
					list.add(str);
					res.add(list);
				} else {
					list = res.get(idx);
					list.add(str);
				}
			}
		}
		return res;
	}
	
	// Time Limit Exceeded
	@Deprecated
	public List<List<String>> groupAnagramsC(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		if (strs != null && strs.length > 0) {
			List<int[]> list = new ArrayList<>();
			for (String word : strs) {
				int idx = -1;
				list:for (int i = 0; i < list.size(); i++) {
					int[] arr = list.get(i);
					if (arr[0] != word.length()) {
						continue list;
					}
					int[] temp = new int[26];
					System.arraycopy(arr, 1, temp, 0, 26);
					for (int j = 0; j < word.length(); j++) {
						temp[word.charAt(j) - 'a']--;
					}
					for (int num : temp) {
						if (num != 0) {
							continue list;
						}
					}
					idx = i;
					break list;
				}
				if (idx == -1) {
					List<String> group = new ArrayList<>();
					group.add(word);
					res.add(group);
					int[] arr = new int[27];
					arr[0] = word.length();
					for (int i = 0; i < word.length(); i++) {
						arr[word.charAt(i) - 'a' + 1]++;
					}
					list.add(arr);
				} else {
					List<String> group = res.get(idx);
					group.add(word);
				}
			}
		}
        return res;
    }
	
}

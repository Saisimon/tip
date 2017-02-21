package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>78. Subsets</h1>
 * 	<p>Given a set of distinct integers, nums, return all possible subsets.
 * 
 * 	<p>Note: The solution set must not contain duplicate subsets.
 * 
 * 	<p>For example:
 * 		<blockquote>
 * 		If nums = [1,2,3], a solution is: </br>
 * 		[ </br>
 * 			[3], </br>
 * 			[1], </br>
 * 			[2], </br>
 * 			[1,2,3], </br>
 * 			[1,3], </br>
 * 			[2,3], </br>
 * 			[1,2], </br>
 * 			[] </br>
 * 		]
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> empty = new ArrayList<>();
		results.add(empty);
		Map<List<Integer>, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			List<Integer> t = new ArrayList<>();
        	t.add(nums[i]);
        	results.add(t);
        	map.put(t, i);
		}
        for (int i = 0; i < nums.length - 1; i++) {
        	Map<List<Integer>, Integer> tempMap = new HashMap<>();
        	for (List<Integer> list : map.keySet()) {
				int start = map.get(list);
				for (int j = start + 1; j < nums.length; j++) {
					List<Integer> temp = new ArrayList<>(list);
					temp.add(nums[j]);
					tempMap.put(temp, j);
				}
			}
        	map = tempMap;
        	results.addAll(map.keySet());
		}
        return results;
    }
	
}

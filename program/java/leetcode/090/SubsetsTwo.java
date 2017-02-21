package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>90. Subsets II</h1>
 * 	<p>Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * 
 * 	<p>Note: The solution set must not contain duplicate subsets.
 * 
 * 	<p>For example:
 * 		<blockquote>
 * 		If nums = [1,2,2], a solution is: </br>
 * 		[ </br>
 * 			[1], </br>
 * 			[2], </br>
 * 			[1,2,2], </br>
 * 			[2,2], </br>
 * 			[1,2], </br>
 * 			[] </br>
 * 		]
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class SubsetsTwo {
	
	/*
	 * 1 -> 1,2 1,3 1,4
	 * 2 -> 2,3 2,4
	 * 3 -> 3,4
	 * 
	 * 1,2 -> 1,2,3 1,2,4
	 * 1,3 -> 1,3,4
	 * 2,3 -> 2,3,4
	 * 
	 * 1,2,3 -> 1,2,3,4
	 * 
	 */
	public List<List<Integer>> subsetsWithDupA(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> empty = new ArrayList<>();
		results.add(empty);
		Map<List<Integer>, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			List<Integer> t = new ArrayList<>();
        	t.add(nums[i]);
        	List<Integer> value = null;
        	if (map.containsKey(t)) {
        		value = map.get(t);
        	} else {
        		results.add(t);
        		value = new ArrayList<>();
        	}
        	value.add(i);
        	map.put(t, value);
		}
        for (int i = 0; i < nums.length - 1; i++) {
        	Map<List<Integer>, List<Integer>> tempMap = new HashMap<>();
        	for (List<Integer> list : map.keySet()) {
        		List<Integer> value = map.get(list);
        		for (int j = 0; j < value.size(); j++) {
        			for (int k = value.get(j) + 1; k < nums.length; k++) {
    					List<Integer> temp = new ArrayList<>(list);
    					temp.add(nums[k]);
    					List<Integer> tmp = null;
    		        	if (tempMap.containsKey(temp)) {
    		        		tmp = tempMap.get(temp);
    		        	} else {
    		        		tmp = new ArrayList<>();
    		        	}
    		        	tmp.add(k);
    					tempMap.put(temp, tmp);
    				}
				}
			}
        	map = tempMap;
        	results.addAll(map.keySet());
		}
        return results;
    }
	
	/*
	 * 递归求解
	 */
	public List<List<Integer>> subsetsWithDupB(int[] nums) {
	    Arrays.sort(nums);
	    List<List<Integer>> res = new ArrayList<>();
	    List<Integer> each = new ArrayList<>();
	    helper(res, each, 0, nums);
	    return res;
	}
	
	public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
	    if (pos <= n.length) {
	        res.add(each);
	    }
	    int i = pos;
	    while (i < n.length) {
	        each.add(n[i]);
	        helper(res, new ArrayList<>(each), i + 1, n);
	        each.remove(each.size() - 1);
	        i++;
	        while (i < n.length && n[i] == n[i - 1]) {i++;}
	    }
	    return;
	}
	
}

package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>40. Combination Sum II</h1>
 * 	<p>Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * 	<p>Each number in C may only be used once in the combination.
 * 
 * 	<p>Note:
 * 		<br>&nbsp; All numbers (including target) will be positive integers.
 * 		<br>&nbsp; The solution set must not contain duplicate combinations.
 * 
 * 	<p>For example
 * 		<br>&nbsp; given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
 * 	
 * 	<p>A solution set is: 
 * 		<br>&nbsp; [
 * 			<br>&nbsp;&nbsp; [1, 7],
 * 			<br>&nbsp;&nbsp; [1, 2, 5],
 * 			<br>&nbsp;&nbsp; [2, 6],
 * 			<br>&nbsp;&nbsp; [1, 1, 6]
 *		<br>&nbsp; ]
 * 
 * @author Saisimon
 *
 */
public class CombinationSumTwo {
	
	// 递归求解，去重
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0) {
        	return new ArrayList<>();
        }
        Arrays.sort(candidates);
        return find(candidates, target, 0);
    }
	
	private List<List<Integer>> find(int[] candidates, int target, int start) {
		List<List<Integer>> res = new ArrayList<>();
		int idx = start;
		int repeat = 1; // 当前数有多少个重复的
        while (idx < candidates.length && candidates[idx] <= target) {
        	while (idx + 1 < candidates.length && candidates[idx] == candidates[idx + 1]) {
    			idx++;
    			repeat++;
    		}
    		int count = 1; // 使用多少个当前数
        	while (count <= repeat) {
        		int rest = target - candidates[idx] * count;
        		if (rest == 0) {
        			List<Integer> list = new ArrayList<>();
        			for (int i = 0; i < count; i++) {
        				list.add(candidates[idx]);
        			}
        			res.add(list);
        		} else if (rest > 0) {
        			List<List<Integer>> sub = find(candidates, rest, idx + 1); // 递归
            		for (List<Integer> list : sub) {
            			for (int i = 0; i < count; i++) {
            				list.add(0, candidates[idx]);
            			}
            			res.add(list);
            		}
        		}
        		count++;
        	}
        	idx++;
        	repeat = 1;
        }
        return res;
	}
	
}

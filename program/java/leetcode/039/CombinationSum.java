package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>39. Combination Sum</h1>
 * 	<p>Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * 	<p>The same repeated number may be chosen from C unlimited number of times.
 * 	
 * 	<p>Note:
 * 		<br>&nbsp; All numbers (including target) will be positive integers.
 * 		<br>&nbsp; The solution set must not contain duplicate combinations.
 * 
 * 	<p>For example
 * 		<br>&nbsp; given candidate set [2, 3, 6, 7] and target 7, 
 * 	<p>A solution set is: 
 * 		<br>&nbsp; [
 * 			<br>&nbsp;&nbsp; [7],
 * 			<br>&nbsp;&nbsp; [2, 2, 3]
 *		<br>&nbsp; ]
 * 
 * @author Saisimon
 *
 */
public class CombinationSum {
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
        	return new ArrayList<>();
        }
        // 先对数组排序
        Arrays.sort(candidates);
        // 递归求解
        return find(candidates, target, 0);
    }
	
	private List<List<Integer>> find(int[] candidates, int target, int start) {
		List<List<Integer>> res = new ArrayList<>();
		int idx = start;
        while (idx < candidates.length && candidates[idx] <= target) {
        	int mod = target % candidates[idx];
        	int quotient = target / candidates[idx];
        	if (mod == 0) { // 能整除
        		List<Integer> list = new ArrayList<>(quotient);
        		for (int i = 0; i < quotient; i++) {
        			list.add(candidates[idx]);
        		}
        		res.add(list);
        	}
        	int count = 1;
        	while (count < quotient) {
        		int rest = mod + candidates[idx] * count; // 递归获取剩下的目标结果
        		List<List<Integer>> sub = find(candidates, rest, idx + 1);
        		for (List<Integer> list : sub) {
        			for (int i = 0; i < quotient - count; i++) {
        				list.add(0, candidates[idx]);
        			}
        			res.add(list);
        		}
        		count++;
        	}
        	idx++;
        }
        return res;
	}
	
}

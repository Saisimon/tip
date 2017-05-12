package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>47. Permutations II</h1>
 * 	<p>Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * 
 * 	<p>For example,
 * 		<br>&nbsp; [1,1,2] have the following unique permutations:
 * 		<br>&nbsp; [
 * 			<br>&nbsp;&nbsp; [1,1,2],
 * 			<br>&nbsp;&nbsp; [1,2,1],
 * 			<br>&nbsp;&nbsp; [2,1,1]
 *		<br>&nbsp; ]
 * 
 * @author Saisimon
 *
 */
public class PermutationsTwo {
	
	// 分治递归去重
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		} else if (nums.length == 1) {
			List<Integer> list = new ArrayList<>();
			list.add(nums[0]);
			res.add(list);
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (!check(nums, i, nums[i])) {
					int [] temp = new int[nums.length - 1];
					for (int j = 1; j < nums.length; j++) {
						temp[j - 1] = nums[(j + i) % nums.length];
					}
					List<List<Integer>> sub = permuteUnique(temp);
					for (List<Integer> list : sub) {
						list.add(0, nums[i]);
						res.add(list);
					}
				}
			}
		}
		return res;
    }
	
	private boolean check(int[] nums, int end, int target) {
		for (int i = 0; i < end; i++) {
			if (nums[i] == target) {
				return true;
			}
		}
		return false;
	}
	
}

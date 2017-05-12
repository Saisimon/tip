package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>46. Permutations</h1>
 * 	<p>Given a collection of distinct numbers, return all possible permutations.
 * 
 * 	<p>For example,
 * 		<br>&nbsp; [1,2,3] have the following permutations:
 * 		<br>&nbsp; [
 * 			<br>&nbsp;&nbsp; [1,2,3],
 * 			<br>&nbsp;&nbsp; [1,3,2],
 * 			<br>&nbsp;&nbsp; [2,1,3],
 * 			<br>&nbsp;&nbsp; [2,3,1],
 * 			<br>&nbsp;&nbsp; [3,1,2],
 * 			<br>&nbsp;&nbsp; [3,2,1]
 *		<br>&nbsp; ]
 * 
 * @author Saisimon
 *
 */
public class Permutations {
	
	// 分治递归
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		} else if (nums.length == 1) {
			List<Integer> list = new ArrayList<>();
			list.add(nums[0]);
			res.add(list);
		} else {
			for (int i = 0; i < nums.length; i++) {
				int [] temp = new int[nums.length - 1];
				for (int j = 1; j < nums.length; j++) {
					temp[j - 1] = nums[(j + i) % nums.length];
				}
				List<List<Integer>> sub = permute(temp);
				for (List<Integer> list : sub) {
					list.add(0, nums[i]);
					res.add(list);
				}
			}
		}
		return res;
    }
	
}

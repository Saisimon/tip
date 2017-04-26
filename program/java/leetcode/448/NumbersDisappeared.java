package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>448. Find All Numbers Disappeared in an Array</h1>
 * 	<p>Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * 	<p>Find all the elements of [1, n] inclusive that do not appear in this array.
 * 	<p>Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * 	
 * 	<p>Example:
 * 		<br>&nbsp; Input: [4,3,2,7,8,2,3,1]
 * 		<br>&nbsp; Output: [5,6]
 * 
 * @author Saisimon
 *
 */
public class NumbersDisappeared {
	
	/**
	 * 	<p> [4,3,2,7,8,2,3,1]
	 * 	<p> [7,3,2,0,8,2,3,1]
	 * 	<p> [3,3,2,0,8,2,0,1]
	 * 	<p> [2,3,0,0,8,2,0,1]
	 * 	<p> [3,0,0,0,8,2,0,1]
	 *	<p> [3,0,0,0,1,2,0,0]
	 *	<p> [0,0,0,0,3,2,0,0]
	 *	<p>          ^ ^
	 *  <p>         [5,6]
	 */
	public List<Integer> findDisappearedNumbers(int[] nums) {
		if (nums == null) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != 0 && nums[nums[i] - 1] != 0) {
				int temp = nums[i];
				nums[i] = nums[temp - 1];
				nums[temp - 1] = 0;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				result.add(i + 1);
			}
		}
		return result;
    }
	
}

package net.saisimon.leetcode;

/**
 * <h1>238. Product of Array Except Self</h1>
 * 	<p>Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * 
 * 	<p>Solve it without division and in O(n).
 * 	
 * 	<p>For example:
 * 		<br>&nbsp; given [1,2,3,4], return [24,12,8,6].
 * 
 * 	<p>Follow up:
 * 		<br>&nbsp; Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 * 
 * @author Saisimon
 *
 */
public class ProductArray {
	// 求出非零元素积，再除以本身
	public int[] productExceptSelfA(int[] nums) {
		// 空数组直接返回
		if (nums.length == 0) {
			return nums;
		}
		// 乘积
		long product = nums[0];
		// 0 的个数
		int zero = 0;
		// 遍历数组，获取总乘积和 0 的数量
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] != 0) {
        		product *= nums[i];
        	} else {
        		zero++;
        	}
		}
        // 根据条件除以本身得到结果
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] != 0) { // 当前数不等于 0
        		if (zero > 0) { // 数组中存在 0 ，其乘积为 0
        			nums[i] = 0;
        		} else { // 不存在 0 ，其乘积为总乘积除以本身
        			nums[i] = (int) (product / nums[i]);
        		}
        	} else { // 当前数等于 0
        		if (zero > 1) { // 数组中 0 的数量不止一个，则乘积为 0 
        			nums[i] = 0;
        		} else { // 数组中只有当前这一个 0 ，则乘积就为总乘积
        			nums[i] = (int) product;
        		}
        	}
		}
        return nums;
    }
	
	/*
	 * nums = 2,3,1,4
	 * res = 1,2,6,6
	 * 
	 * res = 1,2,6,6 	right = 1;
	 * res = 1,2,6,6 	right = 4;
	 * res = 1,2,24,6 	right = 4;
	 * res = 1,8,24,6 	right = 12;
	 * res = 12,8,24,6 	right = 24
	 * 
	 */
	public int[] productExceptSelfB(int[] nums) {
		int[] res = new int[nums.length];
		res[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}
		for (int i = nums.length - 1, right = 1; i >= 0; i--) {
			res[i] *= right;
			right *= nums[i];
		}
		return res;
	}
}

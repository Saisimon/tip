package net.saisimon.leetcode;

/**
 * 198. House Robber
 * 	You are a professional robber planning to rob houses along a street. 
 * 	Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * 	
 * 	Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * 	
 * 	Example:
 * 		1, 2, 5, 3, 6, 7, 4, 8 -> 1 + 5 + 7 + 8 = 21
 * 
 * 	动态规划求解
 * 
 * @author Saisimon
 *
 */
public class HouseRobber {
	// 遍历,顺序求解
	public int robA(int[] nums) {
        if (nums.length <= 0) {
        	return 0;
        } else if (nums.length == 1) {
        	return nums[0];
        } else {
        	int[] tmp = new int[nums.length];
        	tmp[0] = nums[0];
        	tmp[1] = Math.max(nums[1], tmp[0]);
        	for (int i = 2; i < nums.length; i++) {
        		tmp[i] = Math.max(tmp[i - 2] + nums[i], tmp[i - 1]);
        	}
        	return tmp[nums.length - 1];
        }
    }
	
	// 递归,从下至上求解，时间复杂度过高
	public int robB(int[] nums) {
		if (nums.length <= 0) {
        	return 0;
        } else if (nums.length == 1) {
        	return nums[0];
        } else {
        	int[] lsub = new int[nums.length - 2];
        	System.arraycopy(nums, 0, lsub, 0, nums.length - 2);
        	int[] rsub = new int[nums.length - 1];
        	System.arraycopy(nums, 0, rsub, 0, nums.length - 1);
        	return Math.max(robB(lsub) + nums[nums.length - 1], robB(rsub));
        }
	}
	
}

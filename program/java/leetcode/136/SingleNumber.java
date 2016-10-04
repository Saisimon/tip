package net.saisimon.leetcode;

/**
 * 136. Single Number
 * 	Given an array of integers, every element appears twice except for one. Find that single one.
 * 	
 * 	@author Saisimon
 *
 */
public class SingleNumber {
	public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++)
        	// 异或	b = a ^ b ^ a
            res = res ^ nums[i];
        return res;
    }
}

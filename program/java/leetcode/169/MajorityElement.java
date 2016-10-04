package net.saisimon.leetcode;

import java.util.Arrays;

/**
 * 169. Majority Element
 * 	Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * 	You may assume that the array is non-empty and the majority element always exist in the array.	
 * 
 * 	@author Saisimon
 *
 */
public class MajorityElement {
	public int majorityElementA(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
    }
	
	public int majorityElementB(int[] nums) {
		int major = nums[0], count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (count == 0) {
				count++;
				major = nums[i];
			} else if (major == nums[i]) {
				count++;
			} else {
				count--;
			}
		}
        return major;
	}
}

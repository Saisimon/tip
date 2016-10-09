package net.saisimon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * 	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * 	You may assume that each input would have exactly one solution.
 * 	Example:
 * 		Given nums = [2, 7, 11, 15], target = 9,
 * 		Because nums[0] + nums[1] = 2 + 7 = 9,
 * 		return [0, 1].
 * 	note:
 * 		The return format had been changed to zero-based indices. Please read the above updated description carefully.
 * 
 * @author Saisimon
 *
 */
public class TwoSum {
	public int[] twoSumA(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] results = {i,j};
                    return results;
                }
            }
        }
        return null;
    }
	
	public int[] twoSumB(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				int[] results = {map.get(target - nums[i]), i};
				return results;
			}
			map.put(nums[i], i);
		}
        return null;
    }
}

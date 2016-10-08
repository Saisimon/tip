package net.saisimon.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate
 * 	Given an array of integers, find if the array contains any duplicates. 
 * 	Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * 
 * @author Saisimon
 *
 */
public class ContainsDuplicate {
	public boolean containsDuplicateA(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
			if (!set.add(nums[i])) {
				return true;
			}
		}
        return false;
    }
	
	public boolean containsDuplicateB(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				return true;
			}
		}
        return false;
    }
}

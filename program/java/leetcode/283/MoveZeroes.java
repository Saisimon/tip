package net.saisimon.leetcode;

/**
 * 283. Move Zeroes
 * 	Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * 	For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * 
 * @author Saisimon
 *
 */
public class MoveZeroes {
	public void moveZeroes(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				swap(nums, left, i);
				left++;
			}
		}
    }
	
	// 交换元素
	private void swap(int[] nums, int a, int b) {
		if (a != b) {
			nums[a] = nums[a] ^ nums[b];
			nums[b] = nums[b] ^ nums[a];
			nums[a] = nums[a] ^ nums[b];
		}
	}
}

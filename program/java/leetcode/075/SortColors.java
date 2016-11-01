package net.saisimon.leetcode;

/**
 * <h1>75. Sort Colors</h1>
 * 	<p>Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * 
 * 	<p>Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 
 * 	<p>Note:
 * 		<blockquote>
 * 			You are not suppose to use the library's sort function for this problem.
 * 		</blockquote>
 * 
 * 	<p>Follow up:
 * 		<blockquote>
 * 			A rather straight forward solution is a two-pass algorithm using counting sort. <br>
 * 			First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's. <br>
 * 			Could you come up with an one-pass algorithm using only constant space?
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class SortColors {
	/*
	 * 1,2,0,1,2,0,1,2,0 start = 0 end = 8 gap = 0
	 * 0,2,0,1,2,0,1,2,1 start = 0 end = 8 gap = 1
	 * 0,2,0,1,2,0,1,2,1 start = 1 end = 8 gap = 1
	 * 0,2,0,1,2,0,1,1,2 start = 1 end = 7 gap = 1
	 * 0,1,0,1,2,0,1,2,2 start = 1 end = 6 gap = 1
	 * 0,0,0,1,2,1,1,2,2 start = 1 end = 6 gap = 2
	 * 0,0,0,1,2,1,1,2,2 start = 2 end = 6 gap = 2
	 * 0,0,0,2,1,1,1,2,2 start = 2 end = 6 gap = 3
	 * 0,0,0,1,1,1,2,2,2 start = 2 end = 5 gap = 3
	 * 
	 * 1,0,2
	 * 2,0,1
	 * 0,1,2
	 */
	public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int gap = 0;
        while (start <= end && start <= end - gap) {
        	if (nums[start] == 0) {
        		start++;
        	} else if (nums[start] == 2) {
        		swap(nums, start, end - gap);
        		nums[end - gap] = 1;
        		nums[end] = 2;
        		end--;
        	} else {
        		swap(nums, start, end - gap);
        		gap++;
        	}
        }
    }
	
	// 交换数组中的元素
	private void swap(int[] nums, int a, int b) {
		if (nums[a] == nums[b]) {
			return;
		}
		nums[a] = nums[a] ^ nums[b];
		nums[b] = nums[b] ^ nums[a];
		nums[a] = nums[a] ^ nums[b];
	}
}

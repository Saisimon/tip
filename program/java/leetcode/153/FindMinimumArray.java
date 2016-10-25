package net.saisimon.leetcode;

/**
 * <h1>153. Find Minimum in Rotated Sorted Array</h1>
 * 	<p>Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * 	<p>(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * 	<p>Find the minimum element.
 * 
 * 	<p>You may assume no duplicate exists in the array.
 * 
 * @author Saisimon
 *
 */
public class FindMinimumArray {
	// 遍历
	public int findMin(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
			min = Math.min(min, nums[i]);
		}
        return min;
    }
	
	// 二分查找
	public int findMinB(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int middle;
        while (start < end) {
        	middle = start + (end - start) / 2;
        	if (nums[middle] < nums[end]) {
        		end = middle;
        	} else {
        		start = middle + 1;
        	}
        }
        return nums[start];
    }
}

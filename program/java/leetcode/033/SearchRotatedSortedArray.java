package net.saisimon.leetcode;

/**
 * <h1>33. Search in Rotated Sorted Array</h1>
 * 	<p>Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 	
 * 	<p>(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 	
 * 	<p>You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 	
 * 	<p>You may assume no duplicate exists in the array.
 * 
 * @author Saisimon
 *
 */
public class SearchRotatedSortedArray {
	
	public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
        	return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start <= end) {
        	if (nums[start] < nums[end]) { // 数组没有转圈，全部从小到大排列
        		return binarySearch(nums, start, end, target);
        	} else if (nums[start] < target) { // 目标可能在左边的子数组中
    			while (end - 1 > start && nums[end] > nums[end - 1]) {
    				end--;
    			}
    			return binarySearch(nums, start, --end, target);
    		} else if (nums[end] > target) { // 目标可能在右边的子数组中
    			while (start + 1 < end && nums[start + 1] > nums[start]) {
    				start++;
    			}
    			return binarySearch(nums, ++start, end, target);
    		} else if (nums[start] == target) {
    			return start;
    		} else if (nums[end] == target) {
    			return end;
    		} else {
    			return -1;
    		}
        }
        return -1;
    }
	
	// 二分查找
	private int binarySearch(int[] nums, int start, int end, int target) {
		while (start <= end) {
			int middle = start + (end - start) / 2;
			if (nums[middle] > target) {
				end = middle - 1;
			} else if (nums[middle] < target) {
				start = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}
	
}

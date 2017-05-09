package net.saisimon.leetcode;

/**
 * <h1>34. Search for a Range</h1>
 * 	<p>Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * 	
 * 	<p>Your algorithm's runtime complexity must be in the order of O(log n).
 * 	
 * 	<p>If the target is not found in the array, return [-1, -1].
 * 	
 * 	<p>For example,
 * 		<br>&nbsp; Given [5, 7, 7, 8, 8, 10] and target value 8,
 * 		<br>&nbsp; return [3, 4].
 * 
 * @author Saisimon
 *
 */
public class SearchForRange {
	
	public int[] searchRange(int[] nums, int target) {
		int[] res = {-1, -1};
		if (nums == null) {
			return res;
		}
		// 先二分查找找到其中一个符合的索引
        int idx = binarySearch(nums, 0, nums.length - 1, target);
        if (idx != -1) {
        	int start = idx;
            int end = idx;
            // 然后向两边伸展，查找符合目标的索引
            while ((start >= 0 && nums[start] == target) || (end < nums.length && nums[end] == target)) {
            	if (start >= 0 && nums[start] == target) {
            		start--;
            	}
            	if (end < nums.length && nums[end] == target) {
            		end++;
            	}
            }
            res[0] = ++start;
            res[1] = --end;
        }
        return res;
    }
	
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

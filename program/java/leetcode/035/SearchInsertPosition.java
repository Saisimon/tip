package net.saisimon.leetcode;

/**
 * <h1>35. Search Insert Position</h1>
 * 	<p>Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * 	
 * 	<p>You may assume no duplicates in the array.
 * 
 * 	<p>Here are few examples:
 * 		<blockquote>
 * 			[1,3,5,6], 5 → 2
 * 			[1,3,5,6], 2 → 1
 * 			[1,3,5,6], 7 → 4
 * 			[1,3,5,6], 0 → 0
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class SearchInsertPosition {
	public int searchInsert(int[] nums, int target) {
		// 小于最小值，大于最大值，快速返回
		if (target <= nums[0]) {
			return 0;
		} else if (target > nums[nums.length - 1]) {
			return nums.length;
		}
		// 数组已经排序，使用二分查找查询位置
        int start = 0;
        int end = nums.length - 1;
        int middle = 0;
        while (start < end) {
        	middle = start + (end - start) / 2;
        	if (nums[middle] > target) {
        		end = middle;
        	} else if (nums[middle] < target) {
        		start = middle + 1;
        	} else {
        		return middle;
        	}
        }
        return start;
    }
}

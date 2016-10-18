package net.saisimon.leetcode;

/**
 * 26. Remove Duplicates from Sorted Array
 * 	Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * 	Do not allocate extra space for another array, you must do this in place with constant memory.
 * 	For example,
 * 		Given input array nums = [1,1,2],
 * 		Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
 * 		It doesn't matter what you leave beyond the new length.
 * 
 * @author Saisimon
 *
 */
public class RemoveDuplicatesSort {
	// 数组有顺序，则重复的值都在一起，只需过滤即可
	public int removeDuplicates(int[] nums) {
		// 空数组直接返回 0
		if (nums.length < 1) {
			return 0;
		}
		// 位置
        int m = 1;
        // 待比较的值
        int tmp = nums[0];
        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
        	// 与前一次待比较的值不同时，改变 m 位的值为当前位
			if (nums[i] != tmp) {
				nums[m] = nums[i];
				// 改变待比较值为当前值
				tmp = nums[i];
				// 位置加一
				m++;
			}
		}
        return m;
    }
}

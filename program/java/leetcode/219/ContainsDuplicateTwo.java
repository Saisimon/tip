package net.saisimon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. Contains Duplicate II
 * 	Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array 
 * 	such that nums[i] = nums[j] and the difference between i and j is at most k.
 * 	
 * @author Saisimon
 *
 */
public class ContainsDuplicateTwo {
	// 使用 HashMap 存储数字出现的位置
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> data = new HashMap<>();
        // 遍历数组，存储位置并判断是否满足要求
        for (int i = 0; i < nums.length; i++) {
			if (data.containsKey(nums[i])) {
				// 当前数字上一次出现的位置信息
				int loc = data.get(nums[i]);
				if (i - loc <= k) {
					// 满足条件，返回真
					return true;
				} else {
					// 不满足条件，存储当前位置，继续循环
					data.put(nums[i], i);
				}
			} else {
				// 记录当前数字出现的位置
				data.put(nums[i], i);
			}
		}
        // 循环结束还没有满足条件的，返回假
        return false;
    }
}

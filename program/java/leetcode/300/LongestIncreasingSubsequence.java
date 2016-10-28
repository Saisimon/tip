package net.saisimon.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>300. Longest Increasing Subsequence</h1>
 * 	<p>Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * 	<p>For example:
 * 		<blockquote>
 * 			Given [10, 9, 2, 5, 3, 7, 101, 18] <br>
 * 			The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * 		</blockquote>
 * 
 * 	<p>Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * 
 * 	<p>Your algorithm should run in O(n ^ 2) complexity.
 * 
 * 	<p>Follow up: 
 * 		<blockquote>
 * 			Could you improve it to O(n log n) time complexity?
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class LongestIncreasingSubsequence {
	
	/*
	 * 利用二分查找，找到当前数应当插入到 dp 数组中的哪一位
	 * 
	 * O(n log n)
	 */
	public int lengthOfLISA(int[] nums) {
		int[] dp = new int[nums.length];
        int len = 0;
        for (int x : nums) {
        	// 二分查找
            int i = Arrays.binarySearch(dp, 0, len, x);
            // 插入的位置
            if(i < 0) {
            	i = -(i + 1);
            }
            dp[i] = x;
            if(i == len) {
            	len++;
            }
        }
        return len;
	}
	
	@Deprecated
	private int[][] subArrays;
	/*
	 * 动态规划-递归求解
	 * 子问题重复过多
	 * 时间复杂度过高
	 */
	@Deprecated
	public int lengthOfLISB(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		subArrays = new int[nums.length - 1][];
		for (int i = nums.length - 1; i > 0; i--) {
			int len = nums.length - 1;
			int tmp = i;
			while (tmp > 0) {
				if (subArrays[tmp - 1] == null) {
					subArrays[tmp - 1] = new int[tmp];
				}
				subArrays[--len][tmp - 1] = nums[i];
				tmp--;
			}
		}
		Map<String, Integer> data = new HashMap<>();
		return lis(nums, Integer.MIN_VALUE, data);
    }
	
	@Deprecated
	private int lis(int[] nums, int num, Map<String, Integer> data) {
		if (nums.length == 0) {
			return 0;
		}
		String key = nums.toString() + num;
		if (data.containsKey(key)) {
			return data.get(key);
		}
		int[] tmp;
		if (nums.length - 2 >= 0) {
			tmp = subArrays[nums.length - 2];
		} else {
			tmp = new int[0];
		}
		int val = 0;
		if (nums[0] <= num) {
			val = lis(tmp, num, data);
		} else {
			val = Math.max(lis(tmp, num, data), lis(tmp, nums[0], data) + 1);
		}
		data.put(key, val);
		return val;
	}
}

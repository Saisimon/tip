package net.saisimon.leetcode;

/**
 * <h1>268. Missing Number</h1>
 * 	<p>Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * 
 * 	<p>For example:
 * 		<br>&nbsp; Given nums = [0, 1, 3] return 2.
 * 
 * 	<p>Note:
 * 		<br>&nbsp; Your algorithm should run in linear runtime complexity. 
 * 		<br>&nbsp; Could you implement it using only constant extra space complexity?
 * 
 * @author Saisimon
 *
 */
public class MissingNumber {
	public int missingNumberA(int[] nums) {
		// 前 n 项和
		int sum = nums.length * (nums.length + 1) / 2;
		// 依次减去数组中的元素，最后就得到了缺失的值
        for (int i = 0; i < nums.length; i++) {
			sum -= nums[i];
		}
        return sum;
    }
	
	// 使用异或门的特性，n ^ n = 0; n ^ 0 = n;
	public int missingNumberB(int[] nums) {
		int len = nums.length;
		for (int i = 0; i < nums.length; i++) {
			len ^= i;
			len ^= nums[i];
		}
		return len;
    }
}

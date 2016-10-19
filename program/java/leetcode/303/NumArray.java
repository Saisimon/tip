package net.saisimon.leetcode;

/**
 * 303. Range Sum Query - Immutable
 * 	Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * 	
 * 	Example:
 * 		Given nums = [-2, 0, 3, -5, 2, -1]
 * 		sumRange(0, 2) -> 1
 * 		sumRange(2, 5) -> -1
 * 		sumRange(0, 5) -> -3
 * 		
 * 	Note:
 * 		You may assume that the array does not change.
 * 		There are many calls to sumRange function.
 * 
 * @author Saisimon
 *
 */
public class NumArray {
	
	int[] nums;
	
	public NumArray(int[] nums) {
		// 依次计算前几个数的和
        for (int i = 1; i < nums.length; i++) {
			nums[i] += nums[i - 1];
		}
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
    	// i 到 j 的和等于 前 j 个数的和减去前 i - 1 个数的和
    	return i == 0 ? nums[j] : nums[j] - nums[i - 1];
    }
}

//Your NumArray object will be instantiated and called as such:
//NumArray numArray = new NumArray(nums);
//numArray.sumRange(0, 1);
//numArray.sumRange(1, 2);

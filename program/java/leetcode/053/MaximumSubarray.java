package net.saisimon.leetcode;

/**
 * <h1>53. Maximum Subarray</h1>
 * 	<p>Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * 
 * 	<p>For example, given the array [-2,1,-3,4,-1,2,1,-5,4]
 * 	
 * 	<p>the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * @author Saisimon
 *
 */
public class MaximumSubarray {
	
	private int[][] subArrays;
	
	/*
	 * 动态规划-递归求解
	 * 时间复杂度过高
	 */
	@Deprecated
	public int maxSubArray(int[] nums) {
		// 存放数组的所有子数组，方便递归时获取，避免重复截取子数组
		subArrays = new int[nums.length - 1][];
		int len = nums.length - 1;
		for (int i = nums.length - 1; i > 0; i--) {
			int tmp = i;
			while (tmp > 0) {
				if (subArrays[tmp - 1] == null) {
					subArrays[tmp - 1] = new int[tmp];
				}
				subArrays[--len][tmp - 1] = nums[i];
				tmp--;
			}
		}
		// 递归求解
        return max(nums, 0);
    }
	
	/**
	 * @param nums 数组
	 * @param select 上次选择的状态，0 代表上次没有选择、1代表上次选择、2代表已经选择完成
	 * @return 返回最大子数组的和
	 */
	@Deprecated
	private int max(int[] nums, int select) {
		// 空数组直接返回
		if (nums.length == 0) {
			return 0;
		}
		// 数组长度为 1 ，并且还没有选择过，则直接返回
		if (nums.length == 1 && select == 0) {
			return nums[0];
		}
		// 从前往后得到子数组
		int[] tmp;
		if (nums.length - 2 >= 0) {
			tmp = subArrays[nums.length - 2];
		} else {
			tmp = new int[0];
		}
        if (select == 0) {
        	// 上次没有选择，在这次没有选择与选择了当前数的结果进行比较
        	return Math.max(max(tmp, 0), nums[0] + max(tmp, 1));
        } else if (select == 1){
        	// 上次已经选择，在这次没有选择与继续选择当前数的结果进行比较
        	return Math.max(max(tmp, 2), nums[0] + max(tmp, 1));
        } else {
        	// 上次已经选择完成，返回 0
        	return 0;
        }
	}
	
	/*
	 * nums = {-2,1,-3,4,-1,2,1,-5,4}
	 * tmp = {-2 1 -2 4 3 5 6 1 5}
	 */
	public int maxSubArrayB(int[] nums) {
		// 空数组直接返回 0 
		if (nums.length == 0) {
			return 0;
		}
		// 存放选取每个数后的和
		int[] tmp = new int[nums.length];
		tmp[0] = nums[0];
		// 最大值
		int max = tmp[0];
		for (int i = 1; i < tmp.length; i++) {
			// 当前数与前几项的和
			tmp[i] = nums[i] + (tmp[i - 1] > 0 ? tmp[i - 1] : 0);
			// 获取最大值
			max = Math.max(max, tmp[i]);
		}
		return max;
    }
}

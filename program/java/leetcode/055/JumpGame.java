package net.saisimon.leetcode;

/**
 * <h1>55. Jump Game</h1>
 * 	<p>Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * 	
 * 	<p>Each element in the array represents your maximum jump length at that position.
 * 
 * 	<p>Determine if you are able to reach the last index.
 * 	
 * 	<p>For example:
 * 		<br>&nbsp; A = [2,3,1,1,4], return true.
 * 		<br>&nbsp; A = [3,2,1,0,4], return false.
 * 
 * @author Saisimon
 *
 */
public class JumpGame {
	
	// 递归正向求解
	public boolean canJumpA(int[] nums) {
		if (null == nums) {
			return false;
		}
		// 记录当前位置的是否能跳转至末尾
		Boolean[] ress = new Boolean[nums.length];
        return find(nums, 0, ress);
    }
	
	private boolean find(int[] nums, int start, Boolean[] ress) {
		int head = start;
		// 当前跳转数为1 或者 当前跳转数与下一个跳转数相差1时，下一个跳转数与当前跳转数的作用一样，向前移动
		while (head < nums.length - 1 && ((nums[head] == 1) || (nums[head] - nums[head + 1] == 1))) {
			head++;
		}
		if (head >= nums.length) {
			return false;
		}
		if (ress[head] != null) {
			return ress[head];
		}
		if (head + nums[head] >= nums.length - 1) {
			return true;
		}
		for (int i = nums[head]; i > 0; i--) {
			boolean res = find(nums, head + i, ress);
			ress[head + i] = res;
			if (res) {
				return true;
			}
		}
		return false;
	}
	
	// 遍历倒序求解
	public boolean canJumpB(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int target = nums.length - 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (i + nums[i] >= target) {
				target = i;
			}
		}
		return target == 0;
	}
	
}

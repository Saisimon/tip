package net.saisimon.leetcode;

/**
 * 189. Rotate Array
 * 	Rotate an array of n elements to the right by k steps.
 * 	
 * 	For example:
 * 		with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * 	
 * 	Note:
 * 		Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * 
 * @author Saisimon
 *
 */
public class RotateArray {
	public void rotateA(int[] nums, int k) {
		if (nums.length <= 1) {
			return;
		}
		k %= nums.length;
		int[] tmp = new int[nums.length];
		System.arraycopy(nums, 0, tmp, 0, nums.length);
        for (int i = 0; i < nums.length; i++) {
        	// 计算旋转之后的各个位置应该是哪个元素
			nums[(k + nums.length + i) % nums.length] = tmp[i];
		}
    }
	
	public void rotateB(int[] nums, int k) {
		if (nums.length <= 1) {
			return;
		}
		// step each time to move
		int step = k % nums.length;
		// find GCD between nums length and step
		int gcd = findGcd(nums.length, step);
		int position, count;

		// gcd path to finish movie
		for (int i = 0; i < gcd; i++) {
			// beginning position of each path
			position = i;
			// count is the number we need swap each path
			count = nums.length / gcd - 1;
			for (int j = 0; j < count; j++) {
				position = (position + step) % nums.length;
				// swap index value in index i and position
				nums[i] ^= nums[position];
				nums[position] ^= nums[i];
				nums[i] ^= nums[position];
			}
		}
	}
	
	private int findGcd(int a, int b){
        return (a == 0 || b == 0) ? a + b : findGcd(b, a % b);
    }
	
	public void rotateC(int[] nums, int k) {
	    k %= nums.length;
	    reverse(nums, 0, nums.length - 1);
	    reverse(nums, 0, k - 1);
	    reverse(nums, k, nums.length - 1);
	}

	private void reverse(int[] nums, int start, int end) {
	    while (start < end) {
	        int temp = nums[start];
	        nums[start] = nums[end];
	        nums[end] = temp;
	        start++;
	        end--;
	    }
	}
}

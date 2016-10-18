package net.saisimon.leetcode;

/**
 * 27. Remove Element
 * 	Given an array and a value, remove all instances of that value in place and return the new length.
 * 	Do not allocate extra space for another array, you must do this in place with constant memory.
 * 	The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * 	
 * 	Example:
 * 		Given input array nums = [3,2,2,3], val = 3
 * 		Your function should return length = 2, with the first two elements of nums being 2.
 * 
 * @author Saisimon
 *
 */
public class RemoveElement {
	public int removeElementA(int[] nums, int val) {
		int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
			while (nums[i] == val && i <= j) {
				swap(nums, i, j);
				j--;
			}
		}
        return j + 1;
    }
	
	private void swap(int[] nums, int a, int b) {
		if (a == b) {
			return;
		}
		nums[a] = nums[a] ^ nums[b];
		nums[b] = nums[b] ^ nums[a];
		nums[a] = nums[a] ^ nums[b];
	}
	
	public int removeElementB(int[] nums, int val) {
		int m = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[m] = nums[i];
				m++;
			}
		}
		return m;
	}
}

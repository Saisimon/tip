package net.saisimon.leetcode;

/**
 * <h1>485. Max Consecutive Ones</h1>
 * 	<p>Given a binary array, find the maximum number of consecutive 1s in this array.
 * 
 * 	<p>Example 1:
 * 		<blockquote>
 * 		Input: [1,1,0,1,1,1] </br>
 * 		Output: 3 </br>
 * 		Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * 		</blockquote>		
 * 
 * 	<p>Note:
 * 		<blockquote>
 * 		The input array will only contain 0 and 1. </br>
 * 		The length of input array is a positive integer and will not exceed 10,000
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class MaxConsecutiveOnes {
	
	public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] != 0) {
        		nums[i] = nums[i - 1] + 1;
        	} else if (nums[i - 1] > max) {
        		max = nums[i - 1];
        	}
		}
        if (nums != null && nums.length > 0 && nums[nums.length - 1] > max) {
        	max = nums[nums.length - 1];
        }
        return max;
    }
	
}

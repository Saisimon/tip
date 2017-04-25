package net.saisimon.leetcode;

import java.util.Arrays;

/**
 * <h1>561. Array Partition I</h1>
 * 	<p>Given an array of 2n integers, your task is to group these integers into n pairs of integer, 
 * 	say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 * 
 * 	<p>Example 1:
 * 		<br>&nbsp; Input: [1,4,3,2]
 * 		<br>&nbsp; Output: 4
 * 		<br>&nbsp; Explanation: n is 2, and the maximum sum of pairs is 4.
 * 
 * 	<p>Note:
 * 		<br>&nbsp; n is a positive integer, which is in the range of [1, 10000].
 * 		<br>&nbsp; All the integers in the array will be in the range of [-10000, 10000].
 * 
 * 	<p>[1,4,3,2] -> [1,2],[3,4] -> 1 + 3 -> 4
 * 
 * @author Saisimon
 *
 */
public class ArrayPartition {
	
	public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i += 2) {
        	result += nums[i];
        }
        return result;
    }
	
}

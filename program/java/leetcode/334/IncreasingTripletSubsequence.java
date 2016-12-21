package net.saisimon.leetcode;

/**
 * <h1>334. Increasing Triplet Subsequence</h1>
 * 	<p>Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * 
 * 	<p>Formally the function should:
 * 		Return true if there exists i, j, k 
 * 		such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * 
 * 	<p>Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * 
 * 	<p>Examples:
 * 		<blockquote>
 * 		Given [1, 2, 3, 4, 5], </br>
 * 		return true. </br>
 *		</br>
 * 		Given [5, 4, 3, 2, 1], </br>
 * 		return false.
 * 		</blockquote>	
 * 
 * @author Saisimon
 *
 */
public class IncreasingTripletSubsequence {
	
	public boolean increasingTriplet(int[] nums) {
		if (nums == null || nums.length < 3) {
			return false;
		}
		// 第一个数
        int f = Integer.MAX_VALUE;
        // 第二个数
        int s = Integer.MAX_VALUE;
        for (int i : nums) {
        	if (i <= f) {
        		f = i;
        	} else if (i < s) {
        		s = i;
        	} else if (i > s) {
        		// 存在比两个数都大的数，则存在三个递增序列
        		return true;
        	}
		}
        // 否则不存在这个序列
        return false;
    }
	
}

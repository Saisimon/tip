package net.saisimon.leetcode;

/**
 * <h1>367. Valid Perfect Square</h1>
 * 	<p>Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * 
 * 	<p>Note: Do not use any built-in library function such as sqrt.
 * 
 * 	<p>Example 1:
 * 		<blockquote>
 * 			Input: 16 <br>
 * 			Returns: True
 * 		</blockquote>
 * 
 * 	<p>Example 2:
 * 		<blockquote>
 * 			Input: 14 <br>
 * 			Returns: False
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class ValidPerfectSquare {
	// 二分查找
	public boolean isPerfectSquare(int num) {
		if (num == 1 || num == 4) {
			return true;
		}
        int start = 1;
        int end = num / 2;
        while (start < end) {
        	int middle = start + (end - start) / 2;
        	long tmp = 1L * middle * middle;
        	if (tmp > num) {
        		end = middle;
        	} else if (tmp < num) {
        		start = middle + 1;
        	} else {
        		return true;
        	}
        }
        return false;
    }
}

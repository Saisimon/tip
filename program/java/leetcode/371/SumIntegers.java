package net.saisimon.leetcode;

/**
 * 371. Sum of Two Integers
 * 	Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * 	Example:
 * 	Given a = 1 and b = 2, return 3.
 * 
 * @author Saisimon
 *
 */
public class SumIntegers {
	public int getSum(int a, int b) {
        if (b == 0) {
        	return a;
        }
        int sum = a ^ b;
        int tmp = (a & b) << 1;
        return getSum(sum, tmp);
    }
	
}

package net.saisimon.leetcode;

/**
 * 231. Power of Two
 * 	Given an integer, write a function to determine if it is a power of two.
 * 
 * @author Saisimon
 *
 */
public class PowerTwo {
	public boolean isPowerOfTwo(int n) {
		return n > 0 && Math.pow(2, 32) % n == 0;
    }
}

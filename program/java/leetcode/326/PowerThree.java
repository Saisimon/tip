package net.saisimon.leetcode;

/**
 * 326. Power of Three
 * 	Given an integer, write a function to determine if it is a power of three.
 * 	1 3 9 27 81...
 * 	Follow up:
 * 		Could you do it without using any loop / recursion?
 * 
 * @author Saisimon
 *
 */
public class PowerThree {
	public boolean isPowerOfThreeA(int n) {
		if (n == 1) {
			return true;
		}
		return check(n);
    }
	
	private boolean check(double n) {
		if (n < 3) {
        	return false;
        } else if (n == 3) {
        	return true;
        } else {
        	return check(n / 3);
        }
	}
	
	public boolean isPowerOfThreeB(int n) {
		// 3 ^ 20 > Integer.MAX_VALUE
		return n > 0 && Math.pow(3, 19) % n == 0;
	}
}

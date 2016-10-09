package net.saisimon.leetcode;

/**
 * 7. Reverse Integer
 * 	Reverse digits of an integer.
 * 	Example1: 
 * 		x = 123, return 321
 * 	Example2: 
 * 		x = -123, return -321
 * 
 * @author Saisimon
 *
 */
public class ReverseInteger {
	public int reverseA(int x) {
		long res = 0;
		while (x != 0) {
			res = res * 10 + x % 10;
			x = x / 10;
		}
		if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
			return 0;
		}
		return (int) res;
    }
	
	public int reverseB(int x) {
		StringBuilder sb = new StringBuilder();
		if (x < 0) {
			if (x == Integer.MIN_VALUE) {
				return 0;
			}
			sb.append(-x);
			sb.reverse();
			sb.insert(0, '-');
		} else {
			sb.append(x);
			sb.reverse();
		}
		long res = Long.parseLong(sb.toString());
		if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
			return 0;
		}
		return (int) res;
    }
}

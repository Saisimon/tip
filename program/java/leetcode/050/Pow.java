package net.saisimon.leetcode;

/**
 * <h1>50. Pow(x, n)</h1>
 * 	<p>Implement pow(x, n).
 * 
 * @author Saisimon
 *
 */
public class Pow {
	
	// Math.pow(x, n);
	public double myPowB(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (x == 0) {
			return 0;
		}
		int temp = Math.abs(n);
		if (n == Integer.MIN_VALUE) {
			temp = Integer.MAX_VALUE;
		}
		double res = n % 2 == 0 ? myPowB(x * x, temp / 2) : x * myPowB(x * x, temp / 2);
		return n > 0 ? res : (1 / res);
    }
	
}

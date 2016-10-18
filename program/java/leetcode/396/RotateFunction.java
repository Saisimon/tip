package net.saisimon.leetcode;

/**
 * 396. Rotate Function
 * 	Given an array of integers A and let n to be its length.
 * 
 * 	Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:
 * 		F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 * 
 * 	Calculate the maximum value of F(0), F(1), ..., F(n-1).
 * 
 * 	Note:
 * 		n is guaranteed to be less than 10 ^ 5.
 * 
 * 	Example:
 * 		A = [4, 3, 2, 6]
 * 		F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * 		F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * 		F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * 		F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 
 * 		So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 * 
 * @author Saisimon
 *
 */
public class RotateFunction {
	/*
	 * 
	 *	F(0) = 0 * A + 1 * B + 2 * C + 3 * D
	 * 	F(1) = 0 * D + 1 * A + 2 * B + 3 * C = 1 * A + 2 * B + 3 * C + 0 * D
	 * 	F(1) - F(0) = A + B + C - 3 * D = A + B + C + D - 4 * D = Sum(Array) - Array.length * D
	 * 
	 * 	F(1) = 0 * D + 1 * A + 2 * B + 3 * C
	 * 	F(2) = 0 * C + 1 * D + 2 * A + 3 * B = 1 * D + 2 * A + 3 * B + 0 * C
	 * 	F(2) - F(1) = A + B + C - 3 * C = A + B + C + D - 4 * C = Sum(Array) - Array.length * C
	 * 
	 * 	F(k) - F(k - 1) = Sum(Array) - Array.length * Array[Array.length - k];
	 * 
	 */
	public int maxRotateFunction(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		// 求出第一组数据的和与数组的和
		int f = 0;
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			f += i * A[i];
			sum += A[i];
		}
		int max = f;
		// 根据规律，找出最大值
		for (int i = A.length - 1; i > 0; i--) {
			f = f + sum - A.length * A[i];
			max = Math.max(f, max);
		}
        return max;
    }
}

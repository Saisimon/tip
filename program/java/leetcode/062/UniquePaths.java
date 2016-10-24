package net.saisimon.leetcode;

/**
 * <h1>62. Unique Paths</h1>
 * 	<p>A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * 	
 * 	<p>The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * 	
 * 	<blockquote>
 * 		  R 	X	 X	  X	   X     X <br>
 * 		  X		X	 X	  X	   X     X <br>
 * 		  X		X	 X	  X    X   	 F <br>
 * 		<br>
 * 		R : Robot F : Finish
 * 	</blockquote>
 * 
 * 	<p>How many possible unique paths are there?
 * 	
 * 	<p>Note: m and n will be at most 100.
 * 
 * @author Saisimon
 *
 */
public class UniquePaths {
	/*
	 * f(m, n) = f(m - 1, n) + f(m, n - 1);
	 */
	public int uniquePathsA(int m, int n) {
		if (n <= 0 || m <= 0) {
			return 0;
		}
		int[][] tmp = new int[m][n];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				if (i == 0 || j == 0) {
					tmp[i][j] = 1;
				} else {
					tmp[i][j] = tmp[i - 1][j] + tmp[i][j - 1];
				}
			}
		}
        return tmp[m - 1][n - 1];
    }
	
	/*
	 * 递归
	 * 时间复杂度过高
	 */
	@Deprecated
	public int uniquePathsB(int m, int n) {
		if (n <= 0 || m <= 0) {
			return 0;
		}
		if (n == 1 || m == 1) {
			return 1;
		}
		return uniquePathsB(m - 1, n) + uniquePathsB(m, n - 1);
    }
}

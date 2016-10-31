package net.saisimon.leetcode;

/**
 * <h1>59. Spiral Matrix II</h1>
 * 	<p>Given an integer n, generate a square matrix filled with elements from 1 to n ^ 2 in spiral order.
 * 
 * 	<p>For example:
 * 		<blockquote>
 * 			Given n = 3, <br>
 * 			You should return the following matrix: <br>
 * 			[ <br>
 * 				[ 1, 2, 3 ], <br>
 * 				[ 8, 9, 4 ], <br>
 * 				[ 7, 6, 5 ] <br>
 *			]
 *		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class SpiralMatrixTwo {
	/*
	 * 1   2  3  4 	
	 * 12 13 14  5	
	 * 11 16 15  6 
	 * 10  9  8  7
	 */
	public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int idx = 1;
        int x = 0;
        int y = 0;
        // 方向
        int direct = 1;
        while (idx <= n * n) {
        	res[x][y] = idx;
        	if (direct % 4 == 1) {
        		y++;
        	} else if (direct % 4 == 2) {
        		x++;
        	} else if (direct % 4 == 3) {
        		y--;
        	} else if (direct % 4 == 0) {
        		x--;
        	}
        	if (y == n - 1 - direct / 4 && x == direct / 4) {
        		direct ++;
        	} else if (y == direct / 4 && x == n - 1 - direct / 4) {
        		direct ++;
        	} else if (x == n - 1 - direct / 4 && y == n - 1 - direct / 4) {
        		direct ++;
        	} else if (x == direct / 4 && y == direct / 4 - 1) {
        		direct ++;
        	}
        	idx++;
        }
        return res;
    }
}

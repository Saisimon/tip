package net.saisimon.leetcode;

/**
 * <h1>74. Search a 2D Matrix</h1>
 * 	<p>Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * 		<blockquote>
 * 			Integers in each row are sorted from left to right. <br>
 * 			The first integer of each row is greater than the last integer of the previous row.
 * 		</blockquote>
 * 
 * 	<p>For example:
 * 		<blockquote>
 * 			Consider the following matrix: <br>
 * 			[ <br>
 * 				[1,   3,  5,  7], <br>
 * 				[10, 11, 16, 20], <br>
 * 				[23, 30, 34, 50] <br>
 * 			] <br>
 * 			Given target = 3, return true.
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class Search2DMatrix {
	/*
	 * 二分查找
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
        	return false;
        }
        int n = matrix[0].length;
        if (n == 0) {
        	return false;
        }
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
        	return false;
        }
        int start = 0;
        int end = m - 1;
        while (start <= end) {
        	int middle = start + (end - start) / 2;
        	if (matrix[middle][0] > target) {
        		end = middle - 1;
        	} else if (matrix[middle][0] < target) {
        		start = middle + 1;
        	} else {
        		return true;
        	}
        }
        int s = 0;
        int e = n - 1;
        while (s <= e) {
        	int x = s + (e - s) / 2;
        	if (matrix[start - 1][x] > target) {
        		e = x - 1;
        	} else if (matrix[start - 1][x] < target) {
        		s = x + 1;
        	} else {
        		return true;
        	}
        }
        return false;
    }
}

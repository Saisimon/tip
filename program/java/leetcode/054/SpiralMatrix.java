package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>54. Spiral Matrix</h1>
 * 	<p>Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * 	<p>For example,
 * 		<br>&nbsp; Given the following matrix:
 * 		<br>&nbsp; [
 *			<br>&nbsp;&nbsp; [ 1, 2, 3 ],
 * 			<br>&nbsp;&nbsp; [ 4, 5, 6 ],
 *			<br>&nbsp;&nbsp; [ 7, 8, 9 ]
 *		<br>&nbsp; ]
 *		<br>&nbsp;You should return [1,2,3,6,9,8,7,4,5].
 * 
 * @author Saisimon
 *
 */
public class SpiralMatrix {
	
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if (matrix != null && matrix.length != 0) {
			int n = matrix.length;
			if (matrix[0] == null || matrix[0].length == 0) {
				return res;
			} else if (matrix.length == 1 && matrix[0].length == 1) {
				res.add(matrix[0][0]);
				return res;
			}
			int m = matrix[0].length;
	        int idx = 1;
	        int x = 0;
	        int y = 0;
	        // 方向
	        int direct = 1;
	        while (idx <= n * m) {
	        	if (direct % 4 == 1 && y < m - 1) {
	        		res.add(matrix[x][y]);
	        		y++;
	        		idx++;
	        	} else if (direct % 4 == 2 && x < n - 1) {
	        		res.add(matrix[x][y]);
	        		x++;
	        		idx++;
	        	} else if (direct % 4 == 3 && y > 0) {
	        		res.add(matrix[x][y]);
	        		y--;
	        		idx++;
	        	} else if (direct % 4 == 0 && x > 0) {
	        		res.add(matrix[x][y]);
	        		x--;
	        		idx++;
	        	}
	        	if (y == m - 1 - direct / 4 && x == direct / 4) {
	        		direct++;
	        	} else if (y == direct / 4 && x == n - 1 - direct / 4 && x != direct / 4) {
	        		direct++;
	        	} else if (x == n - 1 - direct / 4 && y == m - 1 - direct / 4 && y != direct / 4 - 1) {
	        		direct++;
	        	} else if (x == direct / 4 && y == direct / 4 - 1) {
	        		direct++;
	        	}
	        }
		}
        return res;
    }
	
}

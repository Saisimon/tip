package net.saisimon.leetcode;

/**
 * <h1>240. Search a 2D Matrix II</h1>
 * 	<p>Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * 		<blockquote>
 * 			Integers in each row are sorted in ascending from left to right. <br>
 * 			Integers in each column are sorted in ascending from top to bottom.
 * 		</blockquote>
 * 
 * 	<p>For example:
 * 		<blockquote>
 * 			Consider the following matrix: <br>
 * 			[ <br>
 * 				[1,   4,  7, 11, 15], <br>
 * 				[2,   5,  8, 12, 19], <br>
 * 				[3,   6,  9, 16, 22], <br>
 * 				[10, 13, 14, 17, 24], <br>
 * 				[18, 21, 23, 26, 30] <br>
 * 			] <br>
 * 			Given target = 5, return true. <br>
 * 			Given target = 20, return false.
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class Search2DMatrixTwo {
	// 二分查找
	public boolean searchMatrixA(int[][] matrix, int target) {
		if(matrix == null) {
			return false;
		}
        int m = matrix.length;
        if (m == 0) {
        	return false;
        }
        int n = matrix[m - 1].length;
        if (n == 0) {
        	return false;
        }
        // target 小于最小值或大于最大值，则直接返回假
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
        	return false;
        }
        int x = Math.min(m, n);
        int i = 0;
        // 遍历对角线
        for (; i < x; i++) {
			if (matrix[i][i] > target) {
				break;
			} else if (matrix[i][i] == target) {
				return true;
			}
		}
        int idx = i;
        // 从 i 开始使用二分查找横竖各行或列的数据
        while (idx <= m - 1) {
        	int start = 0;
            int end = i - 1;
        	while (start <= end) {
        		int middle = start + (end - start) / 2;
        		if (matrix[idx][middle] < target) {
        			start = middle + 1;
        		} else if (matrix[idx][middle] > target) {
        			end = middle - 1;
        		} else {
        			return true;
        		}
        	}
        	idx++;
        }
        idx = i;
        while (idx <= n - 1) {
        	int start = 0;
            int end = i - 1;
        	while (start <= end) {
        		int middle = start + (end - start) / 2;
        		if (matrix[middle][idx] < target) {
        			start = middle + 1;
        		} else if (matrix[middle][idx] > target) {
        			end = middle - 1;
        		} else {
        			return true;
        		}
        	}
        	idx++;
        }
        return false;
    }
	
	// 从左下角开始，根据大小来移动指针的位置，直至找到指定值
	public boolean searchMatrixB(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		if (matrix[0] == null || matrix[0].length == 0) {
			return false;
		}
		int m = matrix.length - 1;
		int n = matrix[0].length - 1;
		int x = 0;
		int y = n;
		while (x <= m && y >= 0) {
			if (matrix[x][y] == target) {
				return true;
			} else if (matrix[x][y] < target) {
				x++;
			} else {
				y--;
			}
		}
		return false;
	}
}

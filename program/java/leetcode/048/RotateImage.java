package net.saisimon.leetcode;

/**
 * <h1>48. Rotate Image</h1>
 * 	<p>You are given an n x n 2D matrix representing an image.
 * 
 * 	<p>Rotate the image by 90 degrees (clockwise).
 * 
 * 	<p>Follow up:
 * 		<blockquote>
 * 			Could you do this in-place?
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class RotateImage {
	/*
	 *  空间复杂度 O(1)
	 *  
	 *  原理：顺时针旋转 90 度，即先按左上角到右下角的对角线进行镜像，再按照中轴线进行镜像
	 *  
	 *  1 2 3 4	   1 5 4 8    8 4 5 1
	 * 	5 6 7 8 -> 2 6 3 7 -> 7 3 6 2
	 * 	4 3 2 1    3 7 2 6    6 2 7 3
	 *	8 7 6 5    4 8 1 5    5 1 8 4
	 */
	public void rotate(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < i; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			int s = 0;
			int e = matrix[i].length - 1;
			while (s <= e) {
				int tmp = matrix[i][s];
				matrix[i][s] = matrix[i][e];
				matrix[i][e] = tmp;
				s++;
				e--;
			}
		}
    }
	
	/*
	 * 顺时针旋转 90 度，即 m[i][j] = m[length - 1 - j][i]
	 */
	public void rotateB(int[][] matrix) {
		int[][] tmp = new int[matrix.length][matrix.length];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				tmp[i][j] = matrix[i][j];
			}
		}
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				matrix[i][j] = tmp[tmp[i].length - 1 - j][i];
			}
		}
    }
}

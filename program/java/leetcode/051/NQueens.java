package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>51. N-Queens</h1>
 * 	<p>The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * 
 * 	<p>Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 	
 * 	<p>Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * 
 * 	<p>For example,
 * 		<br>&nbsp; There exist two distinct solutions to the 4-queens puzzle:
 * 		<br>&nbsp; [
 *			<br>&nbsp;&nbsp; [".Q..",  // Solution 1
 *			<br>&nbsp;&nbsp;  "...Q",
 * 			<br>&nbsp;&nbsp;  "Q...",
 *			<br>&nbsp;&nbsp;  "..Q."],
 *			<br>&nbsp; 
 *			<br>&nbsp;&nbsp; ["..Q.",  // Solution 2
 *			<br>&nbsp;&nbsp;  "Q...",
 *			<br>&nbsp;&nbsp;  "...Q",
 *			<br>&nbsp;&nbsp;  ".Q.."]
 *		<br>&nbsp; ]
 * 
 * @author Saisimon
 *
 */
public class NQueens {
	
	// 递归回溯
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		find(res, n, new int[n][n], 0, 0);
		return res;
    }
	
	private void find(List<List<String>> res, int n, int[][] temp, int row, int column) {
		while (column < n) {
			if (temp[row][column] == 0) {
				int[][] arr = new int[temp.length][];
				int position = change(n, temp, arr, row, column, (row + 1) % n);
				if (position != -1) {
					find(res, n, arr, (row + 1) % n, position);
				} else {
					calcArray(res, arr);
				}
			}
			column++;
		}
	}
	
	private void calcArray(List<List<String>> res, int[][] temp) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			boolean exists = false;
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < temp[i].length; j++) {
				if (temp[i][j] == 8) {
					sb.append('Q');
					exists = true;
				} else {
					sb.append('.');
				}
			}
			if (!exists) {
				return;
			}
			list.add(sb.toString());
		}
		res.add(list);
	}

	private int change(int n, int[][] temp, int[][] arr, int row, int column, int line) {
		int position = -1;
		for (int i = 0; i < temp.length; i++) {
			int[] a = new int[temp[i].length];
			for (int j = 0; j < temp[i].length; j++) {
				if (Math.abs(row - i) == Math.abs(column - j) || row == i || column == j) {
					a[j] = temp[i][j] + 1;
				} else {
					a[j] = temp[i][j];
				}
				arr[i] = a;
				if (line == i && a[j] == 0 && position == -1) {
					position = j;
				}
			}
		}
		arr[row][column] += 7;
		return position;
	}
	
}

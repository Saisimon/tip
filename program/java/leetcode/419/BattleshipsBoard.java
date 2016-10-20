package net.saisimon.leetcode;

/**
 * 419. Battleships in a Board
 * 	Given an 2D board, count how many different battleships are in it. 
 * 	The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * 		You receive a valid board, made of only battleships or empty slots.
 * 		Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * 		At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * 	
 * 	Example:
 * 			X..X
 * 			...X
 * 			...X
 * 		In the above board there are 2 battleships.
 * 
 * 			X..X
 * 			.X.X
 * 			X..X
 * 		In the above board there are 4 battleships.
 * 	
 * 	Invalid Example:
 * 			...X
 * 			XXXX
 * 			...X
 * 		This is not a valid board - as battleships will always have a cell separating between them.
 * 	
 * 	Your algorithm should not modify the value of the board.
 * 
 * @author Saisimon
 *
 */
public class BattleshipsBoard {
	// 分情况讨论
	public int countBattleships(char[][] board) {
		int res = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				if (i == 0) {
					if (j == 0) { //左上角
						res++;
					} else { // 上边
						if (board[i][j - 1] == '.') {
							res++;
						}
					}
				} else if (i != board.length - 1 && j != 0 && j != board[i].length - 1) {
					if (board[i - 1][j] == '.' && board[i + 1][j] == '.' && board[i][j - 1] == '.' && board[i][j + 1] == '.') {
						res++;
					} else if (board[i - 1][j] == '.' && board[i + 1][j] == 'X') {
						res++;
					} else if (board[i][j - 1] == '.' && board[i][j + 1] == 'X') {
						res++;
					}
				} else {
					if (j == 0) { // 左下角
						if (board[i - 1][j] == '.') {
							res++;
						}
					} else { // 下边
						if (board[i - 1][j] == '.' && board[i][j - 1] == '.') {
							res++;
						}
					}
				}
			}
		}
		return res;
    }
}

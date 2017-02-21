package net.saisimon.leetcode;

/**
 * <h1>463. Island Perimeter</h1>
 * 	<p>You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
 * 	<p>Grid cells are connected horizontally/vertically (not diagonally). 
 * 	<p>The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). 
 * 	<p>The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
 * 	<p>One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. 
 * 	<p>Determine the perimeter of the island.
 * 
 * 	<p>Example:
 * 		<blockquote>
 * 		[ </br>
 * 			[0,1,0,0], </br>
 * 			[1,1,1,0], </br>
 * 			[0,1,0,0], </br>
 * 			[1,1,0,0] </br>
 * 		] </br>
 * 		Answer: 16 </br>
 * 		Explanation: The perimeter is the 16 yellow stripes
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class IslandPerimeter {
	
	public int islandPerimeter(int[][] grid) {
		int result = 0;
		// 遍历二维数组
        for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0) {
					continue;
				}
				result += 4;
				// 判断上下左右的值
				if (i - 1 >= 0 && grid[i - 1][j] == 1) {
					result--;
				}
				if (i + 1 < grid.length && grid[i + 1][j] == 1) {
					result--;
				}
				if (j - 1 >= 0 && grid[i][j - 1] == 1) {
					result--;
				}
				if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
					result--;
				}
			}
		}
        return result;
    }
	
}

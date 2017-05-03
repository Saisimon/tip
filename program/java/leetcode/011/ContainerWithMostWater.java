package net.saisimon.leetcode;

/**
 * <h1>11. Container With Most Water</h1>
 * 	<p>Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * 	n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * 	Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * 
 * 	<p>Note: You may not slant the container and n is at least 2.
 * 	
 *  ^
 * 	|
 * 	|
 * 	|        a3
 *  |        |  a4    a6
 *  |  a1    |  |  a5 |
 *  |  |  a2 |  |  |  |  a7
 *  |  |  |  |  |  |  |  |
 *  |  |  |  |  |  |  |  |
 *  +--+--+--+--+--+--+--+-->
 * 
 * @author Saisimon
 *
 */
public class ContainerWithMostWater {
	
	public int maxArea(int[] height) {
		int left = 0;
		int right = height.length - 1;
		int maxArea = 0;
		while (left < right) {
			// 容器能盛多少水取决于宽和最短的高，并非指围成的面积大小
			maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
			if (height[left] < height[right]) {
				left++; // 左边的高小于右边时，向右移动
			} else {
				right--; // 左边的高大与右边时，向左移动
			}
		}
		return maxArea;
    }
	
}

package net.saisimon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>447. Number of Boomerangs</h1>
 * 	<p>Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * 	
 * 	<p>Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
 * 
 * 	<p>Example:
 * 		<blockquote>
 * 		Input: [{0,0},{1,0},{2,0}] </br>
 * 		Output: 2 </br>
 * 		Explanation: The two boomerangs are [{1,0},{0,0},{2,0}] and [{1,0},{2,0},{0,0}]
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class NumberBoomerangs {
	
	// O(n ^ 2)
	public int numberOfBoomerangsA(int[][] points) {
		int res = 0;
		// 保存第i个数与某个数间的距离的数量 key:距离 value：数量
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if (i == j) {
					continue;
				}
				int len = calcLength(points[i], points[j]);
				map.put(len, map.getOrDefault(len, 0) + 1);
			}
			
			for (int count : map.values()) {
				// count 个中选2个
				res += count * (count - 1);
			}
			map.clear();
		}
		return res;
    }
	
	// O(n ^ 3) Test Timeout
	@Deprecated
	public int numberOfBoomerangsB(int[][] points) {
		int res = 0;
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				for (int k = 0; k < points.length; k++) {
					if (i != j && j != k && k != i && calcLength(points[i], points[j]) == calcLength(points[i], points[k])) {
						res++;
					}
				}
			}
		}
		return res;
    }
	
	private int calcLength(int[] p1, int[] p2) {
		int d1 = p2[0] - p1[0];
		int d2 = p2[1] - p1[1];
		return d1 * d1 + d2 * d2;
	}
	
}

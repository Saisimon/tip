package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>279. Perfect Squares</h1>
 * 	<p>Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 *	<p>For example:
 *		<blockquote>
 *		given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 *		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class PerfectSquares {
	
	/*
	 * https://discuss.leetcode.com/topic/24255/summary-of-4-different-solutions-bfs-dp-static-dp-and-mathematics
	 * mathematics
	 * 
	 * 四平方和定理
	 * https://zh.wikipedia.org/wiki/%E5%9B%9B%E5%B9%B3%E6%96%B9%E5%92%8C%E5%AE%9A%E7%90%86
	 * 每个正整数均可表示为4个整数的平方和
	 * 
	 */
	public int numSquaresA(int n) {
		if (n < 1) {
			return 0;
		}
		if (isSqrt(n)) {
			return 1;
		}
		while (n % 4 == 0) {
			n >>= 2;
		}
		if (n % 8 == 7) {
			return 4;
		}
		int sq = (int) Math.sqrt(n);
		for (int i = 1; i <= sq; i++) {
			if (isSqrt(n - i * i)) {
				return 2;
			}
		}
		return 3;
	}
	
	private boolean isSqrt(int n) {
		int sq = (int) Math.sqrt(n);
		return n == sq * sq;
	}
	
	public int numSquaresB(int n) {
		int[] temp = new int[n + 1];
		Arrays.fill(temp, Integer.MAX_VALUE);
		temp[0] = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 1; i + j * j <= n; j++) {
				temp[i + j * j] = Math.min(temp[i + j * j], temp[i] + 1);
			}
		}
		return temp[n];
	}
	
	@Deprecated
	private Map<Integer, Integer> map = new HashMap<>();
	
	@Deprecated
	public int numSquaresC(int n) {
        if (n < 4) {
        	return n;
        }
        int sq = (int) Math.sqrt(n);
        if (sq * sq == n) {
        	map.put(n, sq);
        	return sq;
        }
        if (map.containsKey(n)) {
        	return map.get(n);
        }
        List<Integer> temp = new ArrayList<>();
        while (sq * sq > 3) {
        	if (sq * sq == n) {
        		return 1;
        	}
        	temp.add(numSquaresC(n - sq * sq) + 1);
        	sq--;
        }
        return temp.stream().min(Integer :: compare).get();
    }
	
}

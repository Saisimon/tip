package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>77. Combinations</h1>
 * 	<p>Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * 	
 * 	<p>For example:
 * 		<blockquote>
 * 		If n = 4 and k = 2, a solution is: </br>
 * 		[ </br>
 *		  [2,4], </br>
 *		  [3,4], </br>
 *		  [2,3], </br>
 *		  [1,2], </br>
 *		  [1,3], </br>
 *		  [1,4], </br>
 *		]
 *		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class Combinations {
	
	// 递归求解 
	public List<List<Integer>> combine(int n, int k) {
        return combine(1, n, k);
    }
	
	/*
	 * 1 -> 5 k = 3
	 * 
	 * 1,2,3      	   2,3          3
	 * 1,2,4      	   2,4          4
	 * 1,2,5  --> 	   2,5  -->     5
	 * 1,3,4  -->           -->
	 * 1,3,5           3,4          
	 * 1,4,5           3,5
	 *                    
	 *                 4,5
	 *                 
	 * 2,3,4          
	 * 2,3,5
	 * 2,4,5
	 * 
	 * 
	 * 3,4,5
	 * 
	 * combine(1,n,k) = i + combine(i + 1, n, k - 1)
	 * 
	 */
	private List<List<Integer>> combine(int start, int end, int len) {
		List<List<Integer>> res = new ArrayList<>();
		if (len == 0 || start > end || end - start + 1 < len) {
			return res;
		}
		if (len == 1) {
			for (int i = start; i <= end; i++) {
				List<Integer> list = new ArrayList<>();
				list.add(0, i);
				res.add(i - start, list);
			}
			return res;
		}
		for (int i = start; i <= end; i++) {
			List<List<Integer>> r = combine(i + 1, end, len - 1);
			for (List<Integer> list : r) {
				list.add(0, i);
			}
			res.addAll(r);
		}
		return res;
	}
	
}

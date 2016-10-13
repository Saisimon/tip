package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 * 	Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * 	For example:
 * 		given numRows = 5,
 * 		Return
 * 	[
 *	     [1],
 *	    [1,1],
 *	   [1,2,1],
 *	  [1,3,3,1],
 *	 [1,4,6,4,1]
 *	]
 * 
 * @author Saisimon
 *
 */
public class PascalsTriangle {
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
			List<Integer> tmp = new ArrayList<>(i);
			for (int j = 0; j < i + 1; j++) {
				if (j - 1 < 0) {
					// 最左
					tmp.add(1);
				} else if (j == i) {
					// 最右
					tmp.add(1);
				} else if (i > 0) {
					List<Integer> pre = res.get(i - 1);
					// f[i][j] = f[i-1][j-1] + f[i-1][j]
					tmp.add(pre.get(j - 1) + pre.get(j));
				}
			}
			res.add(tmp);
		}
        return res;
    }
}

package net.saisimon.leetcode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 * 	Given an index k, return the k th row of the Pascal's triangle.
 * 	For example, given k = 3,
 * 	Return [1,3,3,1].
 * 
 * 	Note:
 * 		Could you optimize your algorithm to use only O(k) extra space?
 * 
 * @author Saisimon
 *
 */
public class PascalsTriangleTwo {
	// f[i][j] = f[i-1][j-1] + f[i-1][j]
	public List<Integer> getRowA(int rowIndex) {
		List<List<Integer>> res = new ArrayList<>(rowIndex);
		List<Integer> tmp;
        for (int i = 0; i <= rowIndex; i++) {
        	tmp = new ArrayList<>(i);
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
        return res.get(rowIndex);
    }
	
	// n! / (k! * (n - k)!)
	public List<Integer> getRowB(int rowIndex) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i <= rowIndex; i++) {
			int tmp = getFactorial(rowIndex).divide((getFactorial(i).multiply(getFactorial(rowIndex - i)))).intValue();
			res.add(tmp);
		}
		return res;
	}
	
	private static BigInteger[] factorials = new BigInteger[100];
	
	static {
		factorials[0] = BigInteger.ONE;
		factorials[1] = BigInteger.ONE;
	}
	
	private BigInteger getFactorial(int n) {
		BigInteger fn = factorials[n];
		if (fn == null) {
			fn =  getFactorial(n - 1).multiply(new BigInteger(n + ""));
			factorials[n] = fn;
		}
		return fn;
	}
}

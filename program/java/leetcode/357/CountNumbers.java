package net.saisimon.leetcode;

/**
 * <h1>357. Count Numbers with Unique Digits</h1>
 * 	<p>Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10 ^ n.
 * 
 * 	<p>Example:
 * 		<br>&nbsp; Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])
 * 	
 * 	<blockquote>
 * 		n = 0 => 1;<br>
 * 		n = 1 => 9 + 1;<br>
 * 		n = 2 => 9 * 9 + 9 + 1 = 81;<br>
 * 		n = 3 => 9 * 9 * 8 + 9 * 9 + 9 + 1 = 739;<br>
 * 		n = 4 => 9 * 9 * 8 * 7 + 9 * 9 * 8 + 9 * 9 + 9 + 1 = 5275;<br>
 * 		...<br>
 * 		n = 11 => 9 * 9 * 8 * 7 * ... * 2 * 1 * 0 + ....<br>
 * 		n = 12 == n = 11<br>
 * 		<br>
 * 		f(n) = f(n - 1) + 9 * 9 * .. * (10 - n)
 * 	</blockquote>
 * 
 * @author Saisimon
 *
 */
public class CountNumbers {
	// 根据以上公式，计算结果
	public int countNumbersWithUniqueDigitsA(int n) {
		if (n < 0) {
			return 0;
		} else if (n > 10) {
			return countNumbersWithUniqueDigitsA(10);
		}
        int idx = 1;
        int res = 1;
        while (idx <= n) {
        	int tmp = 9;
        	for (int i = 9; i > (10 - idx); i--) {
				tmp *= i;
			}
        	res += tmp;
        	idx++;
        }
        return res;
    }
	
	private static final int[] COUNTS = {1,10,91,739,5275,32491,168571,712891,2345851,5611771,8877691};
	
	// 其数据量小，当数字超过10位时，就不可能存在所有位数的值唯一的数，可以事先计算好所有值，直接返回指定结果
	public int countNumbersWithUniqueDigitsB(int n) {
		if (n < 0) {
			return 0;
		}
		return n < 10 ? COUNTS[n] : COUNTS[10];
	}
}

package net.saisimon.leetcode;

/**
 * 172. Factorial Trailing Zeroes
 * 	Given an integer n, return the number of trailing zeroes in n!.
 * 	Note: Your solution should be in logarithmic time complexity.
 * 
 * @author Saisimon
 *
 */
public class FactorialTrailingZeroes {
	/*
	 *  n < 5  -> 0 
	 *  n < 10 -> 1 5
	 *  n < 15 -> 2 5 10
	 *  n < 20 -> 3 5 10 15
	 *  n < 25 -> 4 5 10 15 20
	 *  n < 30 -> 6 5 10 15 20 25
	 *  n < 35 -> 7 5 10 15 20 25 30
	 *  
	 *  阶乘值最后的零是由2 * 5提供的，而阶乘中 2 的数量必然比 5 的数量多
	 *  所以只需要计算阶乘中有多少个5
	 *  
	 *  56! :
	 *  
	 *  1 *..* 5 *..* 10 *..* 15 *..* 20 *..* 25 *..* 30 *..* 35 *..* 40 *..* 45 *..* 50 *..* 55 * 56
	 *  
	 *  1 *..* 5 *..* 2*5 *..* 3*5 *..* 4*5 *..* 5*5*1 *..* 5*6 *..* 5*7 *..* 5*8 *..* 5*9 *..* 5*5*2 *..* 5*11 * 56
	 *         1       1        1        1         2         1        1        1        1         2         1
	 *         1       1        1        1        1+1        1        1        1        1        1+1        1
	 *    = 56 / 5 +                               1                                              1
	 *    = 56 / 5 + 56 / 5 / 5
	 *    = 13
	 *    
	 *    f(n) = n / 5 + n / 25 + n / 125 ...
	 */
	
	// 循环
	public int trailingZeroesA(int n) {
		int res = 0;
		while (n > 0) {
			res += n /= 5;
		}
		return res;
    }
	
	// 递归
	public int trailingZeroesB(int n) {
		return n == 0 ? 0 : n / 5 + trailingZeroesB(n / 5);
	}
}

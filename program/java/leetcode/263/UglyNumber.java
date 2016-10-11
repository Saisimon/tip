package net.saisimon.leetcode;

/**
 * 263. Ugly Number
 * 	Write a program to check whether a given number is an ugly number.
 * 	Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 * 	For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 * 	Note that 1 is typically treated as an ugly number.
 * 
 * @author Saisimon
 *
 */
public class UglyNumber {
	// 递归
	public boolean isUglyA(int num) {
		if (num < 1) {
			return false;
		}
		if (num < 6) {
			return true;
		}
		boolean res = false;
    	if (num % 2 == 0) {
    		res = isUglyA(num / 2);
    	} 
    	if (num % 3 == 0) {
    		res = isUglyA(num / 3);
    	}
    	if (num % 5 == 0) {
    		res = isUglyA(num / 5);
    	}
    	return res;
    }
	
	// 遍历
	public boolean isUglyB(int num) {
		for (int i = 2; i < 6 && num > 0; i++)
			while (num % i == 0)
				num /= i;
		return num == 1;
	}
}

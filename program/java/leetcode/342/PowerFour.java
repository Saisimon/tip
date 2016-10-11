package net.saisimon.leetcode;

/**
 * 342. Power of Four
 * 	Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 	Example:
 * 		Given num = 16, return true. Given num = 5, return false.
 * 	Follow up: Could you solve it without loops/recursion?
 * 
 * @author Saisimon
 *
 */
public class PowerFour {
	public boolean isPowerOfFourA(int num) {
		if (num == 1) {
			return true;
		}
		return check(num);
    }
	
	private boolean check(double num) {
		if (num < 4) {
			return false;
		} else if (num == 4) {
			return true;
		} else {
			return check(num / 4);
		}
	}
	
	public boolean isPowerOfFourB(int num) {
		// (num & (num - 1)) == 0 判断是否为 2 的乘方
		// 在 2 的乘方的基础上
		// 4 的乘方的 1 位永远在奇数位上, 4 -> 100, 16 -> 10000
		// 0x55555555 的 奇数位全为1, 和 num 做与操作，结果肯定不为 0
		return (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }
}

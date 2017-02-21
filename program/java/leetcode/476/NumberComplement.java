package net.saisimon.leetcode;

/**
 * 476. Number Complement
 * 	Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 * 
 * 	Note:
 * 		1. The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * 		2. You could assume no leading zero bit in the integer’s binary representation.
 * 
 * 	Example 1:
 * 		Input: 5
 * 		Output: 2
 * 		Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 * 
 * 	Example 2:
 * 		Input: 1
 * 		Output: 0
 * 		Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 * 
 * @author Saisimon
 *
 */
public class NumberComplement {
	
	/*
	 * 异或门
	 * 1 ^ 1 = 0
	 * 1 ^ 0 = 1
	 * 0 ^ 0 = 0
	 */
	public int findComplementA(int num) {
        return num ^ ((1 << Integer.toBinaryString(num).length()) - 1);
    }
	
	/*
	 * 同上
	 */
	public int findComplementB(int num) {
        return num ^ ((Integer.highestOneBit(num) << 1) - 1);
    }
	
	/*
	 * 按位取反
	 * ~1 = -2
	 * ~0 = -1
	 */
	public int findComplementC(int num) {
        return ~num + (Integer.highestOneBit(num) << 1);
    }
	
}

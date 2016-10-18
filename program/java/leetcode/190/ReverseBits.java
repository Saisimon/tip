package net.saisimon.leetcode;

/**
 * 190. Reverse Bits
 * 	Reverse bits of a given 32 bits unsigned integer.
 * 	For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
 * 	return 964176192 (represented in binary as 00111001011110000010100101000000).
 * 
 * 	Follow up:
 * 		If this function is called many times, how would you optimize it?
 * 
 * @author Saisimon
 *
 */
public class ReverseBits {
	// you need treat n as an unsigned value
    public int reverseBitsA(int n) {
    	// 转换为无符号数
    	long x = ((long) n) & 0xffffffffL;
        long res = 0;
        int i = 31;
        while (x != 0) {
        	long tmp = x % 2;
        	if (Math.abs(tmp) == 1) {
        		res += (1 << i);
        	}
        	i--;
        	x >>= 1;
        }
        return (int) res;
    }
    
    // 位操作
    public int reverseBitsB(int n) {
    	int r = 0;
        for (int i = 31; i >= 0; i--, n >>= 1) {
          r |= ((n & 1) << i);
        }
        return r;
    }
}

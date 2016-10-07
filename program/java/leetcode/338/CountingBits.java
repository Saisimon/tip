package net.saisimon.leetcode;

/**
 * 338. Counting Bits
 * 	Given a non negative integer number num. 
 * 	For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * 	Example:
 * 	For num = 5 you should return [0,1,1,2,1,2].
 * 	
 * 	Follow up:
 * 		It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * 		Space complexity should be O(n).
 * 		Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 * 
 * @author Saisimon
 *
 */
public class CountingBits {
	public int[] countBitsA(int num) {
		int[] res = new int[num + 1];
		String tmp;
        for (int i = 1; i <= num; i++) {
			tmp = Integer.toBinaryString(i);
			for (int j = 0; j < tmp.length(); j++) {
				if (tmp.charAt(j) == '1') {
					res[i]++;
				}
			}
		}
        return res;
    }
	
	// 时间复杂度 O(n)，9-15 相当于 1-7 加一
	public int[] countBitsB(int num) {
		int[] ret = new int[num + 1];
		int pow = 1;
		for (int i = 1, t = 0; i <= num; i++, t++) {
			if (i == pow) {
				pow *= 2;
				t = 0;
			}
			ret[i] = ret[t] + 1;
		}
		return ret;
	}
	
	// Integer.bitCount(int num)
	public int[] countBitsC(int num) {
		int[] res = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			res[i] = Integer.bitCount(i);
		}
		return res;
	}
}

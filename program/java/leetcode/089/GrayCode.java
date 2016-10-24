package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>89. Gray Code</h1>
 * 	<p>The gray code is a binary numeral system where two successive values differ in only one bit.
 * 
 * 	<p>Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * 
 * 	<p>For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 		<blockquote>
 * 			00 - 0 <br>
 * 			01 - 1 <br>
 * 			11 - 3 <br>
 * 			10 - 2
 * 		</blockquote>
 * 
 * 	<p>Note:
 * 		<br>&nbsp; For a given n, a gray code sequence is not uniquely defined.
 * 		<br>&nbsp; For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * 
 * 	<p>Example :
 * 	<blockquote>
 * 		00000 00001 <br>
 * 		00011 00010 <br>
 * 		00110 00111 00101 00100 <br>
 *		01100 01101 01111 01110 01010 01011 01001 01000 <br>
 * 		11000 11001 11011 11010 11110 11111 11101 11100 10100 10101 10111 10110 10010 10011 10001 10000 <br>
 * 		...	<br>
 * 		0  1 0  2 0 1 0  3 0 1 0 2 0 1 0  4 0 1 0 2 0 1 0 3 0 1 0 2 0 1 0 ...
 * 	</blockquote>
 * 	
 * @author Saisimon
 *
 */
public class GrayCode {
	public List<Integer> grayCodeA(int n) {
		// 结果长度
		int len = 1 << n;
		// 存放结果
		List<Integer> res = new ArrayList<>(len);
		// 存放当前应该变换哪一位
		List<Integer> tmp = new ArrayList<>(len);
		// 当前数的二进制位数
		Integer gap = 0;
		// 从第一位开始变换
		tmp.add(0);
		while (tmp.size() <= len) {
			// 位数增加
			gap++;
			// 变换最高位
			tmp.add(gap);
			int size = tmp.size() - 1;
			// 接下来的 2 ^ gap - 1 位就是前面所有的倒叙排列
			for (int i = size - 1; i >= 0; i--) {
				tmp.add(tmp.get(i));
			}
		}
		// 结果从 0 开始
		res.add(0);
		// 顺序与上一个结果进行异或操作，变换指定位置
		for (int i = 1; i < len; i++) {
			// 根据 tmp 列表中指定的位置变换
			res.add(res.get(i - 1) ^ (1 << tmp.get(i - 1))); 
		}
        return res;
    }
	
	/*
	 * 	Gray Code : https://en.wikipedia.org/wiki/Gray_code
	 *  G(i) = i ^ (i / 2)
	 */
	public List<Integer> grayCodeB(int n) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < 1 << n; i++) {
			res.add(i ^ i >> 1);
		}
		return res;
	}
}

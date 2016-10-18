package net.saisimon.leetcode;

/**
 * 67. Add Binary
 * 	Given two binary strings, return their sum (also a binary string).
 * 	
 * 	For example:
 * 		a = "11"
 * 		b = "1"
 * 		Return "100".
 * 
 * @author Saisimon
 *
 */
public class AddBinary {
	// 使用 Java 自带的转换方法
	public String addBinaryA(String a, String b) {
        long longA = Long.parseLong(a, 2);
        long longB = Long.parseLong(b, 2);
        return Long.toBinaryString(longA + longB);
    }
	
	public String addBinaryB(String a, String b) {
		// 确保 a 的长度大于 b 的长度，否则就交换两个字符串
		if (a.length() < b.length()) {
			String tmp = a;
			a = b;
			b = tmp;
		}
		// 保存结果，结果最多比 a 的长度长一位
		char[] cs = new char[a.length() + 1];
		// 进位标识符
		boolean over = false;
		// 遍历
		for (int i = a.length() - 1, j = b.length() - 1; i >= 0; i--, j--) {
			if (j >= 0) {
				if (a.charAt(i) == '1' && b.charAt(j) == '1') {
					// 都为 1 时，必然进位
					if (over) {
						cs[i + 1] = '1';
					} else {
						cs[i + 1] = '0';
					}
					over = true;
				} else if (a.charAt(i) == '0' && b.charAt(j) == '0') {
					// 都为 0 时，必然不进位
					if (over) {
						cs[i + 1] = '1';
					} else {
						cs[i + 1] = '0';
					}
					over = false;
				} else {
					// 其中一个为 1 ，可能进位
					if (over) {
						cs[i + 1] = '0';
						over = true;
					} else {
						cs[i + 1] = '1';
						over = false;
					}
				}
			} else {
				if (over) {
					// 上一位进位
					if (a.charAt(i) == '1') {
						cs[i + 1] = '0';
						over = true;
					} else {
						cs[i + 1] = '1';
						over = false;
					}
				} else {
					cs[i + 1] = a.charAt(i);
				}
			}
		}
		// 循环结束后，检查是否最后一位是否要进位
		if (over) {
			cs[0] = '1';
			return new String(cs);
		} else {
			return new String(cs).substring(1);
		}
    }
}

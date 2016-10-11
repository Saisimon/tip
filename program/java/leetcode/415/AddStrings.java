package net.saisimon.leetcode;

/**
 * 415. Add Strings
 * 	Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
 * 	Note:
 * 		The length of both num1 and num2 is < 5100.
 * 		Both num1 and num2 contains only digits 0-9.
 * 		Both num1 and num2 does not contain any leading zero.
 * 		You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * @author Saisimon
 *
 */
public class AddStrings {
	public String addStrings(String num1, String num2) {
		char[] cs1, cs2;
		// 确保cs1 不小于 cs2
        if (num1.length() < num2.length()) {
        	cs1 = num2.toCharArray();
        	cs2 = num1.toCharArray();
        } else {
        	cs1 = num1.toCharArray();
        	cs2 = num2.toCharArray();
        }
        StringBuilder res = new StringBuilder();
        // 进位标识符
        boolean over = false;
        // 由后向前遍历字符数组
        for (int i = cs1.length - 1, j = cs2.length - 1; i >= -1; i--,j--) {
        	// 当前位的和
        	int tmp = 0;
        	if (i == -1) {
        		// 最后一位，判断是否进位
        		if (over) {
        			res.insert(0, 1);
        		}
        		break;
        	} else if (j < 0) {
        		// 只剩 cs1 数组有位数
        		tmp = cs1[i] - '0';
			} else {
				// 位数相加
				tmp = (cs1[i] - '0') + (cs2[j] - '0');
			}
        	// 前一位是否进位，进位则和加一
        	if (over) {
    			tmp++;
    		}
        	//判断和是否需要进位
    		if (tmp > 9) {
				res.insert(0, tmp - 10);
				over = true;
			} else {
				res.insert(0, tmp);
				over = false;
			}
		}
        return res.toString();
    }
}

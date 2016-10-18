package net.saisimon.leetcode;

/**
 * 66. Plus One
 * 	Given a non-negative number represented as an array of digits, plus one to the number.
 * 	The digits are stored such that the most significant digit is at the head of the list.
 * 
 * @author Saisimon
 *
 */
public class PlusOne {
	// 重复代码较多
	public int[] plusOneA(int[] digits) {
		// 空数组直接返回 null
		if (digits == null) {
			return null;
		}
		// 当前位是否进位标识符
		boolean over = false;
		// 倒序遍历
		for (int i = digits.length - 1; i >= 0; i--) {
			// 最后一个元素
			if (i == digits.length - 1) {
				// 当前元素为9，进位并至 0，否则加一
				if (digits[i] == 9) {
					over = true;
					digits[i] = 0;
				} else {
					digits[i]++;
				}
				// 第一个元素并且当前位要进位
				if (i == 0 && over) {
					// [9,9] => [1,0,0]
					int[] res = new int[digits.length + 1];
					res[0] = 1;
					return res;
				}
				continue;
			}
			// 判断是否进位
			if (over) {
				// 同上，判断进位
				if (digits[i] == 9) {
					if (i == 0) {
						int[] res = new int[digits.length + 1];
						res[0] = 1;
						return res;
					}
					over = true;
					digits[i] = 0;
				} else {
					over = false;
					digits[i]++;
				}
			} else {
				// 当前位没有进位，后续位亦不可能进位，即可跳出循环
				break;
			}
		}
		return digits;
    }
	
	
	// 更好理解
	public int[] plusOneB(int[] digits) {
		int n = digits.length;
		// 倒序遍历
		for (int i = n - 1; i >= 0; i--) {
			// 当前位小于 9 ，当前位加一即可直接返回
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			// 等于 9，当前位至 0
			digits[i] = 0;
		}
		// 循环结束还没有返回，则最后一位也进位
		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;
		return newNumber;
    }
}

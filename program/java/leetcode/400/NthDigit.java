package net.saisimon.leetcode;

/**
 * 400. Nth Digit
 * 	Find the n th digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * 	
 * 	Note:
 * 		n is positive and will fit within the range of a 32-bit signed integer (n < 2 ^ 31).
 * 	
 * 	Example 1:
 * 		Input: 3
 * 		Output: 3
 * 
 * 	Example 2:
 * 		Input: 11
 * 		Output: 0
 * 		Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 * 	
 * 	序列 12345678910111213...中第 11 个数字是 0 ,求第 n 个数字?
 * 				  ^
 * 
 * 	根据序列的规律，可以看出：
 * 		1 位的数有 9 个，提供 9 个数
 * 		2 位的数有 90 个，提供 180 个数
 * 		3 位的数有 900 个，提供 2700 个数
 * 		...
 * 		i 位的数有 9 * 10 ^ (i - 1) 个，提供 9 * i * 10 ^ (i - 1) 个数
 * 	
 * 	求第 n 个数，只需求出这第 n 个数在哪位数上的哪个位置即可
 * 
 * @author Saisimon
 *
 */
public class NthDigit {
	public int findNthDigit(int n) {
		// 当 n 小于零时，直接返回
		if (n <= 0) {
			return n;
		}
		// 每增加一位能为序列提供多少位，9、180、2700、36000...
		int gap = 0;
		// 在由 i 位组成的序列中排多少
		int tmp = n;
		// 位数，1、2、3、4...
		int i = 0;
		// 前面提供了多少个数，9、99、999、9999...
		int sum = 0;
		for (; tmp > gap; i++) {
			tmp -= gap;
			sum += 9 * Math.pow(10, i - 1);
			gap = (int) (9 * (i + 1) * Math.pow(10, i));
		}
		// 第 n 个数在序列哪个数字里面
		int div = sum + tmp / i;
		// 第 n 个数在序列 div 里面的哪一位上
		int mod = tmp % i;
		if (tmp % i != 0) {
			// 不能整除的时候，div 为下一个数字
			div++;
		} else {
			// 能整除时，应该为这个数字的末尾位
			mod = i;
		}
		// 存放这个数字的所有位
		int[] arr = new int[i];
		for (int j = i - 1; j >= 0; j--) {
			arr[j] = div % 10;
			div /= 10;
		}
		// 返回要求的位的值
		return arr[--mod];
    }
}

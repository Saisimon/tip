package net.saisimon.leetcode;

/**
 * <h1>390. Elimination Game</h1>
 * 	<p>There is a list of sorted integers from 1 to n. 
 * 	Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
 *
 * 	<p>Repeat the previous step again, but this time from right to left, 
 * 	remove the right most number and every other number from the remaining numbers.
 *
 * 	<p>We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 *
 * 	<p>Find the last number that remains starting with a list of length n.
 *
 * 	<p>Example:
 * 		<blockquote>
 * 		Input: n = 9, </br>
 *		1 2 3 4 5 6 7 8 9 </br>
 *		2 4 6 8 </br>
 *		2 6 </br>
 *		6 </br>
 * 		</br>
 *		Output:6
 *		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class EliminationGame {
	
	/*
	 * 关注每次变化前后第一个数的变化
	 * 
	 * 只有当向左删除数字或者向右删除时总数为奇数的时候，第一个数才会变化
	 */
	public int lastRemainingA(int n) {
		int count = n;
		int head = 1;
		int step = 1;
		boolean left = true;
		while (count > 1) {
			if (left) {
				head += step;
				left = false;
			} else {
				if (count % 2 == 1) {
					head += step;
				}
				left = true;
			}
			step <<= 1;
			count >>= 1;
		}
		return head;
	}
	
	/*
	 * 按部就班
	 * 当n 过大时，时间与空间消耗过大
	 */
	@Deprecated
	public int lastRemainingB(int n) {
		if (n < 2) {
			return 1;
		} else if (n < 6) {
			return 2;
		}
		if (n % 2 == 1) {
			n--;
		}
		int count = n / 4;
		int[] nums = new int[count];
		boolean left = true;
		if ((n / 2) % 2 == 0) {
			nums[0] = 2;
		} else {
			nums[0] = 4;
		}
		for (int i = 1; i < nums.length; i++) {
			nums[i] = nums[i - 1] + 4;
		}
        int[] temp;
        while (count > 1) {
        	count /= 2;
        	temp = new int[count];
        	if (left) {
        		left = false;
        		for (int i = 0; i < nums.length; i++) {
        			if (i % 2 != 0) {
        				temp[i / 2] = nums[i];
        			}
				}
        	} else {
        		left = true;
        		for (int i = nums.length - 1; i >= 0; i--) {
        			if ((nums.length - 1 - i) % 2 != 0) {
        				temp[i / 2] = nums[i];
        			}
				}
        	}
        	nums = temp;
        }
        return nums[0];
    }
	
}

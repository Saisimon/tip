package net.saisimon.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * <h1>386. Lexicographical Numbers</h1>
 * 	<p>Given an integer n, return 1 - n in lexicographical order.
 * 
 * 	<p>For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * 	
 * 	<p>Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 * 
 * @author Saisimon
 *
 */
public class LexicographicalNumbers {
	
	public List<Integer> lexicalOrder(int n) {
		List<Integer> res = new LinkedList<Integer>();
		int idx = 1;
		//当前数
		int tmp = 1;
        while (idx <= n) {
        	if (tmp <= n && tmp * 10 <= n) { // 当前数小于 n，10 倍当前数也小于 n
        		idx++;
        		res.add(tmp);
        		tmp *= 10;
        	} else if (tmp <= n && tmp * 10 > n) { // 当前数小于 n，10 倍的当前数大于 n
        		idx++;
        		res.add(tmp);
        		while (tmp % 10 == 9) { // 当前数末尾为9的话，下个数为除 10 除到没有 9 为止加上 1
        			tmp /= 10;
        		}
        		if (tmp == 0) {
        			// 当进行到最后，tmp 回到 0 时，跳出循环
        			break;
        		}
        		tmp++;
        	} else { // 当前数超过 n
        		tmp /= 10;
        		tmp++;
        		// 针对末尾为 0 的数
        		while (tmp % 10 == 0) {
        			tmp /= 10;
        		}
        	}
        }
        return res;
    }
	
}

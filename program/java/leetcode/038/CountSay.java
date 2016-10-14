package net.saisimon.leetcode;

/**
 * 38. Count and Say
 * 	The count-and-say sequence is the sequence of integers beginning as follows:
 * 	1, 11, 21, 1211, 111221, ...
 * 	1 is read off as "one 1" or 11.
 * 	11 is read off as "two 1s" or 21.
 * 	21 is read off as "one 2, then one 1" or 1211.
 * 	
 * 	Given an integer n, generate the n th sequence.
 * 	
 * 	Note: 
 * 		The sequence of integers will be represented as a string.
 * 
 * @author Saisimon
 *
 */
public class CountSay {
	// 递归求解
	public String countAndSay(int n) {
		if (n < 1) {
			return "";
		} else if (n == 1) {
			// 递归出口
        	return "1";
        }
		// 获取上一个结果
        String pre = countAndSay(n - 1);
        // 首字符
        char tmp = pre.charAt(0);
        StringBuilder res = new StringBuilder();
        // 当前字符出现次数
        int count = 1;
        for (int i = 1; i < pre.length(); i++) {
        	// 当前字符与上次存储的字符不一致，添加出现次数与字符至结果中，否则出现的次数加一
			if (tmp != pre.charAt(i)) {
				res.append(count).append(tmp);
				count = 1;
				// 存储当前字符
				tmp = pre.charAt(i);
			} else {
				count++;
			}
		}
        // 循环结束后，将最后的结果也添加进去
        res.append(count).append(tmp);
        return res.toString();
    }
}

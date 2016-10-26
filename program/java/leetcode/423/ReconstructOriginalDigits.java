package net.saisimon.leetcode;

/**
 * <h1>423. Reconstruct Original Digits from English</h1>
 * 	<p>Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 * 
 * 	<p>Note:
 * 		<blockquote>
 * 			Input contains only lowercase English letters. <br>
 * 			Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted. <br>
 * 			Input length is less than 50,000.
 * 		</blockquote>
 * 
 * 	<p>Example 1:
 * 		<blockquote>
 * 			Input: "owoztneoer" <br>
 * 			Output: "012"
 * 		</blockquote>
 * 
 * 	<p>Example 2:
 * 		<blockquote>
 * 			Input: "fviefuro" <br>
 * 			Output: "45"
 * 		</blockquote>
 * 
 * 	owoztneoer => zeroonetwo => 012
 * 	fviefuro => fourfive => 45
 * 
 * @author Saisimon
 *
 */
public class ReconstructOriginalDigits {
	
	// 0-9 英文单词
	private static String[] english = {"zero","one","two","three","four","five","six","seven","eight","nine"};
	// 根据某个字符可以确定某个单词出现的次数
	private static char[] cs = {'z','w','u','x','g','o','t','f','v','i'};
	// 确定数字的次序
	private static int[] is = {0,2,4,6,8,1,3,5,7,9};
	
	public String originalDigits(String s) {
		// 存储字符串所有的字符出现的次数
        int[] arr = new int[26];
        // 遍历获取所有字符
        for (int i = 0; i < s.length(); i++) {
			arr[s.charAt(i) - 'a']++;
		}
        // 0-9 出现的次数
        int[] tmp = new int[10];
        // 遍历10次，获取 0-9 出现的次数
        for (int i = 0; i < 10; i++) {
        	// 根据 cs 数组中的字符确定 is 数组中的数出现的次数
        	int min = arr[cs[i] - 'a'];
        	// is 数组中的数出现的次数不为 0
        	if (min != 0) {
        		// 减去当前数中字符出现的次数
        		for (int k = 0; k < english[is[i]].length(); k++) {
    				arr[english[is[i]].charAt(k) - 'a'] -= min;
    			}
        		// 排序，存储次序
    			tmp[is[i]] += min;
        	}
		}
        // 遍历获取最后的结果
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i]; j++) {
				res.append(i);
			}
		}
        return res.toString();
    }
}

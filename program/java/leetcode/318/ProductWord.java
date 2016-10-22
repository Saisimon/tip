package net.saisimon.leetcode;

/**
 * <h1>318. Maximum Product of Word Lengths</h1>
 * 	<p>Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
 * 	You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
 * 	
 * 	<p>Example 1:
 * 		<blockquote>
 * 			Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"] <br>
 * 			Return 16 <br>
 * 			The two words can be "abcw", "xtfn".
 * 		</blockquote>
 * 
 * 	<p>Example 2:
 * 		<blockquote>
 * 			Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"] <br>
 * 			Return 4 <br>
 * 			The two words can be "ab", "cd".
 * 		</blockquote>
 * 
 * 	<p>Example 3:
 * 		<blockquote>
 * 			Given ["a", "aa", "aaa", "aaaa"] <br>
 * 			Return 0 <br>
 * 			No such pair of words.
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class ProductWord {
	// 将字符串数组中的字符串转换为根据字符的大小的二进制数
	public int maxProduct(String[] words) {
		// 存放转换以后的数
		int[] tmp = new int[words.length];
        for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				// "abcd" => 1111   "fbcf" => 100110
				tmp[i] |= 1 << (words[i].charAt(j) - 'a');
			}
		}
        int max = 0;
        // 两两遍历比较
        for (int i = 0; i < tmp.length; i++) {
			for (int j = i + 1; j < tmp.length; j++) {
				// 当两个数没有一位是相同的时候，则表示两个字符串没有字符相同
				if ((tmp[i] & tmp[j]) == 0 && max < words[i].length() * words[j].length()) {
					max = words[i].length() * words[j].length();
				}
			}
		}
        return max;
    }
}

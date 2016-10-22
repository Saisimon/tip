package net.saisimon.leetcode;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>394. Decode String</h1>
 * 	<p>Given an encoded string, return it's decoded string.
 * 
 * 	<p>The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
 * 	Note that k is guaranteed to be a positive integer.
 * 
 * 	<p>You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * 	
 * 	<p>Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * 
 * 	<p>Examples:
 * 		<blockquote>
 * 			s = "3[a]2[bc]", return "aaabcbc". <br>
 * 			s = "3[a2[c]]", return "accaccacc". <br>
 * 			s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class DecodeString {
	// 使用 Stack 解析字符串
	public String decodeString(String s) {
		Stack<String> stack = new Stack<>();
		String num = "";
		String word = "";
        for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				stack.push(word);
				word = "";
				num += s.charAt(i);
			} else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
				word += s.charAt(i);
			} else if (s.charAt(i) == '[') {
				stack.push(num);
				num = "";
			} else if (s.charAt(i) == ']') {
				stack.push(word);
				word = "";
				String w = stack.pop();
				while (!isNumber(stack.peek())) {
					w = stack.pop() + w;
				}
				int len = Integer.parseInt(stack.pop());
				for (int j = 0; j < len; j++) {
					word += w;
				}
				word = stack.pop() + word;
			}
		}
        while (!stack.isEmpty()) {
        	word = stack.pop() + word;
        }
        return word;
    }
	
	private static Pattern pattern = Pattern.compile("[0-9]*");
	
	private boolean isNumber(String str) {
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		}
		return false;
	}
}

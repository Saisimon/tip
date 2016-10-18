package net.saisimon.leetcode;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 * 	Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 	The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * 
 * @author Saisimon
 *
 */
public class ValidParentheses {
	// 使用栈存储字符的先后顺序，利用其先进后出的特点
	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
				// 压入开始字符
				stack.push(s.charAt(i));
			} else if (s.charAt(i) == ')') {
				// 与开始字符做匹配，匹配不到的则返回空
				if (stack.isEmpty() || stack.pop() != '(') {
					return false;
				}
			} else if (s.charAt(i) == '}') {
				if (stack.isEmpty() || stack.pop() != '{') {
					return false;
				}
			} else if (s.charAt(i) == ']') {
				if (stack.isEmpty() || stack.pop() != '[') {
					return false;
				}
			}
		}
        // 遍历字符串结束，栈中还有字符，说明不匹配
        if (!stack.isEmpty()) {
        	return false;
        }
        return true;
    }
}

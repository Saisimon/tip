package net.saisimon.leetcode;

/**
 * 344. Reverse String
 * 	Write a function that takes a string as input and returns the string reversed.
 * 	Example:
 *	Given s = "hello", return "olleh".
 * 
 * @author Saisimon
 *
 */
public class ReverseString {
	public String reverseStringA(String s) {
		if (s == null) {
			return s;
		}
		StringBuilder res = new StringBuilder();
        char[] cs = s.toCharArray();
        for (int i = cs.length - 1; i >= 0; i--) {
			res.append(cs[i]);
		}
        return res.toString();
    }
	
	public String reverseStringB(String s) {
		if (s == null) {
			return s;
		}
		StringBuilder res = new StringBuilder(s);
        return res.reverse().toString();
    }
}

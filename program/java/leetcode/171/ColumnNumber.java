package net.saisimon.leetcode;

/**
 * 171. Excel Sheet Column Number
 * 	Given a column title as appear in an Excel sheet, return its corresponding column number.
 * 	For example:
 * 		A -> 1
 * 		B -> 2
 * 		...
 * 		Z -> 26
 * 		AA -> 27
 * 
 * @author Saisimon
 *
 */
public class ColumnNumber {
	public int titleToNumber(String s) {
        char[] cs = s.toCharArray();
        int res = 0;
        for (int i = cs.length - 1, j = 0; i >= 0; i--, j++) {
			res += (cs[i] - 'A' + 1) * Math.pow(26, j);
		}
        return res;
    }
}

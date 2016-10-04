package net.saisimon.leetcode;

/**
 * 168. Excel Sheet Column Title
 * 	Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 	For example:
 * 		1 -> A
 * 		2 -> B
 * 		...
 * 		26 -> Z
 * 		27 -> AA
 * 
 * 	@author Saisimon
 *
 */
public class ColumnTitle {
	public String convertToTitle(int n) {
		StringBuilder res = new StringBuilder();
        while (n != 0) {
        	int num = n % 26;
        	if (num == 0) {
        		res.insert(0, (char)('Z'));
        	} else {
        		res.insert(0, (char)('A' + num - 1));
        	}
        	n = --n / 26;
        }
        return res.toString();
    }
}

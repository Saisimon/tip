package net.saisimon.leetcode;

/**
 * <h1>8. String to Integer (atoi)</h1>
 * 	<p>Implement atoi to convert a string to an integer.
 * 	
 * 	<p>Hint: 
 * 		<br>&nbsp; Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * 
 * 	<p>Notes: 
 * 		<br>&nbsp; It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 * 
 * 	<p>Requirements for atoi:
 * 		<br>&nbsp; The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 *		<br>&nbsp; The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *		<br>&nbsp; If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *		<br>&nbsp; If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 * 
 * @author Saisimon
 *
 */
public class StringToInteger {
	
	public int myAtoi(String str) {
		long res = 0;
		// 是否有符号
		boolean symbol = false;
		// 是否是正数
		boolean positive = true;
		// 是否中断
		boolean complete = false;
        for (int i = 0; i < str.length(); i++) {
        	int temp = str.charAt(i) - '0';
        	if (!complete && str.charAt(i) == ' ') {
        		continue;
        	} else if (!symbol && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
        		if (str.charAt(i) == '-') {
        			positive = false;
        		}
        		symbol = true;
        		complete = true;
        	} else if (temp <= 9 && temp >= 0) {
        		res *= 10;
        		res += temp;
        		if (res > Integer.MAX_VALUE) {
        			return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        		}
        		complete = true;
        	} else {
        		break;
        	}
        }
        return (int) (positive ? res : -res);
    }
	
}

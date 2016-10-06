package net.saisimon.leetcode;

/**
 * 258. Add Digits
 * 	Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * 	For example:
 * 	Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * 
 * @author Saisimon
 *
 */
public class AddDigits {
	public int addDigitsA(int num) {
        String s;
        while(num > 9) {
        	s = String.valueOf(num);
        	int temp = 0;
        	for(char c : s.toCharArray()) {
        		temp += c - '0';
        	}
        	num = temp;
        }
        return num;
    }
	
	// without any loop/recursion in O(1) runtime
	public int addDigitsB(int num) {
        return num == 0 ? 0 : (num % 9 == 0 ? 9 : num % 9);
    }
}

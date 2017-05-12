package net.saisimon.leetcode;

/**
 * <h1>43. Multiply Strings</h1>
 * 	<p>Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
 * 
 * 	<p>Note:
 * 		<br>&nbsp; The length of both num1 and num2 is < 110.
 * 		<br>&nbsp; Both num1 and num2 contains only digits 0-9.
 * 		<br>&nbsp; Both num1 and num2 does not contain any leading zero.
 * 		<br>&nbsp; You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * @author Saisimon
 *
 */
public class MultiplyStrings {
	
	public String multiplyA(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
        	StringBuilder num = new StringBuilder();
        	int move = 0;
        	for (int j = num2.length() - 1; j >= 0; j--) {
        		int time = (num2.charAt(j) - '0') * (num1.charAt(i) - '0') + move;
        		num.insert(0, time % 10);
        		move = time / 10;
        	}
        	if (move != 0) {
        		num.insert(0, move);
        	}
        	addString(res, num, num1.length() - 1 - i);
        }
        return res.toString();
    }
	
	private void addString(StringBuilder num1, StringBuilder num2, int indentation) {
		StringBuilder num = new StringBuilder();
		for (int i = 0; i < indentation; i++) {
			num2.append(0);
		}
		int diff = num1.length() - num2.length();
		StringBuilder longStr = new StringBuilder(num1);
		StringBuilder shortStr = new StringBuilder(num2);
		if (num1.length() < num2.length()) {
			diff = -diff;
			longStr = num2;
			shortStr = num1;
		}
		int move = 0;
		for (int i = longStr.length() - 1; i >= 0; i--) {
			int n1 = longStr.charAt(i) - '0';
			int n2 = 0;
			if (i - diff >= 0) {
				n2 = shortStr.charAt(i - diff) - '0';
			}
			int sum = n1 + n2 + move;
			num.insert(0, sum % 10);
			move = sum / 10;
		}
		if (move != 0) {
			num.insert(0, move);
    	}
	}
	
	public String multiplyB(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int p1 = i + j;
				int sum = mul + pos[p1 + 1];
				pos[p1] += sum / 10;
				pos[p1 + 1] = (sum) % 10;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int p : pos) {
			if (!(sb.length() == 0 && p == 0)) {
				sb.append(p);
			}
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
	
}

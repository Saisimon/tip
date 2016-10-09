package net.saisimon.leetcode;

/**
 * 12. Integer to Roman
 * 	Given an integer, convert it to a roman numeral.
 * 	Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author Saisimon
 *
 */
public class IntegerRoman {
	
	private static final int[] VALUES = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
	private static final String[] ROMANS = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	
	public String intToRomanA(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < VALUES.length; i++) {
            while(num >= VALUES[i]) {
                num -= VALUES[i];
                sb.append(ROMANS[i]);
            }
        }
        return sb.toString();
    }
	
	private static final String M[] = {"", "M", "MM", "MMM"};
	private static final String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	private static final String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	private static final String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
	
	public String intToRomanB(int num) {
	    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
	}
	
	public String intToRomanC(int num) {
		String[] strs = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		StringBuffer sb = new StringBuffer();
		int qcount = num / 1000;
		num = num % 1000;
		for (int i = 0; i < qcount; i++) {
			sb.append("M");
		}
		int bcount = num / 100;
		num = num % 100;
		if (bcount > 0) {
			String sub = strs[bcount - 1].replaceAll("I", "C").replaceAll("V", "D").replaceAll("X", "M");
			sb.append(sub);
		}
		int scount = num / 10;
		num = num % 10;
		if (scount > 0) {
			String sub = strs[scount - 1].replaceAll("X", "C").replaceAll("V", "L").replaceAll("I", "X");
			sb.append(sub);
		}
		if (num > 0) {
			sb.append(strs[num - 1]);
		}
		return sb.toString();
	}
}

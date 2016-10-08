package net.saisimon.leetcode;

/**
 * 405. Convert a Number to Hexadecimal
 * 	Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 * 	Note:
 * 		All letters in hexadecimal (a-f) must be in lowercase.
 * 		The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 * 		The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * 		You must not use any method provided by the library which converts/formats the number to hex directly.
 * 	Example 1:
 * 		Input: 26
 * 		Output: "1a"
 * 	Example 2:
 * 		Input: -1
 * 		Output: "ffffffff"
 * 
 * @author Saisimon
 *
 */
public class ConvertNumberHex {
	private static final char[] LETTERS = {'0','1','2','3','4','5','6','7','8','9','a', 'b', 'c', 'd', 'e', 'f'};
	
	public String toHexA(int num) {
		if (num == 0) {
			return "0";
		}
		long tmp = num;
        if (num < 0) {
        	// 取得补码
        	tmp = (long) Integer.MAX_VALUE * 2 + 2 + num;
        }
        StringBuilder res = new StringBuilder();
        while (tmp != 0) {
        	long div = tmp / 16;
        	int mod = (int) (tmp % 16);
        	if (div == 0) {
        		res.insert(0, LETTERS[mod]);
        		tmp = 0;
        	} else if (div < 16) {
        		res.insert(0, LETTERS[(int) div] + "" + LETTERS[mod]);
        		tmp = 0;
        	} else {
        		res.insert(0, LETTERS[mod]);
        		tmp = div;
        	}
        }
        return res.toString();
    }
	
	public String toHexB(int num) {
        if(num == 0) 
        	return "0";
        String result = "";
        while(num != 0){
        	// 取 num 最后八位对应的字符
            result = LETTERS[(num & 0xf)] + result; 
            // 将 num 右移 4 位
            num = (num >>> 4);
        }
        return result;
    }
	
	public static void main(String[] args) {
		ConvertNumberHex cnh = new ConvertNumberHex();
		System.out.println(cnh.toHexB(-1));
	}
}

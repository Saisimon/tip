package net.saisimon.leetcode;

/**
 * 6. ZigZag Conversion
 * 	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * 	(you may want to display this pattern in a fixed font for better legibility)
 * 		P   A   H   N
 * 		A P L S I I G
 * 		Y   I   R
 * 	And then read line by line: "PAHNAPLSIIGYIR"
 * 	
 * 	Write the code that will take a string and make this conversion given a number of rows:
 * 		string convert(string text, int nRows);
 * 	
 * 	convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 * 	P Y A I H R N
 * 	A P L S I I G
 * 
 * 	PYAIHRNAPLSIIG
 * 	
 * 	P     I     N
 * 	A   L S   I G
 * 	Y A	  H R
 * 	P     I 
 * 
 * 	PINALSIGYAHRPI
 * 
 * 	P       H
 * 	A     S I
 * 	Y   I   R
 * 	P L     I G
 * 	A       N
 * 	
 * 	PHASIYIRPLIGAN
 * 
 * @author Saisimon
 *
 */
public class ZigZagConversion {
	// 使用二维数组存储字符
	public String convert(String s, int numRows) {
		// 小于2的时候，直接返回原字符串
        if (numRows < 2) {
        	return s;
        }
        // 二维数组，最大宽度为 numRows 为 2 的时候
        char[][] arr = new char[numRows][(s.length() + 1) / 2];
        int x = 0;
        int y = 0;
        // 前进方向
        boolean down = true;
        for (int i = 0; i < s.length(); i++) {
        	arr[x][y] = s.charAt(i);
        	if (down && x < numRows - 1) {
        		// 向下
        		x++;
        	} else if (down && x == numRows - 1) {
        		// 换方向，向斜上方
        		x--;
        		y++;
        		down = false;
        	} else if (!down && x > 0) {
        		// 向斜上方
        		x--;
        		y++;
        	} else if (!down && x == 0) {
        		// 换方向，向下
        		x++;
        		down = true;
        	}
		}
        // 遍历二维数组得到结果
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != 0) {
					res.append(arr[i][j]);
				}
			}
		}
        return res.toString();
    }
}

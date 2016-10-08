package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. Binary Watch
 * 	A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * 	Each LED represents a zero or one, with the least significant bit on the right.
 * 	Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * 	Example:
 * 		Input: n = 1
 * 		Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * 	Note:
 * 		The order of output does not matter.
 * 		The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * 		The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 * 
 * @author Saisimon
 *
 */
public class BinaryWatch {
	public List<String> readBinaryWatchA(int num) {
		List<String> times = new ArrayList<>();
		// 遍历所有时间
		for (int h = 0; h < 12; h++)
			for (int m = 0; m < 60; m++)
				// 获取 1 位数量的和
				if (Integer.bitCount(h * 64 + m) == num)
					// 格式化操作
					times.add(String.format("%d:%02d", h, m));
		return times;
    }
	
	public List<String> readBinaryWatchB(int num) {
        List<String> res = new ArrayList<>();
        dfs(res, num, 0, new int[2]);
        return res;
    }
	
    private void dfs(List<String> res, int k, int i, int[] hhmm) {
        if (i == 10 || k <= 0) {
            if (k == 0 && hhmm[0] < 12 && hhmm[1] < 60) 
                res.add(String.format("%d:%02d", hhmm[0], hhmm[1]));
            return;
        }
        int idx = i < 4 ? 0 : 1;
        int val = 1 << (i < 4 ? i : i - 4);
        hhmm[idx] += val;
        dfs(res, k - 1, i + 1, hhmm);
        hhmm[idx] -= val;
        dfs(res, k, i + 1, hhmm);
    }
}

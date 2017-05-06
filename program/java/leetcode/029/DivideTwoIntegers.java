package net.saisimon.leetcode;

/**
 * <h1>29. Divide Two Integers</h1>
 * 	<p>Divide two integers without using multiplication, division and mod operator.
 * 	
 * 	<p>If it is overflow, return MAX_INT.
 * 
 * @author Saisimon
 *
 */
public class DivideTwoIntegers {
	
	// Integer.MAX_VALUE 下 10 的倍数数组
	private final static int[] tens = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };
	
	// 按照笔算除法来做除法运算
	public int divide(int dividend, int divisor) {
		// 排除除零错误
		if (divisor == 0) {
			throw new ArithmeticException("/ by zero");
		}
		// 判断结果的符号
        boolean negative = (dividend >= 0) ^ (divisor > 0);
        long dividendLong = Math.abs((long) dividend);
		long divisorLong = Math.abs((long) divisor);
        // 被除数小于除数直接返回 0
        if (dividendLong < divisorLong) {
        	return 0;
        }
        // 除数为 1 或 -1 时直接返回被除数本身或取反，注意 Integer 最小值取反会溢出，直接返回最大值
        if (divisorLong == 1) {
        	if (dividendLong > Integer.MAX_VALUE) {
        		return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        	}
        	return (int) (negative ? -dividendLong : dividendLong);
        }
        // 将除数与被除数按字符串做处理,进行笔算除法
        String dividendStr = dividendLong + "";
        String divisorStr = divisorLong + "";
        StringBuilder sb = new StringBuilder();
        // 被除数的分割位置
        int idx = divisorStr.length();
        while (idx <= dividendStr.length()) {
        	// 按照除数的位数进行分割操作
        	String numStr = dividendStr.substring(0, idx);
        	String tailStr = dividendStr.substring(idx);
        	// 根据字符串得到对应的数字
        	long num = getNumber(numStr);
        	// 当除数大于分割后的数时，进行补零操作
        	if (divisorLong > num) {
        		sb.append(0);
        		idx++;
        		continue;
        	}
        	// 当前除法的商
        	int temp = 0;
        	// 除法操作
        	while (divisorLong <= num) {
        		temp++;
        		num -= divisorLong;
        	}
        	// 保存商
        	sb.append(temp);
        	// 聚合被除数
        	dividendStr = num + tailStr;
        	// 获取下一个分割点
        	idx = (num + "").length() + 1;
        }
        // 获得结果
        long res = getNumber(sb.toString());
        // 根据符号返回结果
        return (int) (negative ? -res : res);
    }
	
	// 根据字符串得到对应的数字
	private long getNumber(String num) {
		long res = 0;
		for (int i = num.length() - 1; i >= 0; i--) {
			int n = num.charAt(i) - '0';
			for (int j = 0; j < n; j++) {
				res += tens[num.length() - 1 - i];
			}
		}
		return res;
	}
	
	// 递归操作除法
	public int divideB(int dividend, int divisor) {
		int sign = 1;
		if ((dividend >= 0) ^ (divisor > 0)) {
			sign = -1;
		}
		long ldividend = Math.abs((long) dividend);
		long ldivisor = Math.abs((long) divisor);
		if (ldivisor == 0) {
			throw new ArithmeticException("/ by zero");
		}
		if ((ldividend == 0) || (ldividend < ldivisor)) {
			return 0;
		}
		long lans = ldivide(ldividend, ldivisor);
		int ans = 0;
		if (lans > Integer.MAX_VALUE) {
			ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		} else {
			ans = (int) (sign * lans);
		}
		return ans;
	}

	private long ldivide(long ldividend, long ldivisor) {
		if (ldividend < ldivisor) {
			return 0;
		}
		long sum = ldivisor;
		long multiple = 1;
		while ((sum + sum) <= ldividend) {
			sum += sum;
			multiple += multiple;
		}
		return multiple + ldivide(ldividend - sum, ldivisor);
	}
	
}

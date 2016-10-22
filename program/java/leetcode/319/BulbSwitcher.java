package net.saisimon.leetcode;

import java.math.BigInteger;

/**
 * <h1>319. Bulb Switcher</h1>
 * 	<p>There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. 
 * 	On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
 * 	For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. 
 * 	Find how many bulbs are on after n rounds.
 * 
 * 	<p>Example:
 * 		<blockquote>
 * 			Given n = 3. <br>
 * 			<br>
 * 			At first, the three bulbs are [off, off, off].<br>
 * 			After first round, the three bulbs are [on, on, on].<br>
 * 			After second round, the three bulbs are [on, off, on].<br>
 * 			After third round, the three bulbs are [on, off, off]. <br>
 * 			<br>
 * 			So you should return 1, because there is only one bulb is on.
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class BulbSwitcher {
	
	/*
	 * 影响灯泡开关状态的只有当前数的因子个数，而大部分数的因子都是成对出现的。
	 * 而只有完全平方数会出现奇数个因子的情况，所以只需要求出小于 n 有多少个完全平方算即可。
	 */
	public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
	
	/*
	 * 遍历 - 异或
	 * 当 n 过大时，时间复杂度过高。Time out
	 */
	@Deprecated
	public int bulbSwitchB(int n) {
        if (n == 0) {
        	return 0;
        }
        BigInteger res = BigInteger.ZERO;
        for (int i = 1; i <= n; i++) {
        	BigInteger tmp = BigInteger.ONE;
			for (int j = 1; j < n + 1 - i; j++) {
				tmp = tmp.shiftLeft(1);
				if (j % i == 0) {
					tmp = tmp.add(BigInteger.ONE);
				}
			}
			res = res.xor(tmp);
		}
        return res.bitCount();
    }
}

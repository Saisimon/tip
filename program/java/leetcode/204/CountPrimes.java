package net.saisimon.leetcode;

/**
 * 204. Count Primes
 * 	Description:
 * 		Count the number of prime numbers less than a non-negative number, n.
 * 
 * @author Saisimon
 *
 */
public class CountPrimes {
	// 普通的遍历查找质数，效率低下
	public int countPrimes(int n) {
        if (n < 2) {
        	return 0;
        }
        int res = 1;
        for (int i = 3; i < n; i += 2) {
        	boolean prime = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					prime = false;
				}
			}
			if (prime) {
				res++;
			}
		}
        return res;
    }
	
	// 依靠一个数组，选择出能写成乘积形式的数，剩下的就为质数
	public int countPrimesB(int n) {
		boolean[] primes = new boolean[n];
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (!primes[i]) {
				count++;
				for (int j = 2; i * j < n; j++) {
					primes[i * j] = true;
				}
			}
		}
		return count;
    }
}

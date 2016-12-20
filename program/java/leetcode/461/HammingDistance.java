package net.saisimon.leetcode;

/**
 * <h1>461. Hamming Distance</h1>
 * 
 * 	<p>The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * 	<p>Given two integers x and y, calculate the Hamming distance.
 * 
 * 	<p>Note:
 * 		<blockquote>
 * 		0 ≤ x, y < 2 ^ 31
 * 		</blockquote>
 * 
 * 	<p>Example:
 * 		<blockquote>
 * 		Input: x = 1, y = 4 </br>
 * 		Output: 2 </br>
 * 		Explanation: </br>
 * 			1   (0 0 0 1) </br>
 * 			4   (0 1 0 0) </br>
 * 			       ↑   ↑ </br>
 * 		</br>
 * 		The above arrows point to positions where the corresponding bits are different.
 * 		</blockquote>
 * 
 * @author Saisimon
 *
 */
public class HammingDistance {
	
	public int hammingDistance(int x, int y) {
		// 异或  1 ^ 4 = 5
        int z = x ^ y;
        // 求出位为1的数量
        return Integer.bitCount(z);
    }
	
}

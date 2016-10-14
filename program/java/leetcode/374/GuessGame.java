package net.saisimon.leetcode;

/**
 * 374. Guess Number Higher or Lower
 * 
 * The guess API
 * 
 * @author Saisimon
 * @see GuessNumber
 *
 */
public class GuessGame {
	
	private int num;
	
	public void setNum(int num) {
		this.num = num;
	}
	
	/**
	 * @param num your guess
	 * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
	 */
	int guess(int num) {
		if (num > this.num) {
			return -1;
		} else if (num < this.num) {
			return 1;
		} else {
			return 0;
		}
	}
}

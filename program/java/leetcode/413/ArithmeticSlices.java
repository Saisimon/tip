package net.saisimon.leetcode;

/**
 * <h1>413. Arithmetic Slices</h1>
 * 	<p>A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * 	
 * 	<p>For example, these are arithmetic sequence:
 * 		<br>&nbsp; 1, 3, 5, 7, 9
 * 		<br>&nbsp; 7, 7, 7, 7
 * 		<br>&nbsp; 3, -1, -5, -9
 * 		
 * 	<p>The following sequence is not arithmetic.
 * 		<br>&nbsp; 1, 1, 2, 5, 7
 * 	
 * 	<p>A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 * 	
 * 	<p>A slice (P, Q) of array A is called arithmetic if the sequence:
 * 		<br>&nbsp; A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 * 	
 * 	<p>The function should return the number of arithmetic slices in the array A.
 * 	
 * 	<p>Example:
 * 		<br>&nbsp; A = [1, 2, 3, 4]
 * 		<br>&nbsp; return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 * 
 * 		<br>&nbsp; B = [1,2,3,4,5,6]
 * 		<br>&nbsp; B : [1,2,3],[2,3,4],[3,4,5],[4,5,6],[1,2,3,4],[2,3,4,5],[3,4,5,6],[1,2,3,4,5],[2,3,4,5,6,],[1,2,3,4,5,6]
 * 
 * @author Saisimon
 *
 */
public class ArithmeticSlices {
	public int numberOfArithmeticSlices(int[] A) {
		int res = 0;
		int tmp = 0;
        for (int i = 2; i < A.length; i++) {
			while (i < A.length && A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
				tmp++;
				res += tmp;
				i++;
			}
			tmp = 0;
		}
        return res;
    }
}

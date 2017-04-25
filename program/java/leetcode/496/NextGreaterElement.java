package net.saisimon.leetcode;

/**
 * <h1>496. Next Greater Element I</h1>
 * 	<p>You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
 * 	Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * 
 * 	<p>The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
 * 	If it does not exist, output -1 for this number.
 * 
 * 	<p>Example 1:
 * 		<br>&nbsp; Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 		<br>&nbsp; Output: [-1,3,-1]
 * 		<br>&nbsp; Explanation:
 * 			<br>&nbsp;&nbsp; For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 * 			<br>&nbsp;&nbsp; For number 1 in the first array, the next greater number for it in the second array is 3.
 * 			<br>&nbsp;&nbsp; For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * 
 * 	<p>Example 2:
 * 		<br>&nbsp; Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * 		<br>&nbsp; Output: [3,-1]
 * 		<br>&nbsp; Explanation:
 * 			<br>&nbsp;&nbsp; For number 2 in the first array, the next greater number for it in the second array is 3.
 * 			<br>&nbsp;&nbsp; For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * 
 * 	<p>Note:
 * 		<br>&nbsp; All elements in nums1 and nums2 are unique.
 * 		<br>&nbsp; The length of both nums1 and nums2 would not exceed 1000.
 * 
 * @author Saisimon
 *
 */
public class NextGreaterElement {
	
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if (findNums == null || nums == null) {
        	return null;
        }
        int[] result = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
        	boolean checked = false;
        	int find = -1;
        	for (int j = 0; j < nums.length; j++) {
        		if (!checked && nums[j] == findNums[i]) {
        			checked = true;
        		}
        		if (checked && nums[j] > findNums[i]) {
        			find = nums[j];
        			break;
        		}
        	}
        	result[i] = find;
        }
        return result;
    }
	
}

package net.saisimon.leetcode;

/**
 * <h1>4. Median of Two Sorted Arrays</h1>
 * 	<p>There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 	
 * 	<p>Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * 	<p>Example 1:
 * 		<br>&nbsp; nums1 = [1, 3]
 * 		<br>&nbsp; nums2 = [2]
 * 		<br>&nbsp; The median is 2.0
 * 
 * 	<p>Example 2:
 * 		<br>&nbsp; nums1 = [1, 2]
 * 		<br>&nbsp; nums2 = [3, 4]
 * 		<br>&nbsp; The median is (2 + 3)/2 = 2.5
 * 
 * @author Saisimon
 *
 */
public class MedianTwoSortedArrays {
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        if (n == 0) {
        	return 0;
        }
        boolean odd = n % 2 == 1;
        int m = odd ? (n - 1) >> 1 : n >> 1;
        int i = 0, j = 0, idx = 0, h = 0, t = 0;
        // 归并排序
        while (i + j <= m) {
        	if (i >= nums1.length) {
        		if (idx == 0) {
        			h = nums2[j];
        			t = nums2[j];
        		} else {
        			h = t;
        			t = nums2[j];
        		}
        		j++;
        	} else if (j >= nums2.length) {
        		if (idx == 0) {
        			h = nums1[i];
        			t = nums1[i];
        		} else {
        			h = t;
        			t = nums1[i];
        		}
        		i++;
        	} else if (nums1[i] < nums2[j]) {
        		if (idx == 0) {
        			h = nums1[i];
        			t = nums1[i];
        		} else {
        			h = t;
        			t = nums1[i];
        		}
        		i++;
        	} else if (nums1[i] >= nums2[j]) {
        		if (idx == 0) {
        			h = nums2[j];
        			t = nums2[j];
        		} else {
        			h = t;
        			t = nums2[j];
        		}
        		j++;
        	}
        	idx++;
        }
        if (odd) {
        	return t;
        } else {
        	return (h + t) / 2.0;
        }
    }
	
}

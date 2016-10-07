package net.saisimon.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 349. Intersection of Two Arrays
 * 	Given two arrays, write a function to compute their intersection.
 * 	Example:
 * 		Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * 	Note:
 * 		Each element in the result must be unique.
 * 		The result can be in any order.
 * 
 * @author Saisimon
 *
 */
public class IntersectionArrays {
	public int[] intersectionA(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        out:for (int i = 0; i < nums2.length; i++) {
			for (int j = 0; j < nums1.length; j++) {
				if (nums2[i] == nums1[j]) {
					set.add(nums2[i]);
					continue out;
				}
			}
		}
        int[] res = new int[set.size()];
        int idx = 0;
        for (int i : set) {
			res[idx] = i;
			idx++;
		}
        return res;
    }
	
	public int[] intersectionB(int[] nums1, int[] nums2) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
        	setA.add(nums1[i]);
		}
        for (int i = 0; i < nums2.length; i++) {
			if (setA.contains(nums2[i])) {
				setB.add(nums2[i]);
			}
		}
        int[] res = new int[setB.size()];
        int idx = 0;
        for (int i : setB) {
        	res[idx] = i;
			idx++;
		}
        return res;
    }
}

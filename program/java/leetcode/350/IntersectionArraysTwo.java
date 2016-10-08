package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 350. Intersection of Two Arrays II
 * 	Given two arrays, write a function to compute their intersection.
 * 	Example:
 * 		Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * 	Note:
 * 		Each element in the result should appear as many times as it shows in both arrays.
 * 		The result can be in any order.
 * 
 * @author Saisimon
 *
 */
public class IntersectionArraysTwo {
	// 使用 HashMap 存储与对比
	public int[] intersectA(int[] nums1, int[] nums2) {
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		// 存储 nums1
		for (int i = 0; i < nums1.length; i++) {
			if (map.containsKey(nums1[i])) {
				map.put(nums1[i], map.get(nums1[i]) + 1);
			} else {
				map.put(nums1[i], 1);
			}
		}
		// 将 nums2 与 HashMap 做对比
		for (int i = 0; i < nums2.length; i++) {
			if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
				list.add(nums2[i]);
				map.put(nums2[i], map.get(nums2[i]) - 1);
			}
		}
		int[] res = new int[list.size()];
        int idx = 0;
        for (int i : list) {
        	res[idx] = i;
			idx++;
		}
        return res;
    }
	
	public int[] intersectB(int[] nums1, int[] nums2) {
		// 对数组进行排序操作
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int pnt1 = 0, pnt2 = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		// 依次对比两数组
		while ((pnt1 < nums1.length) && (pnt2 < nums2.length)) {
			if (nums1[pnt1] < nums2[pnt2]) {
				pnt1++;
			} else if (nums1[pnt1] > nums2[pnt2]) {
				pnt2++;
			} else {
				list.add(nums1[pnt1]);
				pnt1++;
				pnt2++;
			}
		}
		int[] res = new int[list.size()];
		int idx = 0;
		for (int i : list) {
			res[idx] = i;
			idx++;
		}
        return res;
    }
}

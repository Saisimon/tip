package net.saisimon.leetcode;

/**
 * 88. Merge Sorted Array
 * 	Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * 	
 * 	Note:
 * 		You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
 * 		The number of elements initialized in nums1 and nums2 are m and n respectively.
 * 
 * @author Saisimon
 *
 */
public class MergeSortedArray {
	
	/*
	 * 不需要额外的数组空间
	 * 
	 * 	{1,5,8,9,0,0,0} 4 {2,3,7} 3
	 * 
	 * 	1,5,8,9,0,0,0 + 2,3,7
	 * 	1,2,8,9,0,0,0 + 3,5,7
	 * 	1,2,3,9,0,0,0 + 5,7,8
	 * 	1,2,3,5,0,0,0 + 7,8,9
	 * 	1,2,3,5,7,8,9
	 * 
	 */
	public void mergeA(int[] nums1, int m, int[] nums2, int n) {
		if (n == 0) {
			return;
		}
        for (int i = 0; i < nums1.length; i++) {
			if (i < m) {
				// 与第二个数组的第一个数字进行比较
				if (nums1[i] > nums2[0]) {
					int h = 1;
					// 交换数字，并插入到第二个数组的合适位置
					int tmp = nums1[i];
					nums1[i] = nums2[0];
					while (h < n && tmp > nums2[h]) {
						nums2[h - 1] = nums2[h];
						h++;
					}
					nums2[h - 1] = tmp;
				}
			} else {
				// 只剩下第二个数组中的元素，逐一添加进去
				nums1[i] = nums2[i - m];
			}
		}
    }
	
	/*
	 * 需要一个辅助的数组空间 - 归并排序的合并原理
	 * 
	 * 	{1,5,8,9,0,0,0} 4 {2,3,7} 3
	 * 
	 * 	1,5,8,9,0,0,0 + 2,3,7 = 1,0,0,0,0,0,0
	 * 	1,5,8,9,0,0,0 + 2,3,7 = 1,2,0,0,0,0,0
	 * 	1,5,8,9,0,0,0 + 2,3,7 = 1,2,3,0,0,0,0
	 * 	1,5,8,9,0,0,0 + 2,3,7 = 1,2,3,5,0,0,0
	 * 	1,5,8,9,0,0,0 + 2,3,7 = 1,2,3,5,7,0,0
	 * 	1,5,8,9,0,0,0 + 2,3,7 = 1,2,3,5,7,8,0
	 * 	1,5,8,9,0,0,0 + 2,3,7 = 1,2,3,5,7,8,9
	 * 
	 */
	public void mergeB(int[] nums1, int m, int[] nums2, int n) {
		// 额外的数组空间，存放合并的结果
		int[] res = new int[m + n];
		int i = 0, j = 0, h = 0;
		// 循环比较
		while (i < m || j < n) {
			if (i < m && j < n) {	
				// 依次进行比较
				if (nums1[i] <= nums2[j]) {
					res[h] = nums1[i++];
				} else {
					res[h] = nums2[j++];
				}
			} else if (i < m) {
				// 只剩下第一个数组的元素
				res[h] = nums1[i++];
			} else if (j < n){
				// 只剩下第二个数组的元素
				res[h] = nums2[j++];
			}
			h++;
		}
		// 将结果赋值给第一个数组
		for (int k = 0; k < res.length; k++) {
			nums1[k] = res[k];
		}
    }
}

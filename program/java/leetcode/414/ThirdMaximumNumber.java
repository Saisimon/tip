package net.saisimon.leetcode;

/**
 * <h1>414. Third Maximum Number</h1>
 * 	<p>Given a non-empty array of integers, return the third maximum number in this array. 
 * 	<p>If it does not exist, return the maximum number. The time complexity must be in O(n).
 * 
 * 	<p>Example 1:
 * 		<br>&nbsp; Input: [3, 2, 1]
 * 		<br>&nbsp; Output: 1
 * 		<br>&nbsp; Explanation: The third maximum is 1.
 * 
 * 	<p>Example 2:
 * 		<br>&nbsp; Input: [1, 2]
 * 		<br>&nbsp; Output: 2
 * 		<br>&nbsp; Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 * 
 * 	<p>Example 3:
 * 		<br>&nbsp; Input: [2, 2, 3, 1]
 * 		<br>&nbsp; Output: 1
 * 		<br>&nbsp; Explanation: Note that the third maximum here means the third maximum distinct number. Both numbers with value 2 are both considered as second maximum.
 * 
 * @author Saisimon
 *
 */
public class ThirdMaximumNumber {
	public int thirdMaxA(int[] nums) {
		if (nums.length == 1) {
			// 数组长度为 1 ，直接返回
			return nums[0];
		} else if (nums.length == 2) {
			// 数组长度为 2 ，返回最大值
			return Math.max(nums[0], nums[1]);
		}
		// 存放最大的前两个数
        int[] tmp = new int[2];
        int i = 1;
        // 排除重复的数
        while (i < nums.length && nums[0] == nums[i]) {
        	i++;
        }
        if (i == nums.length) {
        	// 说明数组里的元素全部相同，直接返回第一个数
        	return nums[0];
        }
        // 比较前两个不同的数，存入 tmp 数组中
        if (nums[0] > nums[i]) {
        	tmp[0] = nums[0];
        	tmp[1] = nums[i];
        } else if (nums[0] < nums[i]){
        	tmp[0] = nums[i];
        	tmp[1] = nums[0];
        }
        // 第三大的数，初始值为当前最大的数
        int third = tmp[0];
        // 是否有第三大的数
        boolean flag = false;
        // 遍历，与前两大数比较
        for (; i < nums.length; i++) {
        	// 依次比较
			if (nums[i] > tmp[0]) {
				third = tmp[1];
				tmp[1] = tmp[0];
				tmp[0] = nums[i];
				flag = true;
			} else if (nums[i] > tmp[1] && nums[i] < tmp[0]) {
				third = tmp[1];
				tmp[1] = nums[i];
				flag = true;
			} else if (nums[i] < tmp[1]) {
				if (!flag) {
					third = nums[i];
					flag = true;
				} else if (nums[i] > third) {
					third = nums[i];
				}
			}
		}
        return third;
    }
	
	public int thirdMaxB(int[] nums) {
		Integer max1 = null;
		Integer max2 = null;
		Integer max3 = null;
		for (Integer n : nums) {
			// 过滤相等的元素
			if (n.equals(max1) || n.equals(max2) || n.equals(max3))
				continue;
			// 依次比较
			if (max1 == null || n > max1) {
				max3 = max2;
				max2 = max1;
				max1 = n;
			} else if (max2 == null || n > max2) {
				max3 = max2;
				max2 = n;
			} else if (max3 == null || n > max3) {
				max3 = n;
			}
		}
		return max3 == null ? max1 : max3;
	}
}

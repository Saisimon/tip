package net.saisimon.leetcode;

/**
 * <h1>31. Next Permutation</h1>
 * 	<p>Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * 	<p>If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * 	<p>The replacement must be in-place, do not allocate extra memory.
 * 	<p>Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 		<br>&nbsp; 1,2,3 → 1,3,2
 * 		<br>&nbsp; 1,3,2 → 2,1,3
 * 		<br>&nbsp; 3,2,1 → 1,2,3
 * 		<br>&nbsp; 1,1,5 → 1,5,1
 * 		<br>&nbsp; 1,5,2 → 2,1,5
 * 		<br>&nbsp; 1,5,4,2 → 4 2 1 5
 * 		<br>&nbsp; 4,5,2,1 → 5 1 2 4
 * 		<br>&nbsp; 3,5,4,2,1 → 4 1 2 3 5
 * 
 * @author Saisimon
 *
 */
public class NextPermutation {
	
	public void nextPermutation(int[] nums) {
		// 第一次要交换的位置
		int first = -1;
		// 最后一次交换的位置
		int last = -1;
		// 是否交换首尾
		boolean change = true;
		if (nums != null) {
			int start = 0, end = nums.length - 1;
			// 从两端开始比较
	        while (start < end) {
	        	if (nums[end - 1] >= nums[end]) { // 尾部不需要交换
	        		if (nums[start] >= nums[start + 1]) { // 头部不需要交换
	        			if (change) {
	        				swap(nums, start, end); // 交换首尾
	        			}
		        		start++;
		        		end--;
		        	} else { // 头部需要交换
		        		if (first == -1) { // 第一次交换，记录交换的位置
		        			first = start;
		        		}
		        		last = start; // 记录交换的位置
		        		change = false; // 不再交换首尾
		        		start++;
		        		end--;
		        	}
	        	} else { // 尾部需要交换
	        		if (first == -1) { // 第一次交换，记录交换的位置
	        			first = start;
	        		}
	        		last = end - 1; // 记录交换的位置
	        		break; // 尾部找到要交换的位置，则只需要处理从这个位置后面的元素
	        	}
	        }
	        if (first != -1) { // 记录到终止首尾交换的位置，对数组进行还原
	        	first--;
	        	while (first >= 0) {
	        		swap(nums, first, nums.length - 1 - first);
	        		first--;
	        	}
	        }
	        if (last != -1) { // 存在要交换的位置，对其后的元素进行处理，根据规则其后元素是按降序排列
	        	int position = last; // 记录最后交换的位置
	        	start = last + 1;
	        	end = nums.length - 1;
	        	int temp = nums[start]; // 后面元素中的最大值
	        	boolean find = false; // 是否发现了最后交换的位置
	        	while (start <= end) {
	        		// 后面的元素首尾是否相等，相等的话直接取首元素作为最后要交换的元素
	        		// 如 4,1,3,3,3,3 → 4,3,1,3,3,3
	        		if (!find && start != end && nums[start] == nums[end] && nums[last] != nums[end]) {
	        			position = start;
	        			break;
	        		}
	        		// 交换首尾元素
	        		swap(nums, start, end);
	        		if (!find && nums[start] > nums[last] && nums[start] <= temp) { // 当前首元素符合交换条件，直接作为最后要交换的元素
	        			find = true; // 发现了最后交换的位置
	        			position = start;
	        		} else if (!find && nums[end] > nums[last] && nums[end] <= temp) { // 或者当前尾元素符合条件，但是还不能确定是否为最终交换的元素
	        			temp = nums[end];
	        			position = end;
	        		}
	        		start++;
	        		end--;
	        	}
	        	swap(nums, last, position); // 交换最后的元素
	        }
		}
    }
	
	// 交换两个元素的位置
	private void swap(int[] nums, int start, int end) {
		if (start == end || nums[start] == nums[end]) {
			return;
		}
		int temp = nums[end];
		nums[end] = nums[start];
		nums[start] = temp;
	}
	
}

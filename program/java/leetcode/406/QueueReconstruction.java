package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406. Queue Reconstruction by Height
 * 	Suppose you have a random list of people standing in a queue. 
 * 	Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. 
 * 	Write an algorithm to reconstruct the queue.
 * 	
 * 	Example:
 * 		Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *		Output:	[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 *	Note:
 *		The number of people is less than 1,100.
 * 
 * @author Saisimon
 *
 */
public class QueueReconstruction {
	// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
	public int[][] reconstructQueue(int[][] people) {
		// 按 h 从大到小，再按 k 从小到大对二维数组进行排序
		// [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1];
			}
		});
        List<int[]> list = new ArrayList<>();
        // 根据 k 的值 插入到 list 当中
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        for (int i = 0; i < people.length; i++) {
        	list.add(people[i][1], people[i]);
		}
        return list.toArray(new int[people.length][2]);
    }
}

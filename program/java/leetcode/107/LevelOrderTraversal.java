package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. Binary Tree Level Order Traversal II
 * 	Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * 	For example:
 * 		Given binary tree [3,9,20,null,null,15,7],
 * 		    3
 * 		   / \
 *		  9  20
 * 		    /  \
 * 		   15   7
 * 		return its bottom-up level order traversal as:
 * 		[
 * 			[15,7],
 * 			[9,20],
 * 			[3]
 * 		]
 * 
 * 	Definition for a binary tree node.
 * 	public class TreeNode {
 *     	int val;
 *     	TreeNode left;
 *     	TreeNode right;
 *     	TreeNode(int x) { val = x; }
 * 	}
 * 
 * @author Saisimon
 *
 */
public class LevelOrderTraversal {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root != null) {
			// 使用队列存储，先进先出
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				List<Integer> tmp = new ArrayList<>();
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					// 插入左节点
					if (queue.peek().left != null) {
						queue.offer(queue.peek().left);
					}
					// 插入右节点
					if (queue.peek().right != null) {
						queue.offer(queue.peek().right);
					}
					// 弹出
					tmp.add(queue.poll().val);
				}
				// 插入至
				res.add(0, tmp);
			}
		}
		return res;
    }
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
}

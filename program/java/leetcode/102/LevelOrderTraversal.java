package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * 	Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * 	For example:
 * 		Given binary tree [3,9,20,null,null,15,7],
 * 		    3
 * 		   / \
 *		  9  20
 * 		    /  \
 * 		   15   7
 * 		return its level order traversal as:
 * 		[
 * 			[3],
 * 			[9,20],
 * 			[15,7]
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
	public List<List<Integer>> levelOrder(TreeNode root) {
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
				// 顺序添加
				res.add(tmp);
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

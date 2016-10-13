package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 101. Symmetric Tree
 * 	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 	For example:
 * 		this binary tree [1,2,2,3,4,4,3] is symmetric:
 * 	    1
 *     / \
 * 	  2   2
 *	 / \ / \
 *  3  4 4  3
 *  
 *  But the following [1,2,2,null,3,null,3] is not:
 *      1
 * 	   / \
 *    2   2
 *     \   \
 *      3   3
 *  Note:
 *  	Bonus points if you could solve it both recursively and iteratively.
 *  
 *  Definition for a binary tree node.
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
public class SymmetricTree {
	// 递归
	public boolean isSymmetricA(TreeNode root) {
		if (root == null) {
			return true;
		}
		return symmetric(root.left, root.right);
	}
	
	private boolean symmetric(TreeNode left, TreeNode right) {
		if (left != null && right != null) {
			// 两个节点值相等且左边与右边相等且右边与左边相等
			return left.val == right.val && symmetric(left.left, right.right) && symmetric(left.right, right.left);
		} else if (left == null && right == null) {
			// 都为空，返回真
			return true;
		} else {
			// 其中一个为空，返回假
			return false;
		}
	}
	
	// 使用队列,超过时限
	public boolean isSymmetricB(TreeNode root) {
        if (root == null) {
        	return true;
        }
        // 队列存储节点，先进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 循环状态
        boolean status = true;
        // 列表存储当前层的节点值
        List<Integer> tmp;
        // 定义一个空节点，Queue 中的值不允许为 null
        TreeNode empty = new TreeNode(-1);
        while (status) {
        	tmp = new ArrayList<>();
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		// 添加左节点，为空则添加空节点
        		if (queue.peek().left != null) {
        			queue.offer(queue.peek().left);
        		} else {
        			queue.offer(empty);
        		}
        		// 添加右节点，为空则添加空节点
        		if (queue.peek().right != null) {
        			queue.offer(queue.peek().right);
        		} else {
        			queue.offer(empty);
        		}
        		// 弹出队首
        		TreeNode node = queue.poll();
        		// 弹出节点为空节点，则列表添加null，否则添加弹出节点的值
        		if (node == empty) {
        			tmp.add(null);
        		} else {
        			tmp.add(node.val);
        		}
        	}
        	// 从两端遍历列表
        	int start = 0;
        	int end = tmp.size() - 1;
        	// 判断是否列表全为null
        	boolean flag = true;
        	// 从两端遍历列表
        	while (end >= start) {
        		// 前后不相等是，返回假
        		if (tmp.get(start) != tmp.get(end)) {
        			return false;
        		}
        		// 前后有一个不为空，说明列表不全为 null
        		if (tmp.get(start) != null || tmp.get(end) != null) {
        			flag = false;
        		}
        		start++;
        		end--;
        	}
        	// 列表全为 null，结束循环
        	if (flag) {
        		status = false;
        	}
        }
        return true;
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

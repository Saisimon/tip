package net.saisimon.leetcode;

/**
 * 112. Path Sum
 * 	Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * 	
 * 	For example:
 * 		Given the below binary tree and sum = 22,
 * 		              5
 *    		         / \
 *  	            4   8
 *                 /   / \
 *                11  13  4
 *               /  \      \
 *              7    2      1
 *      return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
public class PathSum {
	// 递归求解
	public boolean hasPathSum(TreeNode root, int sum) {
		// 根节点为空，返回假，递归进来的节点不可能为空
        if (root == null) {
        	return false;
        }
        if (root.left == null && root.right == null) {
        	// 叶子节点
        	if (sum - root.val == 0) {
        		// 当前节点的值等于剩余和的值，则返回真，否则为假
        		return true;
        	} else {
        		return false;
        	}
        } else if (root.left == null) {
        	// 左节点为空，递归判断右节点是否存在
        	return hasPathSum(root.right, sum - root.val);
        } else if (root.right == null) {
        	// 右节点为空，递归判断左节点是否存在
        	return hasPathSum(root.left, sum - root.val);
        } else {
        	// 都存在，则两边依次判断
        	return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
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

package net.saisimon.leetcode;

/**
 * 104. Maximum Depth of Binary Tree
 * 	Given a binary tree, find its maximum depth.
 * 	The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * 
 * 	Definition for a binary tree node.
 * 	public class TreeNode {
 *     	int val;
 *     	TreeNode left;
 *     	TreeNode right;
 *     	TreeNode(int x) { val = x; }
 * 	}
 * 
 * 	@author Saisimon
 * 
 */
public class MaxDepth {
	
	public int maxDepth(TreeNode root) {
        if (root == null) {
        	return 0;
        }
        int lMaxDepth = maxDepth(root.left);
        int rMaxDepth = maxDepth(root.right);
        return lMaxDepth > rMaxDepth ? lMaxDepth + 1 : rMaxDepth + 1;
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
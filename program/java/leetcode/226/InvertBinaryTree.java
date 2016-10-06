package net.saisimon.leetcode;

/**
 * 226. Invert Binary Tree
 * 	Invert a binary tree.
 * 	     4
 *     /   \
 *    2     7
 *   / \   / \
 *  1   3 6   9
 *  
 *  to
 * 	     4
 *     /   \
 *    7     2
 *   / \   / \
 *  9   6 3   1
 *  
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * @author Saisimon
 *
 */
public class InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
        if (root == null) {
        	return root;
        }
        TreeNode lNode = root.left;
        TreeNode rNode = root.right;
        root.right = invertTree(lNode);
        root.left = invertTree(rNode);
        return root;
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

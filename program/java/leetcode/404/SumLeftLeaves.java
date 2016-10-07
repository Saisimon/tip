package net.saisimon.leetcode;

/**
 * 404. Sum of Left Leaves
 * 	Find the sum of all left leaves in a given binary tree.
 * 	Example:
 *  	  3
 * 		 / \
 * 		9  20
 *  	  /  \
 *  	 15   7
 *  There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 *	
 *	Definition for a binary tree node.
 *	public class TreeNode {
 *     	int val;
 *    	TreeNode left;
 *    	TreeNode right;
 *    	TreeNode(int x) { val = x; }
 *	}
 * 
 * @author Saisimon
 *
 */
public class SumLeftLeaves {
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return sum(root.left, true) + sum(root.right, false);
    }
	
	private int sum(TreeNode root, boolean isLeft) {
		if (root == null) {
        	return 0;
        }
		if (isLeft && root.left == null && root.right == null) {
			return root.val;
		}
		return sum(root.left, true) + sum(root.right, false);
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

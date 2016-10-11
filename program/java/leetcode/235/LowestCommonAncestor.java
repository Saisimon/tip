package net.saisimon.leetcode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * 	Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 	According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * 	     ______6______
 *      /             \
 *   __2__           __8__
 *  /     \         /     \
 * 0      _4_      7       9
 *       /   \
 *      3     5	
 * 	For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
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
public class LowestCommonAncestor {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode min;
		TreeNode max;
		if (p.val > q.val) {
			min = q;
			max = p;
		} else {
			min = p;
			max = q;
		}
        if (root.val < min.val){
        	return lowestCommonAncestor(root.right, min, max);
        } else if (root.val > max.val) {
        	return lowestCommonAncestor(root.left, min, max);
        } else {
        	return root;
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

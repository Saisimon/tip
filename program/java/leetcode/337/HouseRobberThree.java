package net.saisimon.leetcode;

/**
 * <h1>337. House Robber III</h1>
 * 	<p>The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. 
 * 	After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * 
 * 	<p>Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * 
 * 	<p>Example 1:
 * 	<blockquote>
 * 	         3 <br>
 *		    / \ <br>
 * 		   2   3 <br>
 *  	    \   \ <br>
 *  	     3   1 <br>
 *  	Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *  </blockquote>
 *  
 *  <p>Example 2:
 *  <blockquote>
 *  	     3 <br>
 *		    / \ <br>
 *		   4   5 <br>
 * 		  / \   \ <br>
 *		 1   3   1 <br>
 *		Maximum amount of money the thief can rob = 4 + 5 = 9.
 *	</blockquote>
 *
 *	<p>Definition for a binary tree node.
 *	<pre> public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * } </pre>
 * 
 * @author Saisimon
 *
 */
public class HouseRobberThree {
	/*
	 * 动态规划递归求解
	 */
	public int rob(TreeNode root) {
		// 节点为空，返回 0
        if (root == null) {
        	return 0;
        }
        // 情况 1：选择当前节点 + 并加上当前节点的所有孙子节点的最优解
        int s1 = root.val;
        // 情况 2：不选择当前节点 ，选择当前节点所有的子节点的最优解的和
        int s2 = rob(root.left) + rob(root.right);
        if (root.left != null) {
        	s1 += rob(root.left.left);
        	s1 += rob(root.left.right);
        }
        if (root.right != null) {
        	s1 += rob(root.right.left);
        	s1 += rob(root.right.right);
        }
        // 在情况 1 和 情况 2 中选择最好的结果
        return Math.max(s1, s2);
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

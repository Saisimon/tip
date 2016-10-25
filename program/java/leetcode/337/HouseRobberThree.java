package net.saisimon.leetcode;

import java.util.HashMap;
import java.util.Map;

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
	 * 
	 * OJ - 980ms
	 */
	public int robA(TreeNode root) {
		// 节点为空，返回 0
        if (root == null) {
        	return 0;
        }
        // 情况 1：选择当前节点 + 并加上当前节点的所有孙子节点的最优解
        int s1 = root.val;
        // 情况 2：不选择当前节点 ，选择当前节点所有的子节点的最优解的和
        int s2 = robA(root.left) + robA(root.right);
        if (root.left != null) {
        	s1 += robA(root.left.left);
        	s1 += robA(root.left.right);
        }
        if (root.right != null) {
        	s1 += robA(root.right.left);
        	s1 += robA(root.right.right);
        }
        // 在情况 1 和 情况 2 中选择最好的结果
        return Math.max(s1, s2);
    }
	
	/*
	 * 动态规划 - 优化
	 * 解决子问题重复计算问题，通过一个集合存储已经计算的结果
	 * 
	 * OJ - 8ms
	 */
	public int robB(TreeNode root) {
		// 存储计算过的子问题
        Map<TreeNode, Integer> data = new HashMap<>();
        return subRobB(root, data);
    }
	
	private int subRobB(TreeNode root, Map<TreeNode, Integer> data) {
		if (root == null) {
        	return 0;
        }
		// 子问题计算过，直接返回结果，避免重复计算带来的时间消耗
		if (data.containsKey(root)) {
			return data.get(root);
		}
		int s1 = root.val;
        int s2 = subRobB(root.left, data) + subRobB(root.right, data);
        if (root.left != null) {
        	s1 += subRobB(root.left.left, data);
        	s1 += subRobB(root.left.right, data);
        }
        if (root.right != null) {
        	s1 += subRobB(root.right.left, data);
        	s1 += subRobB(root.right.right, data);
        }
        int val = Math.max(s1, s2);
        // 记录子问题的结果
        data.put(root, val);
        return val;
	}
	
	/*
	 * 动态规划 - 优化
	 * 去除重复的子问题
	 * 
	 * OJ - 2ms
	 */
	public int robC(TreeNode root) {
		int[] res = subRobC(root);
		return Math.max(res[0], res[1]);
	}
	
	private int[] subRobC(TreeNode root) {
		// 存储两个不同的结果，第一个存储不选择当前节点时的结果，第二位选择当前节点的情况
		int[] res = new int[2];
		if (root == null) {
			return res;
		}
		int[] left = subRobC(root.left);
		int[] right = subRobC(root.right);
		// 不选择当前节点，则只需要求得其左节点的最优解和右节点的最优解
		res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		// 如果选择了当前节点，则只需要获取当前值与不获取左节点的最优解与不获取右节点的最优解，即只可能是left[0]和right[0]的情况
		res[1] = root.val + left[0] + right[0];
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

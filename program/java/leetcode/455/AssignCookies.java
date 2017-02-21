package net.saisimon.leetcode;

import java.util.Arrays;

/**
 * <h1>455. Assign Cookies</h1>
 * 	<p>Assume you are an awesome parent and want to give your children some cookies. 
 * 	<p>But, you should give each child at most one cookie. 
 * 	<p>Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; 
 * 	<p>and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content.
 *  <p>Your goal is to maximize the number of your content children and output the maximum number.
 *  
 *  <p>Note:
 *  	You may assume the greed factor is always positive. 
 *  	You cannot assign more than one cookie to one child.
 *  
 *  <p>Example 1:
 *  	<blockquote>
 *  	Input: [1,2,3], [1,1] </br>
 *  	Output: 1 </br>
 *  	Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.  </br>
 *  	And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content. </br>
 *  	You need to output 1.
 *  	</blockquote>
 *  
 *  <p>Example 2:
 *  	<blockquote>
 *  	Input: [1,2], [1,2,3] </br>
 *  	Output: 2 </br>
 *  	Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. </br>
 *  	You have 3 cookies and their sizes are big enough to gratify all of the children, </br>
 *  	You need to output 2.
 *  	</blockquote>
 * 
 * @author Saisimon
 *
 */
public class AssignCookies {
	
	public int findContentChildren(int[] g, int[] s) {
		if (g.length == 0 || s.length == 0) {
        	return 0;
        }
		// 排序
        Arrays.sort(g);
        Arrays.sort(s);
		int res = 0;
		int i = 0, j = 0;
		// 遍历比较
		while (i < g.length && j < s.length) {
			if (g[i] <= s[j]) {
				i++;
				res++;
			}
			j++;
        }
		return res;
    }
	
}

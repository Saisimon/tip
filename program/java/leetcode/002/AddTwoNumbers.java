package net.saisimon.leetcode;

/**
 * <h1>2. Add Two Numbers</h1>
 * 
 * 	<p>You are given two non-empty linked lists representing two non-negative integers. 
 * 	The digits are stored in reverse order and each of their nodes contain a single digit. 
 * 	Add the two numbers and return it as a linked list.
 * 
 * 	<p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * 	<p>Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 	<p>Output: 7 -> 0 -> 8
 * 
 *	<p>Definition for singly-linked list.
 *	<code>
 * 	<br>public class ListNode {
 *     	<br>&nbsp; int val;
 *     	<br>&nbsp; ListNode next;
 *     	<br>&nbsp; ListNode(int x) { val = x; }
 * 	<br>}
 * </code>
 * 
 * @author Saisimon
 *
 */
public class AddTwoNumbers {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = new ListNode(-1);
        ListNode current = result;
        while (l1 != null || l2 != null) {
        	int n1 = l1 != null ? l1.val : 0;
        	int n2 = l2 != null ? l2.val : 0;
        	int sum = n1 + n2;
        	sum += carry;
        	if (sum > 9) {
        		sum %= 10;
        		carry = 1;
        	} else {
        		carry = 0;
        	}
        	if (l1 != null) {
        		l1 = l1.next;
        	}
        	if (l2 != null) {
        		l2 = l2.next;
        	}
        	current.next = new ListNode(sum);
        	current = current.next;
        }
        if (carry == 1) {
        	current.next = new ListNode(1);
        }
        return result.next;
    }
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
}

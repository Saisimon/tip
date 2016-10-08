package net.saisimon.leetcode;

/**
 * 206. Reverse Linked List
 * 	Reverse a singly linked list.
 * 
 * 	1 -> 2 -> 3 -> 4   =>   4 -> 3 -> 2 -> 1
 * 
 * 	Definition for singly-linked list.
 * 	public class ListNode {
 *     	int val;
 *     	ListNode next;
 *     	ListNode(int x) { val = x; }
 * 	}
 * 
 * @author Saisimon
 */
public class ReverseLinkedList {
	// 循环
	public ListNode reverseListA(ListNode head) {
		ListNode node = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = node;
			node = head;
			head = next;
		}
		return node;
    }
	
	// 递归
	public ListNode reverseListB(ListNode head) {
		return reverse(head, null);
    }
	
	private ListNode reverse(ListNode head, ListNode node) {
		if (head == null) {
			return node;
		}
		ListNode next = head.next;
		head.next = node;
		return reverse(next, head);
	}
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
}

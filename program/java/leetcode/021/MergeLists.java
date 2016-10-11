package net.saisimon.leetcode;

/**
 * 21. Merge Two Sorted Lists
 * 	Merge two sorted linked lists and return it as a new list. 
 * 	The new list should be made by splicing together the nodes of the first two lists.
 * 
 * 	1 -> 3 -> 5  +  2 -> 3 -> 4 -> 6  =>  1 -> 2 -> 3 -> 3 -> 4 -> 5 -> 6
 * 
 * 	Definition for singly-linked list.
 * 	public class ListNode {
 *     	int val;
 *     	ListNode next;
 *     	ListNode(int x) { val = x; }
 * 	}
 * 
 * @author Saisimon
 *
 */
public class MergeLists {
	public ListNode mergeTwoListsA(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		if (l1.val < l2.val) {
			l1.next = mergeTwoListsA(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoListsA(l1, l2.next);
			return l2;
		}
    }
	
	public ListNode mergeTwoListsB(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode next= head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				next.next = l1;
				next = l1;
				l1 = l1.next;
			} else {
				next.next = l2;
				next = l2;
				l2 = l2.next;
			}
		}
		if (l1 != null) {
			next.next = l1;
		}
		if (l2 != null) {
			next.next = l2;
		}
		return head.next;
	}
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
}

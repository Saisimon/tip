package net.saisimon.leetcode;

/**
 * 24. Swap Nodes in Pairs
 * 	Given a linked list, swap every two adjacent nodes and return its head.
 * 	For example,
 * 		Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 	Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
public class SwapNodes {
	public ListNode swapPairsA(ListNode head) {
		// head = 1->2->3->4
        if (head == null || head.next == null) {
        	return head;
        }
        // next = 2->3->4
        ListNode next = head.next;
        // head = 1->3->4
        head.next = next.next;
        // next = 2->1->3->4  head = 1->3->4
        next.next = head;
        // next = 2->1->4->3  head = 1->4->3
        head.next = swapPairsA(head.next);
        return next;
    }
	
	public ListNode swapPairsB(ListNode head) {
		// head = 1->2->3->4  dummy = 0
	    ListNode dummy = new ListNode(0);
	    // head = 1->2->3->4  dummy = 0->1->2->3->4
	    dummy.next = head;
	    // head = 1->2->3->4  dummy = 0->1->2->3->4  current = 0->1->2->3->4
	    ListNode current = dummy;
	    while (current.next != null && current.next.next != null) {
	    	// firse = 1->2->3->4
	        ListNode first = current.next;
	        // second = 2->3->4
	        ListNode second = current.next.next;
	        // firse = 1->3->4  current = 0->1->3->4  dummy = 0->1->3->4
	        first.next = second.next;
	        // current = 0->2->3->4  dummy = 0->2->3->4
	        current.next = second;
	        // current = 0->2->1->3->4  dummy = 0->2->1->3->4
	        current.next.next = first;
	        // current = 1->3->4  dummy = 0->2->1->3->4
	        current = current.next.next;
	    }
	    // dummy.next = 2->1->4->3
	    return dummy.next;
	}
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
}

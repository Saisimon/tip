package net.saisimon.leetcode;

/**
 * 83. Remove Duplicates from Sorted List
 * 	Given a sorted linked list, delete all duplicates such that each element appear only once.
 * 	For example,
 * 		Given 1->1->2, return 1->2.
 * 		Given 1->1->2->3->3, return 1->2->3.
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
public class RemoveDuplicates {
	public ListNode deleteDuplicates(ListNode head) {
		// head 为空，直接返回空
		if (head == null) {
			return null;
		}
        ListNode next = head.next;
        // head 的下一个节点为空，直接返回 head
        if (next == null) {
        	return head;
        }
        // 循环判断相同节点
        while (next.val == head.val) {
        	next = next.next;
        	if (next == null) {
        		break;
        	}
        }
        // 递归调用，获得 head 的下一个节点
        head.next = deleteDuplicates(next);
        return head;
    }
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
}

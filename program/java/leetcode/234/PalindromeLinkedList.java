package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. Palindrome Linked List
 * 	Given a singly linked list, determine if it is a palindrome.
 * 
 * 	Follow up:
 * 		Could you do it in O(n) time and O(1) space?
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
public class PalindromeLinkedList {
	// 使用列表存储数据，判断列表是否为回文
	public boolean isPalindromeA(ListNode head) {
		List<Integer> list = new ArrayList<>();
        while (head != null) {
        	list.add(head.val);
        	head = head.next;
        }
        int start = 0, end = list.size() - 1;
        while (start <= end) {
        	if (list.get(start).intValue() != list.get(end).intValue()) {
        		return false;
        	}
        	start++;
        	end--;
        }
        return true;
    }
	
	public boolean isPalindromeB(ListNode head) {
		if(head == null) {
            return true;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode p3 = p1.next;
        ListNode pre = p1;
        //find mid pointer, and reverse head half part
        while(p2.next != null && p2.next.next != null) {
            p2 = p2.next.next;
            pre = p1;
            p1 = p3;
            p3 = p3.next;
            p1.next = pre;
        }
        //odd number of elements, need left move p1 one step
        if(p2.next == null) {
            p1 = p1.next;
        }
        //compare from mid to head/tail
        while(p3 != null) {
            if(p1.val != p3.val) {
                return false;
            }
            p1 = p1.next;
            p3 = p3.next;
        }
        return true;
	}
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
}

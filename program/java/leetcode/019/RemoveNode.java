package net.saisimon.leetcode;

import java.util.Stack;

/**
 * 19. Remove Nth Node From End of List
 * 	Given a linked list, remove the n th node from the end of list and return its head.
 * 	
 * 	For example:
 * 		Given linked list: 1->2->3->4->5, and n = 2.
 * 		After removing the second node from the end, the linked list becomes 1->2->3->5.
 * 
 * 	Note:
 * 		Given n will always be valid.
 * 		Try to do this in one pass.
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
public class RemoveNode {
	// 使用栈存储节点，先进后出
	public ListNode removeNthFromEndA(ListNode head, int n) {
		// head 为空，直接返回 null
		if (head == null) {
			return head;
		}
		// 创建栈存储节点
        Stack<ListNode> stack = new Stack<>();
        ListNode tmp = head;
        // 循环存储节点
        while (tmp != null) {
        	stack.push(tmp);
        	tmp = tmp.next;
        }
        // 当要删除的为第一个节点的时候，直接返回 head.next
        if (stack.size() == n) {
        	return head.next;
        }
        ListNode res = null;
        int idx = 1;
        // 循环栈
        while (!stack.isEmpty()) {
        	ListNode pop = stack.pop();
        	if (idx == n) {
        		// 得到待删除节点的下一个节点
        		res = pop.next;
        	} else if (idx == n + 1) {
        		// 待删除节点的上一个节点的下一个节点指向待删除的下一个节点
        		pop.next = res;
        		// 所在的操作是直接操作 head 节点，所以删除完毕后直接跳出循环
        		break;
        	}
        	idx++;
        }
        return head;
    }
	
	// 使用两个指针，其间相距 n ，向前移动
	public ListNode removeNthFromEndB(ListNode head, int n) {
		ListNode first = new ListNode(0);
		// 两个指针 start, end
		ListNode start = first, end = first;
		first.next = head;
		// end 先前进 n + 1 步
		for (int i = 1; i <= n + 1; i++) {
			end = end.next;
		}
		// 两个指针同时前进
		while (end != null) {
			start = start.next;
			end = end.next;
		}
		// 删除待删除节点，将待删除节点的上一个节点的下一个节点指向待删除的下一个节点
		start.next = start.next.next;
		// 返回 head 节点
		return first.next;
	}
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
}
package net.saisimon.leetcode;

/**
 * <h1>25. Reverse Nodes in k-Group</h1>
 * 	<p>Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * 	
 * 	<p>k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * 	
 * 	<p>You may not alter the values in the nodes, only nodes itself may be changed.
 * 	
 * 	<p>Only constant memory is allowed.
 * 	
 * 	<p>For example,
 * 		<br>&nbsp; Given this linked list: 1->2->3->4->5
 * 		<br>&nbsp; For k = 2, you should return: 2->1->4->3->5
 * 		<br>&nbsp; For k = 3, you should return: 3->2->1->4->5
 * 
 * @author Saisimon
 *
 */
public class ReverseNodesInGroup {
	
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode temp = head;
		int count = k;
		ListNode res = null;
		while (temp != null) {
			ListNode sub = null;
			// 按组倒序
			while (temp != null && count != 0) {
				ListNode node = new ListNode(temp.val);
				if (sub != null) {
					node.next = sub;
				}
				sub = node;
				temp = temp.next;
				count--;
			}
			if (count == 0) {
				// 重新分组
				count = k;
			} else {
				// 将最后一组多出来的数据还原
				temp = sub;
				ListNode lastGroup = new ListNode(-1);
				while (temp != null) {
					ListNode node = new ListNode(temp.val);
					node.next = lastGroup.next;
					lastGroup.next = node;
					temp = temp.next;
				}
				sub = lastGroup.next;
			}
			// 按照顺序排列子链表
			if (res != null) {
				ListNode next = res;
				while (next.next != null) {
					next = next.next;
				}
				next.next = sub;
			} else {
				res = sub;
			}
		}
		return res;
    }
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
}

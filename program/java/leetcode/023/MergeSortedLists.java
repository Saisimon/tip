package net.saisimon.leetcode;

/**
 * <h1>23. Merge k Sorted Lists</h1>
 * 	<p>Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 	
 * 	<p>Definition for singly-linked list.
 * 	<br>public class ListNode {
 *     	<br>&nbsp; int val;
 *     	<br>&nbsp; ListNode next;
 *     	<br>&nbsp; ListNode(int x) { val = x; }
 * 	<br>}
 * 
 * @author Saisimon
 *
 */
public class MergeSortedLists {
	
	// A.递归分治归并求解
	public ListNode mergeKListsA(ListNode[] lists) {
		return partionA(lists, 0, lists.length - 1);
	}

	public ListNode partionA(ListNode[] lists, int start, int end) {
		if (start == end) {
			return lists[start];
		} else if (start < end) {
			int middle = (start + end) / 2;
			ListNode l1 = partionA(lists, start, middle);
			ListNode l2 = partionA(lists, middle + 1, end);
			ListNode head = new ListNode(-1);
			head.next = l1;
			ListNode temp = head;
			ListNode next = temp.next;
			while (next != null && l2 != null) {
				if (next.val >= l2.val) {
					temp.next = new ListNode(l2.val);
					temp.next.next = next;
					next = temp.next;
					l2 = l2.next;
				}
				temp = next;
				next = next.next;
			}
			if (l2 != null) {
				temp.next = l2;
			}
			return head.next;
		} else {
			return null;
		}
	}
	
	// B.递归分治递归求解
	public ListNode mergeKListsB(ListNode[] lists) {
		return partionB(lists, 0, lists.length - 1);
	}

	public ListNode partionB(ListNode[] lists, int start, int ende) {
		if (start == ende) {
			return lists[start];
		} else if (start < ende) {
			int middle = (start + ende) / 2;
			ListNode l1 = partionB(lists, start, middle);
			ListNode l2 = partionB(lists, middle + 1, ende);
			return mergeB(l1, l2);
		} else {
			return null;
		}
	}

	public ListNode mergeB(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		}
		if (l1.val < l2.val) {
			l1.next = mergeB(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeB(l1, l2.next);
			return l2;
		}
	}
	
	// C.遍历归并求解
	// Time Limit Exceeded
	@Deprecated
	public ListNode mergeKListsC(ListNode[] lists) {
		if (lists == null) {
			return null;
		}
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        boolean done = false;
        while (!done) {
        	int min = Integer.MAX_VALUE;
        	int position = -1;
        	for (int i = 0; i < lists.length; i++) {
            	if (lists[i] != null && min > lists[i].val) {
        			min = lists[i].val;
        			position = i;
            	}
            }
        	if (position != -1) {
        		lists[position] = lists[position].next;
        		temp.next = new ListNode(min);
            	temp = temp.next;
        	} else {
        		done = true;
        	}
        }
        return head.next;
    }
	
	// 遍历插入求解 
	// Time Limit Exceeded
	@Deprecated
	public ListNode mergeKListsD(ListNode[] lists) {
		if (lists == null) {
			return null;
		}
		ListNode head = new ListNode(-1);
        if (lists.length > 0) {
        	head.next = lists[0];
        }
		for (int i = 1; i < lists.length; i++) {
			ListNode node = lists[i];
			ListNode temp = head;
			ListNode next = temp.next;
			while (next != null && node != null) {
				if (next.val >= node.val) {
					temp.next = new ListNode(node.val);
					temp.next.next = next;
					next = temp.next;
					node = node.next;
				}
				temp = next;
				next = next.next;
			}
			if (node != null) {
				temp.next = node;
			}
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

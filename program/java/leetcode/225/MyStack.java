package net.saisimon.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * 	Implement the following operations of a stack using queues.
 * 		push(x) -- Push element x onto stack.
 * 		pop() -- Removes the element on top of the stack.
 * 		top() -- Get the top element.
 * 		empty() -- Return whether the stack is empty.
 * 	
 * 	Notes:
 * 		You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * 		Depending on your language, queue may not be supported natively. 
 * 		You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 * 		You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 * 
 * 	The class name of the Java function had been updated to MyStack instead of Stack.
 * 
 * @author Saisimon
 *
 */
public class MyStack {
	
	// 使用两个队列来实现堆栈，两个队列相互倒腾
	private Queue<Integer> a = new LinkedList<>();
	private Queue<Integer> b = new LinkedList<>();
	// 下次应该插入哪个队列
	private boolean isA = false;
	
	// Push element x onto stack.
    public void push(int x) {
    	if (isA) {
    		// 本次要插入到 a 队列时，将 a 中的元素倒腾到 b 队列，然后将元素插入至 a 队列中作为堆栈的栈顶
    		while (!a.isEmpty()) {
    			b.offer(a.poll());
    		}
    		a.offer(x);
    		isA = false;
    	} else {
    		// 反之亦然
    		while (!b.isEmpty()) {
    			a.offer(b.poll());
    		}
    		b.offer(x);
    		isA = true;
    	}
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (isA) {
        	// 下次要插入 a 队列时，删除 b 队列的唯一一个元素即删除栈顶元素
        	b.poll();
        	// 还原到上次插入时的状态
        	while (!a.isEmpty()) {
        		b.offer(a.poll());
        	}
        	if (!b.isEmpty()) {
        		a.offer(b.poll());
        	}
        	// 修改下次插入哪个队列
        	isA = false;
        } else {
        	// 反之亦然
        	a.poll();
        	while (!b.isEmpty()) {
        		a.offer(b.poll());
        	}
        	if (!a.isEmpty()) {
        		b.offer(a.poll());
        	}
        	isA = true;
        }
    }

    // Get the top element.
    public int top() {
    	if (isA) {
    		// 下次要插入 a 队列时，b 队列中的第一个元素也是唯一的元素就是堆栈的栈顶
    		return b.peek();
    	} else {
    		// 反之亦然
    		return a.peek();
    	}
    }

    // Return whether the stack is empty.
    public boolean empty() {
    	// 两个队列都为空时，堆栈才为空
		return a.isEmpty() && b.isEmpty();
    }
}

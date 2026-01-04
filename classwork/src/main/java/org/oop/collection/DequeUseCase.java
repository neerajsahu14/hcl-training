package org.oop.collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeUseCase {
	public static void main(String[] args) {
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offer(1);
		dq.offer(2);
		dq.offerLast(3);
		dq.offerFirst(4);
		System.out.println("Deque Elements:" + dq);
		int first = dq.peekFirst();	
		dq.pollFirst();
	}
}

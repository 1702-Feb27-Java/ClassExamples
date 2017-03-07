package com.revature.collections;

import java.util.PriorityQueue;
import java.util.Queue;

public class MyQueues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		q.add(5);
		q.add(7);
		q.add(3);
		
		Integer i;
		
		System.out.println("Size of queue: " + q.size());
		while (q.peek()!= null){
			System.out.println(q.poll());
		}
		System.out.println(q.poll());
		
		
		
		
	}

}

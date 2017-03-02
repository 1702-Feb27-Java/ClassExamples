package com.revature.collections;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyQueues {

	public static void main(String[] args) {
		
		Queue<Integer> q = new PriorityQueue<>();
		
		q.add(5);
		q.add(7);
		q.add(3);
		int i = 0;
		
		System.out.println("Size of queue: " + q.size());
		while(q.peek()!=null){
			System.out.println(i = q.poll());
		}
		System.out.println(q.poll());
		
		System.out.println("Last element is: " + i);
		
		
		q.add(5);
		q.add(7);
		q.add(3);
		Iterator it = q.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}

		
		
	}

}

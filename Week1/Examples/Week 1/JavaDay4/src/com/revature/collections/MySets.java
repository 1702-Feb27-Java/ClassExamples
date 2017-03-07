package com.revature.collections;

import java.util.HashSet;
import java.util.Set;

public class MySets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Sets example\n=====================");
		
		HashSet<Integer> s = new HashSet<>();
		
		//Integer i = new Integer(1);
		
		
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(2);
		s.add(1);
		s.add(10);
		s.add(7);
		
		System.out.println("Set size = " + s.size());
		
		for (Integer i : s){
			System.out.println(System.identityHashCode(i));
		}
	}

}

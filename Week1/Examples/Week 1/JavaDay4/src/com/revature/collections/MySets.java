package com.revature.collections;

import java.util.HashSet;
import java.util.Set;

public class MySets {

	public static void main(String[] args) {
		System.out.println("Sets example!\n====================");
		
		HashSet<Integer> s = new HashSet<>();
		
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		
		System.out.println(System.identityHashCode(i1));
		
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(2);
		s.add(1);
		s.add(10);
		s.add(7);
		

		System.out.println("Set size = " + s.size());
		
		
		for(Integer i : s){
			System.out.println(System.identityHashCode(i));
		}
		s.add(1);
		System.out.println();
		for(Integer i : s){
			System.out.println(System.identityHashCode(i));
		}
		
		System.out.println(s.remove(1));
		
	}

}

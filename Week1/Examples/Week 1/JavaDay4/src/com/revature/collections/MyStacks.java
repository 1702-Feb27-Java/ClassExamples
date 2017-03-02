package com.revature.collections;

import java.util.Stack;

public class MyStacks {

	public static void main(String[] args) {
		Stack<Integer> s = new Stack<>();
		s.push(2);
		s.push(5);
		s.push(3);
		
		//System.out.println(s.pop());
		
		s.remove(1);
		System.out.println(s.pop());
		System.out.println(s.pop());
		
		//s.iterator();
		
		System.out.println("Argument count: " + args.length);
		for(int i = 0; i <args.length; i++){
			System.out.println(args[i]);
		}
		
	}

}

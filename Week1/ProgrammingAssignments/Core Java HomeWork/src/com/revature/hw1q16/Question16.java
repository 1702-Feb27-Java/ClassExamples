package com.revature.hw1q16;

public class Question16 {
	
	public static void main(String[] args) {
		int count = 0;
		for (int j = 0; j < args.length; j++) {
			count+=args[j].length();
		}
		System.out.println(count);
		
	}

}

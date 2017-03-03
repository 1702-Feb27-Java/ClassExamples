package com.revature.hw1q3;

public class Question3 {
	
	public static void main(String[] args) {
		System.out.println(reverseString("reverse with no temp string"));
	}
	
	public static String reverseString(String s){
		
		for(int i =s.length()-1; i >= 0; i--){
			s = s+ s.charAt(i);
		}

		s = s.substring(s.length()/2);
		
		return s;
	}

}

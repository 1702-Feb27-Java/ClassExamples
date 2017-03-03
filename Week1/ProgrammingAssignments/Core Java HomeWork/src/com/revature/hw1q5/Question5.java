package com.revature.hw1q5;

public class Question5 {
	
	public static void main(String[] args) {
		System.out.println(subString("Half of string", 6));
	}

	public static String subString(String s, int n){
		
		String sub = "";
		
		for(int i = 0; i <= n; i++){
			sub += s.charAt(i);
		}
		
		return sub;
	}
}

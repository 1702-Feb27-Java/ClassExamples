package com.revature.hw1q10;

public class Question10 {
	
	public static void main(String[] args) {
		Question10.min(2, 3);
	}
	
	public static void min(int n1, int n2){
		int min = n1 < n2 ? n1 : n2;
		System.out.println(min);
	}

}

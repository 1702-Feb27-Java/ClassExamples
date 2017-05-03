package com.revature.hw1q6;

public class Question6 {
	
	public static void main(String[] args) {
		isEven(12);
		isEven(15);
	}
	
	
	public static void isEven(int n){
		//uses int to find if even
		int temp = n;
		n = (n/2)*2;
		
		if(n == temp){
			System.out.println("Even");
			
		}
		else
			System.out.println("Not even");
		
	}

}

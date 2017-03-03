package com.revature.hw1q2;

public class Question2 {
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 25; i++){
			System.out.println(fib(i));
		}
		
	}

	public static int fib(int n){
		
		if(n == 1){
			return 1;
		}
		if(n == 0){
			return 0;
		}
		
		return fib(n-1) + fib(n-2);
	}
	
	public static void addFib(int n){
		int sum = 0;
		for(int i =0; i< n; i++){
			sum += fib(i);
		}
		
		System.out.println(sum);
	}
}

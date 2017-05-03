package com.revature.hw1q4;

public class Question4 {
	
	public static void main(String[] args) {
		System.out.println(fac(10));
	}

	public static int fac(int n){
		
		if(n == 1)
			return 1;
		if(n == 0)
			return 0;
		
		return fac(n-1)*n;
	}
	
}

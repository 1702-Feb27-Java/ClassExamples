package com.revature.Q2;

public class Q2 {

	public static void main(String[] args) {
		int numberOfValues = 25;
		for (int n = 0; n < numberOfValues; n++) { //loop to output n fibonacci values 
			System.out.println(fibonacci(n));
		}//for
	}// main

	public static int fibonacci(int n) {
		if (n == 0) { 
			return 0; //return 0 if zero is given
		}
		if (n <= 2) {
			return 1; //return 1 if value is 2 or less
		}											//fib(n-1)+fib(n-2)
		return fibonacci(n - 1) + fibonacci(n - 2); //return fib value of previous and second previous											
	}// fibonacci
}// Q2

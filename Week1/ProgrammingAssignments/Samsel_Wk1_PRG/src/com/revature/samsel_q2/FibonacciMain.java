package com.revature.samsel_q2;

/*
 * Question: Program to display the first and 25 Fibonacci numbers beginning from 0
 */
public class FibonacciMain {

	public static void main(String[] args) {
		
		int i = 25;
		FibonacciC fNumbers = new FibonacciC(i);
		System.out.println("=======FIBONACCI NUMBERS UPTO "+i+"==========");
		fNumbers.ExecuteF();
	}

}

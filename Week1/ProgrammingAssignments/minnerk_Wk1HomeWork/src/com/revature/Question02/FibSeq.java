package com.revature.Question02;

public class FibSeq {
	
	public static void main(String[] args){
		
		//Declaration of two number values that are added together for Fibonocci Series
		int num1 = 0;
		int num2 = 1;
		
		//Prints out zero, the first number in the series
		System.out.println(num1);
		
		//Iteration from 0 to 24 for the other 24 numbers of the first 25 Fib Numbers
		for (int i = 0; i < 24;i++){
				System.out.println(num2);
				int temp = num2;  //holds number twos value
				num2 += num1;  //totals number 2 and number ones value
				num1 = temp; //Puts what the variable num2 had into num1
		}
	}
}

package com.homework1.question10;

public class MinimumNumbers {

	public static void main(String[] args) {
		System.out.println(ternaryMin(10, 13));		
	}
	
	public static int ternaryMin(int number1, int number2){
		//compare 2 integers with ternary, returns the first 1 if
		//it less and the second if it is greater than the first
		int minimum = number1 < number2 ? number1 : number2;
		return minimum;
	}

}

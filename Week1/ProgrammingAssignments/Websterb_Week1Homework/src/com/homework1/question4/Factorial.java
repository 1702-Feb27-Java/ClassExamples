package com.homework1.question4;

public class Factorial {

	public static void main(String[] args) {
		System.out.println(factorial(5));
	}
	
	/**
	 * 
	 * @param start the starting number
	 * @return the final total factorial
	 */
	public static int factorial(int start){
		// start the total at 1 (0 would make it always 0)
		int total = 1;
		//iterate through from the starting value down to 0 subtracting 1 each time
		for(int i = start; i > 0; i--){
			total *= i;//multiply the current total with the new value
		}
		return total;
	}

}

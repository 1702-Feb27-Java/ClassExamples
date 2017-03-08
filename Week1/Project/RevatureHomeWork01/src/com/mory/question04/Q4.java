package com.mory.question04;

public class Q4 {
	public static void main(String[] args){
		System.out.println(factorial(3));
		
	}
	/***
	 * This methods takes a number and return 
	 * its factorial.
	 * </p>
	 * Since the recursive method takes to long to 
	 * compute so I use I used a for loop.
	 * @param number The factorial to evaluate
	 * @return value of the evaluated factorial
	 */
	
	public static long factorial(long number){
		long fact=1;
		for(int i=1;i<=number;i++){
			fact*=i;
		}
		return fact;
		
	}
	

}

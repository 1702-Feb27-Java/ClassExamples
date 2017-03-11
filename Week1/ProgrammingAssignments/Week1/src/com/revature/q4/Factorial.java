package com.revature.q4;
/**
 * 
 * @author Aaron Camm
 *
 */
public class Factorial {
	
	public static void main(String [] args){
		Factorial f = new Factorial();
		System.out.println(f.fact(20));
	}
	
	/***
	 * Gives a factorial number
	 * @param n 
	 * @return a number the represents n!
	 */
	public long fact(int n){
		
		long result = 1;
		
		//multiplies each number from 1 to n with the number result
		for (int i = 1; i <= n; i++){
			result *= i;
		}
		return result;
		
		
	}
}

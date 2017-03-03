package com.revature.q6;
/**
 * 
 * @author Aaron Camm
 *
 */
public class EvenOrOdd {
	
	public static void main(String args[]){
		EvenOrOdd evenOrOdd = new EvenOrOdd();
		int n = -6;
		boolean b = evenOrOdd.isEven(n);
		String value = b ? "even" : "odd";
		
		System.out.println(n + " is " + value);
		
	}
	
	
	/***
	 * Determines if integer is even
	 * 
	 * @param n - integer to be determined if even
	 * @return true if n is even, false if n is odd
	 */
	public boolean isEven(int n) {
		//divides n by 2, if n is odd it will lose decimal 
		//since n2 is an integer
		int n2 = n / 2; 
		
		//compares multiplies n2, if its odd, it will be different then n. 
		if (n == n2*2){
			return true;
		} else {
			return false;
		}
	}
	
	
}

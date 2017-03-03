package com.revature.q2;
/**
 * 
 * @author Aaron Camm
 *
 */
public class FibonacciNumbers {

	
	public static void main(String[] args){
		int array[] = fib(25);
		
		//prints array;
		for(int i = 0; i < array.length; ++i ){
			System.out.print(array[i] + " ");
		}
	}
	
	
	/***
	 * Generates an array with a Fibonacci sequence 
	 * 
	 * 
	 * @param n - the number of Fibonacci numbers to create. Must be greater then 0.
	 * @return an array showing the the fibonacci numbers up to length n;
	 */
	public static int[] fib(int n){
		
		//single variable sequence, no need for building the array.
		if (n == 1){
			return new int[]{0};
		} else {
			
			//creates a array for the fibonacci numbers to be put it.
			int[] seq = new int[n];
			
			//inital values for fibonacci numbers;
			seq[0] = 0;
			seq[1] = 1;
			
			//iterates through the array, adding the two previous numbers together to create the next number
			//also doubles as a case if n is 2, loop is skipped.
			for(int i = 2; i < n; ++i){
				seq[i] = seq[i-1] + seq[i-2];
			}
			
			
			return seq;
		}
		
		
	}
}

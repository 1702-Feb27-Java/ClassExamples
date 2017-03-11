package com.revature.q10;
/**
 * 
 * @author Aaron Camm
 *
 */
public class FindMin {
	public static void main(String[] args){
		System.out.println(FindMin.getMin(1,2));
	}
	
	/**
	 * returns the min of two numbers
	 * @param a - integer to test
	 * @param b - integer to test
	 * @return a if a is less then b, otherwise b
	 */
	public static int getMin(int a, int b){
		return (a < b) ? a : b;
		
		
	}
}

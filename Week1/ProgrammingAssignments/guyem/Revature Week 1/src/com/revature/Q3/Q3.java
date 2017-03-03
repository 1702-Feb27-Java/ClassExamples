package com.revature.Q3;

public class Q3 {
	public static void main(String[] args) {
		String[] arr = { "a", "b", "c", "d", "e" };
		reverseString(arr); //should output [e, d, c, b, a]
	}//main

	public static void reverseString(String[] arr) {
		int lengthOfArray = arr.length;
		for(int i=lengthOfArray-1; i>=0; i--){ //start w/ the end of array and subtract index values
			System.out.print(arr[i]+" ");
		}
	}//reverseString
}//Q3
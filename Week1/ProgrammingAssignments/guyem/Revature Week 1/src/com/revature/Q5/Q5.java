package com.revature.Q5;

public class Q5 {

	public static void main(String[] args) {
		System.out.println("String: hello world");
		try{
			subString("hello world", 7); //should output hello w
		}catch(IndexOutOfBoundsException e){
			System.out.println("Index goes pass the range.");
		}
	}// main

	public static void subString(String str, int idx) {
		int start = 0; // change the starting point
		int length = str.length();
		// check if idx is greater than string length
		if (idx > length) {
			System.out.println("Cannot compute. Number value is larger than string length.");
			return;
		} // if
		for (int i = start; i < idx; i++) {
			System.out.print(str.charAt(i) + " ");
		} // for
	}// subString

}// Q5

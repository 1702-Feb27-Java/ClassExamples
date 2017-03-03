package com.revature.q05;
/**
 * Program that creates a custom substring that returns
 * a substring from index 0 up to that number inclusive
 * @author Nick
 *
 */
public class CustomString {

	public static void main(String[] args) {
		String day = "Second Day!";
		
		//using custom method
		String final_str = Substring(day, 5);
		
		//printing changed string
		System.out.println(final_str);

	}
	
	//Custom substring method that includes an inclusive ending
	static String Substring(String str, int a) {
		//local variable str I.E Method scope
		String temp = "";
		
		for ( int i = 0; i < a; i++) {
			temp = temp + str.charAt(i);
		}
		return temp;
	}
}

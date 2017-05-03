package com.revature.q10;

import java.util.Scanner;

/**
 * This class will demonstrate finding the minimum of two numbers using
 * ternary operators
 * @author Nick
 *
 */
public class MinNumber {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		//Asking user for input
		System.out.print("Enter your first number: ");
		int num1 = keyboard.nextInt();
		
		System.out.print("Enter your next number: ");
		int num2 = keyboard.nextInt();
		
		MinNumCheck(num1, num2);
		
		keyboard.close();
	}
	
	/**
	 * This function will check for the minimum of two numbers
	 * Note: it will do this using ternary operators
	 * @param a int to compare to b
	 * @param b int to compare to a
	 */
	
	static void MinNumCheck(int a, int b) {
		//local varaible min_val
		int min_val = (a < b) ? a : b;
		System.out.println("The minimun value is " + min_val);
	}

}

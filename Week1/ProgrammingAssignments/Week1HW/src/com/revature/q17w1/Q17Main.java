// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 17 - WRITE A PROGRAM THAT CLACULATES THE SIMPLE INTEREST ON THE PRINCIPAL,
// RATE OF INTEREST AND NUMBER OF YEARS PROVIDED BY THE USER. ENTER PRINCIPAL, RATE, AND TIME
// THROUGH THE CONSOLE USING THE SCANNER CLASS.

package com.revature.q17w1;

import java.util.Scanner;

public class Q17Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input1 = new Scanner(System.in);  // Reading from System.in
		System.out.println("Pricipal: ");  // Prompts user to input a number
		double prin = input1.nextDouble();
		
		Scanner input2 = new Scanner(System.in);  // Reading from System.in
		System.out.println("Interest rate: ");  // Prompts user to input a number
		double rate = input2.nextDouble();
		
		Scanner input3 = new Scanner(System.in);  // Reading from System.in
		System.out.println("Number of years: ");  // Prompts user to input a number
		int years = input3.nextInt();
		
		calcInterest(prin, rate, years); // calls the method to calculate interest
		
	}
	
	public static void calcInterest (double a, double b, int c){  // the method that calculates interest
		// a is principal, b is rate, and c is time
		double b2 = b/100 + 1; // rate should be entered as 5.8%, so we converted to 1.058
		double result = a * b2 * c; // the equation to find interest
		System.out.println(result);
	}

}

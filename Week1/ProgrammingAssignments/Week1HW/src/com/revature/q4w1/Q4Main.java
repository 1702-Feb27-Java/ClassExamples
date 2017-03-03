// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 4 - WRITE A PROGRAM TO COMPUTE N FACTORIAL

package com.revature.q4w1;
import java.util.Scanner; 


public class Q4Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");  // Prompts user to input a number
		long num = input.nextInt();		// User input is saved as int num
		
		FactClass fact = new FactClass();  // Creates a new object from the factorial class
		fact.factCalc(num); // calculates the factorial
	}

}

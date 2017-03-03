// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 10 - FIND THE MINIMUM OF TWO NUMBERS USING THE TERNARY OPERATORS

package com.revature.q10w1;
import java.util.Scanner; 


public class Q10Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input1 = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");  // Prompts user to input a number
		double num1 = input1.nextDouble();		// User input is saved as int num
		
		Scanner input2 = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter another number: ");  // Prompts user to input a number
		double num2 = input2.nextDouble();		// User input is saved as int num
		
		FindMin.min(num1, num2);  // calls the method to find min
	}

}

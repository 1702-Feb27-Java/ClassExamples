// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 10 - FIND THE MINIMUM OF TWO NUMBERS USING THE TERNARY OPERATORS

package com.revature.q10w1;

public class FindMin {
	
	public static void min (double a, double b) {  // method to find the min between 2 numbers
		double result = (a < b) ? a : b;  // this uses the ternary operator to check for min
		System.out.println("The minimum is " + result);
	}

}

// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 15 - WRITE A PROGRAM THAT DEFINES AN INTERFACE HAVING THE FOLLOWING METHODS: ADDITION,
// SUBTRACTION, MUILTIPLICATION, AND DIVISION. CREATE A CLASS THAT IMPLEMENTS THIS INTERFACE AND PROVIDES
// APPROPRIATE FUNCTIONALITY TO CARRY OUT THE REQUIRED OPERATIONS. HARD CODE TWO OPERANDS IN A TEST CLASS
// HAVING A MAIN METHOD THAT CALLS THE IMPLEMENTING CLASS.

package com.revature.q15w1;

public interface OperatorInter {
	
	// this is the interface with the 4 math methods
	public double addition(double a, double b);
	public double subtraction(double a, double b);
	public double multiplication(double a, double b);
	public double division(double a, double b);
	
}

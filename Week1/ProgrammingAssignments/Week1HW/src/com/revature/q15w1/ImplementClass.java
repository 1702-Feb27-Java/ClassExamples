// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 15 - WRITE A PROGRAM THAT DEFINES AN INTERFACE HAVING THE FOLLOWING METHODS: ADDITION,
// SUBTRACTION, MUILTIPLICATION, AND DIVISION. CREATE A CLASS THAT IMPLEMENTS THIS INTERFACE AND PROVIDES
// APPROPRIATE FUNCTIONALITY TO CARRY OUT THE REQUIRED OPERATIONS. HARD CODE TWO OPERANDS IN A TEST CLASS
// HAVING A MAIN METHOD THAT CALLS THE IMPLEMENTING CLASS.

package com.revature.q15w1;

public class ImplementClass implements OperatorInter { // this is the class that implements the interface

	// so now we will write what each operator actually does
	@Override
	public double addition(double a, double b) {
		// TODO Auto-generated method stub
		double sum = a + b;
		return sum;
	}

	@Override
	public double subtraction(double a, double b) {
		// TODO Auto-generated method stub
		double diff = a - b;
		return diff;
	}

	@Override
	public double multiplication(double a, double b) {
		// TODO Auto-generated method stub
		double prod = a * b;
		return prod;
	}

	@Override
	public double division(double a, double b) {
		// TODO Auto-generated method stub
		double quotient = a/b;
		return quotient;
	}

	
	
}

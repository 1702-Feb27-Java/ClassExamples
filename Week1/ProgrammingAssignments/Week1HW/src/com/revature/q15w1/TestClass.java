// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 15 - WRITE A PROGRAM THAT DEFINES AN INTERFACE HAVING THE FOLLOWING METHODS: ADDITION,
// SUBTRACTION, MUILTIPLICATION, AND DIVISION. CREATE A CLASS THAT IMPLEMENTS THIS INTERFACE AND PROVIDES
// APPROPRIATE FUNCTIONALITY TO CARRY OUT THE REQUIRED OPERATIONS. HARD CODE TWO OPERANDS IN A TEST CLASS
// HAVING A MAIN METHOD THAT CALLS THE IMPLEMENTING CLASS.

package com.revature.q15w1;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementClass newClass = new ImplementClass();
		
		double a = 19.5;  // hard coding operands
		double b = 5.3;
		
		System.out.println(newClass.addition(a, b));  // calls operation and prints
		System.out.println(newClass.subtraction(a, b));
		System.out.println(newClass.multiplication(a, b));
		System.out.println(newClass.division(a, b));
	}

}

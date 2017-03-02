package com.homework1.question15;

public class Main {

	public static void main(String[] args) {
		//create a MathFunctions object instance of the class
		MathFunctions mathTest = new MathFunctions();
		
		int a = 6;
		int b = 3;
		
		//print out the results of the functions
		System.out.println(a + " + " + b + " = " + mathTest.addition(a, b));
		System.out.println(a + " - " + b + " = " + mathTest.subtraction(a, b));
		System.out.println(a + " * " + b + " = " + mathTest.multiplication(a, b));
		System.out.println(a + " / " + b + " = " + mathTest.division(a, b));
	}

}

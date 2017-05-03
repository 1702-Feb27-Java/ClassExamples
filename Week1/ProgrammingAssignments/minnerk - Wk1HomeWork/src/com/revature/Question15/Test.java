package com.revature.Question15;

public class Test {

	public static void main(String[] args) {

		//Hard coded integers
		int num1 = 45;
		int num2 = 13;
		
		MathSubjectInterface msi = new MathSubjects(); //Creation of interface object
		
		//Printing and calls to additon, subtraction, multiplication, and division from MathSubjects
		System.out.println(msi.additon(num1, num2));
		System.out.println(msi.subtraction(num1, num2));
		System.out.println(msi.multiplication(num1, num2));
		System.out.println(msi.division(num1, num2));
		
	}

}

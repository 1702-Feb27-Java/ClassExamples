package com.revature.q15;

/**
 * 
 * @author tobon
 * Tests our addition, sub, mult, div functions from our class Calculator
 * that implemented CalcFunctions
 */
public class Question15testclass
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Calculator cal = new Calculator();
		System.out.println("Addition (5+4): " + cal.addition(5,4));
		System.out.println("Subtraction (40-4): " + cal.subtraction(40,4));
		System.out.println("Multiplication (5*9): " + cal.multiplication(5,9));
		System.out.println("Division (7/4): " + cal.division(7,4));

	}

}

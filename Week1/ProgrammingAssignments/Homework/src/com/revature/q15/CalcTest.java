package com.revature.q15;
/**
 * This program will check the operators of a calculator from
 * an interface. Check interface Calc operators, then calculators for the 
 * implementation.
 * @author Nick
 *
 */
public class CalcTest {

	public static void main(String[] args) {
		//hard testing the addition and division
		Calculators cal = new Calculators();
		
		System.out.println( cal.Addition(21, 11) );
		System.out.println( cal.Divide(25, 5));

	}

}

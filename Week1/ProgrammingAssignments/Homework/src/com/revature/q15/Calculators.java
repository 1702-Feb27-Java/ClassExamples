package com.revature.q15;

public class Calculators implements CalcOperators {

	public int Addition(int a, int b) {
		return a + b;
	}

	public int Substraction(int a, int b) {
		return a - b;
	}

	public int Multiply(int a, int b) {
		return a * b;
	}

	public int Divide(int a, int b) {
		try {
			return a / b;
			
		}
		catch (ArithmeticException e ) {
			return 0;
		}
	}

}

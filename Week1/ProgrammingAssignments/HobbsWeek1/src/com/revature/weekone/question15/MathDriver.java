package com.revature.weekone.question15;

/**
 * Tests the Math class.
 * 
 * @author Michael Hobbs
 *
 */
public class MathDriver {

	public static void main(String[] args) {
		// initialize some numbers to test the Math class with
		int a = 15, b = 10;
		double c = 5.0, d = 2.0;
		
		// initialize the Math class in order to perform operations
		Math math = new Math();
		
		// perform some operations of the Math class with the initialized numbers
		System.out.println("add(" + a + ", " + b + "): " + math.add(a, b));
		System.out.println("subtract(" + a + ", " + b + "): " + math.subtract(a, b));
		System.out.println("multiply(" + a + ", " + b + "): " + math.multiply(a, b));
		System.out.println("divide(" + c + ", " + d + "): " + math.divide(c, d));
		
	}

}

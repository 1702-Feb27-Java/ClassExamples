package com.revature.weekone.question4;

/**
 * Computes the n-th factorial.
 * 
 * @author Michael Hobbs
 *
 */
public class Question4 {
	
	/**
	 * Computes n!
	 * 
	 * @param n the factorial to be computed
	 * @return the factorial of n
	 */
	public static long computeFactorial(int n) {
		long nFactorial = 1; //start the factorial at 1
		
		for (int i = 2; i <= n; i++) { //run through up to n in order to produce the factorial
			nFactorial *= i; //multiply as we go in order to produce the factorial 
		}
		
		return nFactorial; //return the factorial produced
	}

	/**
	 * Produces and prints out the n-th factorial.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		int n = 10; //the factorial to be produced
		
		long nFactorial = computeFactorial(n); //compute the factorial
		
		System.out.println("Computed the " + n + "! factorial as " + nFactorial); //print out the factorial produced
		
	}

}

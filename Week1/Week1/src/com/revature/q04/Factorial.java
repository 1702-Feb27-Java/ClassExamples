package com.revature.q04;

public class Factorial {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// init a test input
		int testInput = 5;
		// create instance of Factorial
		Factorial f = new Factorial();
		// run factorial method on f using test input
		System.out.println(f.factorial(testInput));
		

	}
	
	/**
	 * 
	 * @param n	number to be factorialized
	 * @return total
	 */
	public long factorial(int n){
		// init result to 1
		// (temporarily serves as multiplicative identity)
		long result = 1;
		// recursively apply multiplication to decremented n
		while(n > 1){
			result = result*n;
			n -= 1;
		}
		return result;
	}

}

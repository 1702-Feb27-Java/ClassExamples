// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 4 - WRITE A PROGRAM TO COMPUTE N FACTORIAL

package com.revature.q4w1;

public class FactClass {
	
	void factCalc(long n){  // calculating the factorial method
		
		long ninit = n;  //initialize values
		long prod;
		long init = n;
		
		do {		
			prod = init*(n-1);  // find the product of the current value and the value-1
			init = prod; // dump the product into the init var
			n--;  // decrements n
		} while (n > 1);  // we don't need to multiply by 1
		
		System.out.println("The factorial of " + ninit + " factorial is " + prod);
	}

}

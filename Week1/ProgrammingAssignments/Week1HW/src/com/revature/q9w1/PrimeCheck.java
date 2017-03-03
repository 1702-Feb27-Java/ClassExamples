// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 9 - CREATE AN ARRAYLIST WHICH STORESS NUMBERS FROM 1 TO 100 AND PRINTS OUT
// ALL THE PRIME NUMBERS TO THE CONSOLE

package com.revature.q9w1;

public class PrimeCheck {
	
	public static boolean isPrime(int a){ // method to check for a prime number
		int b = a-1;
		
		if (a == 1){  // we know 1 isn't a prime
			return false;
		}
		
		if (a == 2) {  // and we know 2 is a prime
			return true;
		}
		
		else {  // for all numbers bigger than 2... 
		do {  // let's implement a do-while loop
			
			if (a%b == 0) {  // using the modulo operator to check
				return false;
			}
			b--;  		// checking every number from a-1, decrementing
		} while (b>1);  // ... to 2
		
		return true; // if any of the above conditions fail, then it's a prime
		}
	}

}

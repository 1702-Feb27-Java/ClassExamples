// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 6 - WRITE A PROGRAM TO DETERMINE IF AN INTEGER IS EVEN WITHOUT USING THE MODULUS OPERATOR (%)

package com.revature.q6w1;

public class EvenOddCheck { 

	public static boolean even (int num) {  // this only checks if num is even
		if ((num & 1) == 0) {   // performs bitwise AND operator
			return true;		// true -> num is even
		}
		else {
			return false;		// false -> num is odd
		}
	}
	
}

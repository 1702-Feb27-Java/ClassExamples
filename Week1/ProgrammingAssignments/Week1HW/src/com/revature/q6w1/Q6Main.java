// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 6 - WRITE A PROGRAM TO DETERMINE IF AN INTEGER IS EVEN WITHOUT USING THE MODULUS OPERATOR (%)

package com.revature.q6w1;

public class Q6Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a = 9;
		boolean check = EvenOddCheck.even(a);  // we call the method to check for even and odd
		
		if (check == true){  // the method checks for EVEN number
			System.out.println("The number " + a + " is even.");
		}
		else {
			System.out.println("The number " + a + " is odd.");
		}
		
	}

}

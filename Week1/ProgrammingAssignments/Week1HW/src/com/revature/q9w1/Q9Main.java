// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 9 - CREATE AN ARRAYLIST WHICH STORESS NUMBERS FROM 1 TO 100 AND PRINTS OUT
// ALL THE PRIME NUMBERS TO THE CONSOLE

package com.revature.q9w1;

import java.util.ArrayList;

public class Q9Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> arrNum = new ArrayList<Integer>();  // let's initiate an array list
		
		for (int i = 0; i < 100; i++){  // let's fill the array list with numbers 1-100
			
			int num = i+1;
			arrNum.add(num);  // add to the array list
			
			boolean prime = PrimeCheck.isPrime(num);  // let's check if the number added to the list is a prime
			//System.out.println(prime);
			
			if (prime == true){           // if the number passes the prime check
				System.out.println(num);  // then we print the number out on the console
			}
		}
		
		// System.out.println(arrNum);
	}

}

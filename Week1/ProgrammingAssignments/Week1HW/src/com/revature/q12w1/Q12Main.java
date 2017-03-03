// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 12 - WRITE A PROGRAM TO STORE NUMBERS FROM 1 T0 100 IN AN ARRAY. PRINT OUT ALL
// THE EVEN NUMBERS FROM THE ARRAY. USE THE ENHANCED FOR LOOP FOR PRINTING OUT THE NUMBERS.

package com.revature.q12w1;

import com.revature.q6w1.*;
import java.util.Arrays;

public class Q12Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a = 100;  // initialize 100
		loopEvenPrint(a);  // calls the method
		
	}
	
	public static void loopEvenPrint (int num){  // method to print even numbers
		
		int[] arrNum; // initialize
		arrNum = new int[num];
		
		for (int i = 0; i < num; i++){ // we make an array of 1-n numbers in an array
			arrNum[i] = i+1;
		}
		
		for (int i : arrNum){  // this is the enhanced for loop
			
			boolean check = EvenOddCheck.even(i);  // this is imported from the Even Check Function in the Q6 package
			
			if (check == true){				// so if this number is even
				System.out.println(i);		// then we print it out
			}	
		}
	}
}

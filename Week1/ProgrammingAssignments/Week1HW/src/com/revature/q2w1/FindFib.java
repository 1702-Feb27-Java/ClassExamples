// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 2 - WRITE A PROGRAM TO DISPLAY THE FIRST 25 FIBONACCI NUMBERS BEGINNING AT 0

package com.revature.q2w1;

import java.util.Arrays;

public class FindFib {  // class that contains the fibonacci method
	
	void fibcalc(int[] array){  // finding the fibonacci numbers method
		
		int i = 0;
		do {  // this is the do-while loop
			int sum = array[i] + array[i+1];  // adds up the 2 numbers before
			array[i+2] = sum;  // dump the sum into the next number
			i++;
		} while (i <23);
		
		System.out.println(Arrays.toString(array)); // printing
		System.out.println("There are " + array.length + " numbers in this Fibonacci sequence.");
		
	}

}

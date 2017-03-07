// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 13 - DISPLAY THE TRIANGLE ON THE CONSOLE AS FOLLOWS USING ANY TYPE OF LOOP.
// DO NOT USE A SIMPLE GROUP OF PRINT STATEMENTS TO ACCOMPLISH THIS.

package com.revature.q13w1;

import java.util.Arrays;

public class Q13Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "0";  // we start with 0
		System.out.println(s);  // prints it
		
		for (int i = 0; i < 3; i++){
			
			if (i == 0){ 
				s = "1" + s; // we add 1 to the left
				System.out.println(s); // print
			}
			if (i == 1){
				s = s + "1";  // add 1 to the right
				System.out.println(s); // print
			}
			if (i == 2){
				s = "0" + s; // add 0 to the left
				System.out.println(s); // print
			}
		}
	}

}

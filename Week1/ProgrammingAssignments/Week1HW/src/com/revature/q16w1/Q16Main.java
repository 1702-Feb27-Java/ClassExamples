// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 16 - WRITE A PROGRAM TO DISPLAY THE NUMBER OF CHARACTERS FOR A STRING INPUT. THE STRING
// SHOULD BE ENTERED AS A COMMAND LINE ARGUMENT USING (STRING[] ARGS).

package com.revature.q16w1;

import java.util.Arrays;

public class Q16Main {
	
	public static void main (String[] args){
		
		int numChar;
		
		String[] s = args;  // we store the command line args into a string array
		numChar = s.length; // find how many arguments there are
		
		for (int i = 0; i<args.length; i++){
			numChar += args[i].length();  // we count the number of characters for each argument/word
		}
		
		System.out.println(Arrays.toString(s));  // print out the args that was put into a command line
		
		System.out.println(numChar);  // print out the number of char
	}

}

// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 3 - REVERSE A STRING WITHOUT USING A TEMPORARY VARIABLE. 
// DO NOT USE REVERSE() IN THE STRINGBUFFER OR THE STRINGBUILDER APIS.

package com.revature.q3w1;

public class ReverseString {
	
	public static void reverse (String str){  // this is the actual reverse string method
		
		String revStr = new String();  // initialize a new reversed string
		
		int len = str.length();  // find the length of that string
				
		while (len > 0){  // while loop runs while len is bigger than 0
			revStr += str.charAt(len-1);  // populates the string with characters indexing from the right of the original string
			len--;  // decrements the count
		}
		
		System.out.println(revStr);  // prints out the reversed string
		
	}
}

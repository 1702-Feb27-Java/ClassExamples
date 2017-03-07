// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 5 - WRITE A SUBSTRING METHOD THAT ACCEPTS A STRING STR AND AN INTEGER IDX AND RETURNS THE
// SUBSTRING CONTAINED BETWEEN 0 AND IDX-1 INCLUSIVE. DO NOT USE ANY OF THE EXISTING SUBSTRING METHODS
// IN THE STRING, STRINGBUILDER, AND STRINGBUFFER APIS.

package com.revature.q5w1;

public class SubstringClass {

	void sub (String str, int idx){  // THIS IS THE SUBSTRING METHOD
		
		int len = idx - 1;  // sets a length as idx-1
		char[] subChar;  // char array that contains the substring
		subChar = new char[len];  // makes the array the same length as len
		//String sub = new String();
		
		for (int i = 0; i < len; i++){  // running the loop to popular the char array with the substring char
			
			if (len > str.length()){  // checks to see if idx is bigger than the length of the string
				System.out.println("Index out of bounds. Try a smaller number.");
				break;  // cannot continue of idx is larger, since we can't find a substring larger than the string
			}
			
			else {
				
				subChar[i] = str.charAt(i);  // populates the char array with each char from the string
				
			}
			
		}
		
		String subStr = new String(subChar);  // converts char array to a new substring
		System.out.println(subStr);
	}
}

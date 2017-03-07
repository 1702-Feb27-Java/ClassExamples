// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 5 - WRITE A SUBSTRING METHOD THAT ACCEPTS A STRING STR AND AN INTEGER IDX AND RETURNS THE
// SUBSTRING CONTAINED BETWEEN 0 AND IDX-1 INCLUSIVE. DO NOT USE ANY OF THE EXISTING SUBSTRING METHODS
// IN THE STRING, STRINGBUILDER, AND STRINGBUFFER APIS.

package com.revature.q5w1;

public class Q5Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String sub = "Hello my name is Danni";  // example stirng
		
		SubstringClass subObj = new SubstringClass();  // creates the class object
		subObj.sub(sub, 13); // to call the method
	}

}

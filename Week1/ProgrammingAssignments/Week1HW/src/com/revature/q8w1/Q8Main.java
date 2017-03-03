// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 8 - WRITE A PROGRAM THAT STORES THE FOLLOWING STRINGS INTO AN ARRAY LIST AND
// SAVES ALL THE PALINDROMES IN ANOTHER ARRAYLIST

package com.revature.q8w1;

import java.util.ArrayList;

public class Q8Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String ofWords = new String();  // make an example string of words
		ofWords = "karan madam tom civic radar sexes jimmy kayak john refer billy did";
		
		//ArrayListClass arrWords = new ArrayListClass();
		ArrayListClass.makesTwoLists(ofWords);  // this calls the makesTwoLists method which calls on the palindrome check
	}

}

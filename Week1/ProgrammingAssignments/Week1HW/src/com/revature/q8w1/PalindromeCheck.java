// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 8 - WRITE A PROGRAM THAT STORES THE FOLLOWING STRINGS INTO AN ARRAY LIST AND
// SAVES ALL THE PALINDROMES IN ANOTHER ARRAYLIST

package com.revature.q8w1;

public class PalindromeCheck {
	
	public static boolean palindrome(String word){ // method to check for palindrome
		char[] wordArr = word.toCharArray();  // converts the string of the word into a char array
		
		int a = 0;  // index from the left
		int b = word.length() - 1; // index from the right
		
		while (b > a){
			if (wordArr[a] != wordArr[b]) {
				return false;  // if the 2 characters are not equal, return false
			}
			++a; // increments left index
			--b; // decrements right index
		}
		return true;  // if every pair of char is equal, then this word is a palindrome
	}

}

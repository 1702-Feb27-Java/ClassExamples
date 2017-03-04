package com.revature.weekone.question8;

import java.util.ArrayList;

/**
 * Searches a list for palindromes and stores them.
 * 
 * It uses an ArrayList to store them.
 * 
 * @author Michael Hobbs
 *
 */
public class Question8 {
	
	/**
	 * Determines whether a string is a palindrome.
	 * 
	 * A palindrome is a word that can be written the same way backwards and forwards.
	 * 
	 * @param s the string
	 * @return true if the string is a palindrome.
	 */
	public static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length() / 2; i++) { //run through the string from both ends
			if (s.charAt(i) != s.charAt(s.length()-1-i)) { //compare characters at both ends of the string to see if they are equal
				return false; //the word is not a palindrome if the characters are not equal
			}
		}
		return true; //the word is a palindrome
	}

	/**
	 * Checks several strings to see if they are palindromes.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<String> potentialPalindromes = new ArrayList<String>(); //hold strings to be checked whether they are palindromes
		
		// create some strings that may or may not be palindromes
		potentialPalindromes.add("karan");
		potentialPalindromes.add("madam");
		potentialPalindromes.add("tom");
		potentialPalindromes.add("civic");
		potentialPalindromes.add("radar");
		potentialPalindromes.add("sexes");
		potentialPalindromes.add("jimmy");
		potentialPalindromes.add("kayak");
		potentialPalindromes.add("john");
		potentialPalindromes.add("refer");
		potentialPalindromes.add("billy");
		potentialPalindromes.add("did");
		
		ArrayList<String> palindromes = new ArrayList<String>(); //hold the strings that are actually palindromes
		
		// find out which strings are actually palindromes
		for (String s : potentialPalindromes) { //run through each of the strings that are potentially palindromes
			System.out.println("\"" + s + "\" a palindrome?: " + Question8.isPalindrome(s)); //check whether the string is actually a palindrome
			if (Question8.isPalindrome(s)) { //store the string if it is a palindrome
				palindromes.add(s); //store the palindrome
			}
		}
		
		// print out all the strings that were actually palindromes
		for (String s : palindromes) {
			System.out.println("\"" + s + "\" is a palindrome");
		}
		
	}

}

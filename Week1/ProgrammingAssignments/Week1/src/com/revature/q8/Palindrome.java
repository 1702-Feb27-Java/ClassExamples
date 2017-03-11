package com.revature.q8;

import java.util.ArrayList;
/**
 * 
 * @author Aaron Camm
 *
 */
public class Palindrome {
	
	
	
	public static void main (String[] args){
		Palindrome p = new Palindrome();
		ArrayList<String> list = new ArrayList<String>();
		list.add("karan");
		list.add("madam");
		list.add("civic");
		list.add("radar");
		list.add("sexes");
		list.add("jimmy");
		list.add("kayak");
		list.add("john");
		list.add("refer");
		list.add("billy");
		list.add("did");
		System.out.println(p.getPalindromes(list));
		
	}
	
	
	
	/***
	 * Checks if a string is a Palindrone
	 * @param string to compare if its a Palindrone
	 * @return true if string is a palindrone, false otherwise.
	 */
	public boolean isPalindrome(String string){
		
		//checks str[i] against string[length - i] to see if it matchs
		//checks only to middle point, ignore the middle most char if odd length
		for (int i = 0; i < string.length() / 2; ++i){
			if(string.charAt(i) != string.charAt(string.length() - 1 - i)){
				return false;
			}
		}
		return true;
	}
	
	/***
	 * Returns only Palindromes in a ArrayList<String>
	 * @param strings ArrayList of strings to check
	 * @return A new list with only Palindromes
	 */
	public ArrayList<String> getPalindromes(ArrayList<String> strings){
		ArrayList<String> list = new ArrayList<String>();
		
		
		for (String string : strings){
			if (isPalindrome(string))
				list.add(string);
		}
		
		return list;
		
	}
}

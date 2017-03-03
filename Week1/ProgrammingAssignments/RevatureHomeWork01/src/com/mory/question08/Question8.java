package com.mory.question08;

import java.util.*;

public class Question8 {

	public static void main(String[] args) {
		List<String> names=new ArrayList<>(Arrays.asList(
				"karan","madam","tom","civic","radar","sexes",
				"jimmy","kayak","john","refer","billy","did"	
				));
		System.out.println(getPalindromes(names));
		
	}
/***
 * This method takes a list of strings, check if a string is a 
 * palindrome and reverse accordingly.
 * 
 * @param list The list of String from which we want to get the palindrome
 * @return list of palindromes
 */
	public static List<String> getPalindromes(List<String> list){
		List<String> palindromesList= new ArrayList<String>();
		for( String str:list){
			if(str.equals(reverseString(str)))
				palindromesList.add(str);
		}
		return palindromesList;
		
	}
	/***
	 * This methods take a String and reverse it.
	 * 
	 * @param str String to reverse
	 * @return the reverse String
	 */
	public static String reverseString(String str){
		if(str==null|| str.isEmpty()) return str;
		String reverse="";
		for(int j=str.length()-1;j>=0;j--)
			reverse+=str.charAt(j);
		return reverse;
		
	}

}

package com.homework1.question3;

public class ReverseString {

	public static void main(String[] args) {
		String reverse = "reverse";
		System.out.println(reverseString(reverse));
	}
	
	/**
	 * 
	 * @param stringToReverse the word we want to reverse
	 * @return the word in reverse order
	 */
	public static String reverseString(String stringToReverse){
		//loop through every character, appending them to the end, in reverse order
		for(int i = stringToReverse.length() - 1; i >= 0; i--){
			stringToReverse = stringToReverse + stringToReverse.charAt(i);
		}
		//take the substring of the second half of the new word, which is the word in reverse
		stringToReverse = stringToReverse.substring(stringToReverse.length()/2, stringToReverse.length());
		return stringToReverse;
	}

}

package com.revature.q08;
import java.util.*;
public class Palindrome {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// initialize test input array
		String testInputs[] = {"karan", "madam", "tom", "civic",
				"radar", "sexes", "jimmy", "kayak", "john",
				"refer", "billy", "did"};
		// create an instance of ArrayList
		ArrayList al = new ArrayList();
		// for each string, perform palindromeCheck
		for(String s : testInputs){
			if(palindromeCheck(s)){
				// add palindromes to array list
				al.add(s);
			}
		}
		// display
		System.out.println(al.toString());
	
	}
	/**
	 * 
	 * @param input string input
	 * @return true if palindrome checks out, false otherwise
	 */
	static boolean palindromeCheck(String input){
		// iterate over string indices
		for (int i = 0; i < input.length()/2; i++){
			// if left symmetric character is identical to right symmetric character,
			// continue loop
			if(input.charAt(i) == input.charAt(input.length()-i-1)){
				continue;
			}
			else{
				return false;
			}
		}
		return true;
	}
}

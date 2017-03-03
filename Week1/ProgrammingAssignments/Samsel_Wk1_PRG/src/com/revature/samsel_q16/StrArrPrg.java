package com.revature.samsel_q16;

/*
 * Question: Program to display the number of characters to a string input. 
 * The String should be entered as a command line argument using (String[] args) 
 */

public class StrArrPrg {

	public static void main(String... args) {
		
		int iCharCount = 0;
		for(String iStr: args){
			iCharCount= iCharCount + iStr.length();
		}
		System.out.println("No of Characters : "+iCharCount);
	}

}

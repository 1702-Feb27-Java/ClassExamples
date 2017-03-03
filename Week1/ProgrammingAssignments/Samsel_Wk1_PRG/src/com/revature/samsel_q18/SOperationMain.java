package com.revature.samsel_q18;

import java.util.Scanner;

/*
 * Program having concrete subclass that inherits three abstract menthods from a superclass.
 * Provide the following in implementation iin the subclass corresponding to the abstract methods 
 * in the super class :
 * 1. Check if uppercase characters in the string are found . 
 * 2. Convert lowerase charaters to uppercase in given string 
 * 3. Convert input string to integer. Add 10 and output the result to console.
 */

public class SOperationMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//User Input : STRING
		System.out.println("Enter a string : ");
		Scanner sc = new Scanner(System.in);
		String iInput = sc.nextLine();
		
		SOperationChild sObj = new SOperationChild();
		
		//TEST : Check if uppercase characters in given string
		boolean bIsPresent = sObj.isUpperCaseFound(iInput);
		System.out.println("METHOD 1 : Is UPPERCASE character found : "+bIsPresent);
		
		//TEST : Convert given string to uppercase
		String iToUpper = sObj.convertToU(iInput);
		System.out.println("METHOD 2 : Converted UPPERCASE string : "+iToUpper);
		
		//TEST : Modify the string (+10)
		sObj.updateInput(iInput);
	}
}

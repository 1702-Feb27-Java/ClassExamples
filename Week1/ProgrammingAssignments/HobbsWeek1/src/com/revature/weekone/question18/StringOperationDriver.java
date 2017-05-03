package com.revature.weekone.question18;

/**
 * Tests StringOperation and its descendants.
 * 
 * @author Michael Hobbs
 *
 */
public class StringOperationDriver {

	public static void main(String[] args) {
		// Set up values to test
		String string1 = "abc";
		String string2 = "eFg";
		String string3 = "TUVwxYZ";
		String string4 = "90";
		StringOperationConcrete stringOperations = new StringOperationConcrete();
		
		// Test StringOperation.hasUppercase(String)
		System.out.println("hasUppercase(" + string1 + "): " + stringOperations.hasUppercase(string1));
		System.out.println("hasUppercase(" + string2 + "): " + stringOperations.hasUppercase(string2));
		
		// Test StringOperation.convertToUppercase(String)
		System.out.println("convertToUppercase(" + string3 + "): " + stringOperations.convertToUppercase(string3));
		
		// Test StringOperation.printAdd10(String)
		System.out.print("printAdd10(" + string4 + "): ");
		stringOperations.printAdd10(string4);
		System.out.println();
		
	}

}

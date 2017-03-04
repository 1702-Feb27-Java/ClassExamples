package com.revature.weekone.question18;

/**
 * Contains operations on strings.
 * 
 * @author Michael Hobbs
 *
 */
public class StringOperationConcrete extends StringOperation {

	@Override
	public boolean hasUppercase(String string) {
		char[] charArray = string.toCharArray(); //hold the characters in the string so we can check whether they any are uppercase
		
		// run through all of the characters
		for (char ch : charArray) {
			if (Character.isUpperCase(ch)) {
				return true; //there is an uppercase letter in the string
			}
		}
		
		return false; //there are no uppercase letters in the string
	}

	@Override
	public String convertToUppercase(String string) {
		return string.toUpperCase(); //convert the string to uppercase
	}

	@Override
	public void printAdd10(String string) {
		int number = 0;
		try {
			number = Integer.parseInt(string) + 10; //add 10 to the number in the string	
		}
		catch (NumberFormatException e) {
			System.out.println("Error: The number in the string is not a number");
		}
		System.out.println(number); //print out the result
	}

}

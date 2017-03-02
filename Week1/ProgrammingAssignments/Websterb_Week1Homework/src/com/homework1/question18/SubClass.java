package com.homework1.question18;

public class SubClass extends AbstractSuperClass{

	/**
	 * @input input the string input
	 * @return boolean that tells whether we found an upper case character 
	 */
	@Override
	boolean upperCaseChars(String input) {
		//loop through all characters
		for(int i = 0; i < input.length(); i++){
			//if there is one upper case character then return true
			if(Character.isUpperCase(input.charAt(i))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @input input the string input
	 * @return string that has all lower case characters converted to upper case
	 */
	@Override
	String lowerCaseConvert(String input) {
		//convert all characters to upper case in the string
		String upperCaseString = input.toUpperCase();
		return upperCaseString;
	}

	/**
	 * @input input the string input
	 */
	@Override
	void integerConvert(String input) {
		//convert the string input to string, if there is a word inputed, it will crash
		try{
			int conversion = Integer.parseInt(input);
			System.out.println(conversion);
		}
		catch(Exception e){
			System.out.println("That is not a number");
		}
	}
}

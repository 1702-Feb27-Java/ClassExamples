package com.revature.q18;
/**
 * 
 * @author Aaron Camm
 *
 */
public class ConcreteClass extends AbstractClass {

	public boolean hasUppercaseCharacters(String s) {
		
		//checks each character in string's char array, for an Uppercase.
		for (char c : s.toCharArray()){
			if (Character.isUpperCase(c)){
				return true;
			}
		}
		return false;
	}

	public String convertToUppercase(String s) {
		StringBuilder builder = new StringBuilder(s);
		
		//goes through a string's array, setting any lowercase it find to uppercase
		for(int i = 0; i < builder.length(); ++i){
			if(Character.isLowerCase(builder.charAt(i))){
				builder.setCharAt(i, (char) (builder.charAt(i) - 32));
			}
		}
		
		return builder.toString();
	}

	public void Add10ToInputStringAndPrint(String s) {
		try{
			System.out.println(Integer.parseInt(s) + 10);
		} catch (NumberFormatException e){
			//if there is no number in string, it assumes number is 0.
			
			System.out.println(10);
		}
	}

}

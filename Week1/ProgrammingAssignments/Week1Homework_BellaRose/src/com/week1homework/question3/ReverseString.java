package com.week1homework.question3;

public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String we want to reverse
		String stringToReverse = "Bella is Cool!";
		System.out.println(stringToReverse + '\n');
		
		//Loop for the length of the string
		for (int i = 0; i < stringToReverse.length(); i++) {
		    stringToReverse = stringToReverse.substring(1, stringToReverse.length() - i)
		        + stringToReverse.substring(0, 1)
		        + stringToReverse.substring(stringToReverse.length() - i, stringToReverse.length());
		    
		    System.out.println(stringToReverse);
		 }
		
		 System.out.println('\n' + stringToReverse);

	}

}

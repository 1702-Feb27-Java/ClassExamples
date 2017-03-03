package com.revature.Q18;

import java.util.StringTokenizer;

public abstract class Q18 {
		//check for uppercase characters
		protected abstract boolean checkUpperCase(); 
		//convert all lowercase to upper
		protected abstract String lowerToUpper();
		//convert string to int
		protected abstract int convertToInt();

}//Q18

class Q18subClass extends Q18 {

	@Override
	protected boolean checkUpperCase() {
		String s ="globAl"; //input string
			StringTokenizer wordToken = new StringTokenizer(s); //tokenize string
			while(wordToken.hasMoreTokens()){
				String word = wordToken.nextToken(); //get the word
				for(int i=0; i<word.length();i++){ //loop through the word for each character
					if(Character.isUpperCase(word.charAt(i))){ //check char at index if it is uppercase
						return true;
					}//if
				}//for
			}//while
		return false;
	}//checkUpperCase

	@Override
	protected String lowerToUpper() {
		String str="baseball"; //input string
		String uppercase=str.toUpperCase(); //make string all uppercase
		return uppercase;
	}//lowerToUpper

	@Override
	protected int convertToInt() {
		String str="248"; //string input
		int intVal = Integer.valueOf(str); //cast to integer
		return intVal+10;
	}//convertInt
}//Q18subClass

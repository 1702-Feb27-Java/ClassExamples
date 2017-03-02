package com.homework1.question5;

public class SubString {

	public static void main(String[] args) {
		System.out.println(split("SplitTest", 5));

	}

	/**
	 * 
	 * @param input the input string
	 * @param index the place to split the string at
	 * @return the resulting cut string
	 */
	public static String split(String input, int index){
		String subStr = "";
		
		//takes chars from 0 to the index selected and put it in a string
		for(int i = 0; i < index; i++){
			subStr += input.charAt(i);
		}
		
		return subStr;
	}
	
}

package com.homework1.question16;

public class StringCommandLineInput {

	public static void main(String[] args) {
		int count = 0;
		//loop through all strings in the command line argument
		for(String string : args){
			//loop through all characters in each string, incrementing count
			for(int i = 0; i < string.length(); i++){
				count++;
			}
		}
		//print out the number of characters in the string input
		System.out.println(count);
	}
}

package com.homework1.question14;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SwitchCase {

	public static void main(String[] args) {
		switchCase(1);
	}
	
	/**
	 * 
	 * @param number the case number to put into the function
	 */
	public static void switchCase(int number){
		int caseNumber = number;
		switch(caseNumber){
			case 1: 
				//create scanner object for input
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a number to find the square root of.");
				
				double input;
				//look for inputs until there is a number input
				while(!scanner.hasNextDouble()){
					System.out.println("input a number!");
					scanner.next();
				}
				//input scanner for the word to get the square root
				input = scanner.nextDouble();
				double squareRoot = Math.sqrt(input);
				
				System.out.println("input: " + input);
				System.out.println("squareRoot: " + squareRoot);
				break;
			case 2: 
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				System.out.println(dateFormat.format(date));
				break;
			case 3:
				String sentence = "I am learning Core Java";
				int prevWord = 0;
				int wordCount = 0;
				
				//loop through once to get the number of words in the string
				for(int x = 0; x < sentence.length(); x++){
					if(sentence.charAt(x) == ' '){
						wordCount++;
					}
				}
				wordCount++;//word count is one more than the number of spaces between words
				
				//create String array with number of words in String
				String[] stringArray = new String[wordCount];
				
				int arrayPlace = 0;
				for(int i = 0; i < sentence.length(); i++){
					//if you find a space, or end of the string, take the word to the left
					if(sentence.charAt(i) == ' ' || i == sentence.length() - 1){
						
						String word;
						
						//if it is the last character, there is no space after, so take one past the character
						if(i == sentence.length() - 1){
							word = sentence.substring(prevWord, i + 1);
						}
						else{
							word = sentence.substring(prevWord, i);
						}
						
						prevWord = i + 1;//move the start of the next word up to the spot after the space
						stringArray[arrayPlace] = word;//put the word in place in the array
						arrayPlace++;//increment to the next place in the array
					}
				}
				//testing if the array is filled with the correct words
/*				for(int z = 0; z < stringArray.length; z++){
					System.out.print(stringArray[z] + " ");
				}*/
		}
	}

}

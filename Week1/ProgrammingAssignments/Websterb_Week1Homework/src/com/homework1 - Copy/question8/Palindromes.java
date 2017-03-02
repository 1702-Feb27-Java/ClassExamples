package com.homework1.question8;

import java.util.ArrayList;
import java.util.Arrays;

public class Palindromes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create 3 arraylists, strings (starting list), 
		//storedStrings(non palindrome words, palindromes(list of palindrome words)
		ArrayList<String> strings = new ArrayList<String>(Arrays.asList("karan", "madam", "tom", 
				                 "civic", "radar", "sexes", "jimmy", "kayak", "john", "billy", "did"));
		ArrayList<String> storedStrings = new ArrayList<String>();
		ArrayList<String> palindromes = new ArrayList<String>();
		//loop through all words 
		for(int i = 0; i < strings.size(); i++){
			
			//boolean checking if the word is a palindrome
			boolean palindrome = true;
			char a, b;
			String currString = strings.get(i);
			int x = currString.length() - 1;
			
			//iterate through the word starting at opposite sides, comparing letters
			for(int y = 0; y < currString.length()/2; y++){
				a = currString.charAt(y);//start character a at the beginning
				b = currString.charAt(x);//start character b at the end
				x--;
				//if any of the letters don't match, it isn't a palindrome
				if(a != b){
					palindrome = false;
				}
			}
			if(palindrome == true){
				palindromes.add(currString);
			}
			else{
				storedStrings.add(currString);
			}
		}
		//print out testing to make sure palindromes are separated out
/*		System.out.println("stored strings: ");
		for(int i = 0; i < storedStrings.size(); i++){
			System.out.println(storedStrings.get(i));
		}
		System.out.println("palindromes: ");
		for(int i = 0; i < palindromes.size(); i++){
			System.out.println(palindromes.get(i));
		}*/
	}

}

package com.revature.Question08;

//Importing Array List to utilize
import java.util.ArrayList;

public class Palindromes {

	public static void main(String[] args) {
		//Creating an Array List to hold strings
		ArrayList<String> myList = new ArrayList<String>();
		
		//Adding strings to myList 
		myList.add("karan");
		myList.add("madam");
		myList.add("tom");
		myList.add("civic"); 
		myList.add("radar");
		myList.add("sexes"); 
		myList.add("jimmy");
		myList.add("kayak");
		myList.add("john"); 
		myList.add("refer");
		myList.add("billy");
		myList.add("did");
		
		//Creating another Array List to hold only the palindrome strings
		ArrayList<String> palindromeList = new ArrayList<String>();

		//Looping through each element in myList
		for (String str1 : myList){
			
			//Getting the reverse string of each element
			String palindromeString = stringReverse(str1);
			
			//Checking to see if the original string and reverse string equal each other
			//if so it is added to the new Array List palindromeList
			if (str1.equals(palindromeString)){
				palindromeList.add(str1);
			}
		}
		//Displaying to screen contents of palindromeList Array
		for (String str2 : palindromeList){
			System.out.println(str2);
		}
	}
	
	//Reversal string method taken from question 3
	public static String stringReverse(String str){
		for (int i = str.length()-2; i > -1;i--){
			str = str + str.charAt(i);
			str = str.substring(0,i)+ str.substring(i+1);
		}
		return str;
	}
}

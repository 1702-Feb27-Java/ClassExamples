// DANNI TANG
// REVATURE - FEB 27, 2017 - JAVA CORE
// WEEK 1 PROBLEM SET
// QUESTION 8 - WRITE A PROGRAM THAT STORES THE FOLLOWING STRINGS INTO AN ARRAY LIST AND
// SAVES ALL THE PALINDROMES IN ANOTHER ARRAYLIST

package com.revature.q8w1;

import java.util.ArrayList;

public class ArrayListClass {
	
	public static void makesTwoLists (String arrList){ // method to make 2 arraylists
	
		// make a new array list of words
		ArrayList<String> arrWords = new ArrayList<String>();
		for (String word : arrList.split(" ")){ // uses enhanced for loop to split the string of words
			arrWords.add(word);  // then add each word to the array of words
		}
		
		System.out.println(arrWords); // displays the words as separate elements in an array list
		
		// now we will check for palindromes
		
		int len = arrWords.size(); // find # of elements in this ArrayList

		// new arraylist for palindromes
		ArrayList<String> arrPali = new ArrayList<String>();

		for (int i = 0; i < len; i++) { // for loop to get each words in arraylist of words
			String temp = arrWords.get(i);
			
			boolean check = PalindromeCheck.palindrome(temp); // use the palindrome method to check
			
			//System.out.println(PalindromeCheck.palindrome(temp));
			
			if (check == true){  // if it's a palindrome, then add to the arraylist of palidromes
				arrPali.add(temp);
			}
		}
		
		System.out.println(arrPali); // displays the palidrome in another array list
	}

}

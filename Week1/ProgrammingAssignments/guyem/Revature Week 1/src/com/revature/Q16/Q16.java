package com.revature.Q16;

import java.util.StringTokenizer;

public class Q16 {

	public static void main(String[] args) {
		String[] s = {"country","house","baseball"}; //20
		System.out.println(numberOfCharacters(s));
	}

	public static int numberOfCharacters(String[] str) {
		int count=0; //count the number of characters
		for(int i=0; i<str.length;i++){
			StringTokenizer word = new StringTokenizer(str[i]); //get the words from the array
			while(word.hasMoreTokens()){ //check if words are in the array
				String s = word.nextToken();
				for(int j=0; j<s.length();j++){
					System.out.print(s.charAt(j) + " "); //print the words
					count+=1; //increment the count
				}
			}
			
		}
		System.out.println();
		return count;
	}
}

package com.revature.Q8;

import java.util.ArrayList;

public class Q8 {

	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<String>(); // array to store the words
		ArrayList<String> reverseWords = new ArrayList<String>(); // array to store the reversed words
		// add the words to the array
		words.add("karan");
		words.add("madam");
		words.add("tom");
		words.add("civic");
		words.add("radar");
		words.add("sexes");
		words.add("jimmy");
		words.add("kayak");
		words.add("john");
		words.add("refer");
		words.add("billy");
		words.add("did");
		// print the words
		System.out.println("ArrayList: " + words);
		// loop through the array of words
		for (int i = 0; i < words.size(); i++) {
			// use StringBuilder to access reverse method
			StringBuilder reverseStr = null;
			try {
			reverseStr = new StringBuilder(words.get(i)).reverse();
			}catch(Exception e){
				System.out.println("StringBuilder error.");
				e.getStackTrace();
			}
			// cast StringBuilder object to string
			String revStr = reverseStr.toString();
			// add the reversed words to the new ArrayList
			reverseWords.add(revStr);
		}
		// print the words in reverse
		System.out.println("ArrayList in reverse: " + reverseWords);
	}
	public static boolean isPalidrome(ArrayList<String> s, ArrayList<String> s2){
		int count=0;
		String firstStr="";
		String secondStr="";
		for(int i=0; i<s.size();i++){
			if(s.get(i).equals(s2.get(i))){
				count++;
				return true;
			}else {
				return false;
			}
		}
		System.out.println(count);
		return false;
	}

}

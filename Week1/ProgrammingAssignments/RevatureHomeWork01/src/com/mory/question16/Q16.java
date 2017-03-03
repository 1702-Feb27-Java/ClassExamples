package com.mory.question16;


public class Q16 {
// This method count the number of characters in a string entered via the command line
	public static void main(String[] args) {
		int count=0;
		for(String str: args){
			count+=str.length();
		}
		System.out.println(count);
	}
	
}

package com.mory.question05;

public class Q5 {
	/***
	 * 
	 * @param str The String on which the method is being
	 * applied on.
	 * @param idx  the upper index of the string of the inclusive
	 * substring to return;
	 * @return the inclusive substring;
	 */
	
	public static String subStringContained(String str, int idx){
		if(idx>str.length()-1) 
			throw new IllegalArgumentException("Index cannot be greater than the length of string");
		String temp="";
		char[] charArray=str.toCharArray();
		for(int i=0;i<=idx-1;i++){
			temp+=charArray[i];
			
		}
		return temp;
		
	}

	public static void main(String[] args) {
		System.out.print(subStringContained("hello",2));
		

	}

}

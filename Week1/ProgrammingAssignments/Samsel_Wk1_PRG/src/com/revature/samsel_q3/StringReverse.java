package com.revature.samsel_q3;
/*
 * Question : Reverse a string without using temporary variable. Do NOT use reverse() in the StringBuffer 
 * or the StringBuilder 
 */
public class StringReverse {

	public static void main(String[] args) {
			
		char[] chName;
		char[] reversedString = new char[6];
		String sName = "Samsel";
		chName = sName.toCharArray();
		System.out.println("Given String :"+sName);

		int j=0;
		System.out.print("Reveresed String : ");
		int iLen = chName.length;
		for(int i=chName.length-1;i>=0;i--){
			reversedString[j] = chName[i];
			j++;
		}
		
		System.out.println(reversedString);
	}

}

package com.revature.q5;

public class Question5 {
/**
 * 	
 * @param str
 * @param idx
 * @return StringBuffer
 * 
 * Returns the substring from str from 0 - idx-1 inclusive
 */
	public static StringBuffer subString (StringBuffer str, int idx)
	{
		StringBuffer buffer = new StringBuffer ();
		
		for (int i=0; i <= idx-1; i++)
		{
			buffer.append(str.charAt(i));
		}
		
		return buffer;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StringBuffer myString =new StringBuffer("This is a test");
		
		System.out.println(subString(myString, 1));
		
	}

}

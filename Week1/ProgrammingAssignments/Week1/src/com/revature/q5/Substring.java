package com.revature.q5;
/**
 * 
 * @author Aaron Camm
 *
 */
public class Substring {
	public static void main(String[] args){
		Substring s = new Substring();
		String str = "I am going to get ya, some nice ice scream";
		str = s.substring(str, 20);
		System.out.println(str);
		
		
	}
	
	/**
	 * Returns a substring of string str
	 * @param str - A String
	 * @param idx - length of substring, must be 0 <= idx <= str.length 
	 * @return a substring of str, from position 0 of length idx
	 */
	public String substring(String str, int idx){
		//Using StringBuilder, because String is immutable
		StringBuilder string = new StringBuilder();
		
		//iterates the string idx times, coping and adding the ith from "str" to "string"
		for (int i = 0; i < idx; ++i){
			string.append(str.charAt(i));
		}
		
		//brings it back to a immutable String object
		return string.toString();
	}
	
}

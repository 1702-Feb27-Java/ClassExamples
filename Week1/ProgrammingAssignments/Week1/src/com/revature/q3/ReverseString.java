package com.revature.q3;
/**
 * 
 * @author Aaron Camm
 *
 */
public class ReverseString {
	public static void main(String[] arg){
		
		String s = "Hello World";
		ReverseString r = new ReverseString();
		System.out.println(r.reverse(s));
	}
	
	
	/**
	 * Reverses a string
	 * @param s string
	 * @return	a string that is in opposite order of s
	 */
	public String reverse(String s){
	
		// on each loop, use the substring to 'push' a character to s.length-i position on the string.
		// Do this s.length times to reverse the string
		for (int i = 0; i < s.length(); ++i){
			s = s.substring(1, s.length() - i) //characters in front of the characer pushed and before it will appear again 
					+ s.substring(0,1)  // character's new location
					+ s.substring(s.length() - i, s.length()); //characters that appeared after s.length-i
		}
		
		return s;
	}
	
	
}

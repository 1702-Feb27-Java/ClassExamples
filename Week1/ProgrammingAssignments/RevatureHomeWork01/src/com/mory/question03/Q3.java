package com.mory.question03;

public class Q3 {

	public static void main(String[] args) {
		System.out.println(reverse("hello"));

	}
	
	/**
	 * This methods takes a string and reverse it.
	 * </p>
	 * 
	 * The implementation uses substring method to reverse the 
	 * string
	 * @param str the string to reverse
	 * @return The reversed string
	 */
	private static String reverse(String str) {
	      if (str.length() <= 1) {
	         return str;
	      }
	      return reverse(str.substring(1)) + str.substring(0, 1);
	   }
	}

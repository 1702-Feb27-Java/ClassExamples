package com.revature.q03;

public class ReverseString2 {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String inputString = "12345";
		// iterate reversing procedure over string indices
		for(int i = 0; i < inputString.length(); i++){
			// begin inputString with unsorted items
			inputString = inputString.substring(1,inputString.length()-i)
					// append first unsorted item
					+ inputString.substring(0,1)
					// append previously sorted items
					+ inputString.substring(inputString.length()-i);
		}
		// display
		System.out.println(inputString);
	}
}

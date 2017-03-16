package com.revature.q05;

public class Substring {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// init test string
		String testString = "What the substring";
		// init test index
		int testIdx = 15;
		// display result
		System.out.println(substr(testString, testIdx));

	}
	/**
	 * 
	 * @param str	input string
	 * @param idx	input index
	 * @return substring
	 */
	static String substr(String str, int idx){
		// convert string to char array
		char charArr[] = str.toCharArray();
		// create an instance of a char array to be returned
		char returnArr[] = new char[idx];
		// copy each char from char array to return array and bound by idx-1 inclusive
		for(int i = 0; i <= idx-1; i++){
			returnArr[i] = charArr[i];
		}
		// return as string using valueOf method
		return String.valueOf(returnArr);
	}

}

package com.revature.q16;


public class StringArgParse {

	public static void main(String[] args) {
		// i indicates starting index used to continually read in command line string arguments
		// set i to 1 since args[0] is filename
		for(String s : args)
			System.out.println(s.length());
		
	}

}

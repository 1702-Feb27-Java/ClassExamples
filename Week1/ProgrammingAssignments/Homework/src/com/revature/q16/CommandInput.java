package com.revature.q16;
/**
 * This program will count the number of characters
 * in a string argument passed by command line
 * @author Nick
 *
 */
public class CommandInput {

	public static void main(String[] args) {
		int count = 0;
		
		//This outer loop will check for the number of arguments that you pass in
		for ( int i = 0; i < args.length;i++ ) {
				//This inner loop with check each character of every string arguments
				for (int j = 0; j < args[i].length(); j++){
					//System.out.println(args[i].charAt(j));
					count += 1;
				}
				
		}
		
		System.out.println(count);

	}

}

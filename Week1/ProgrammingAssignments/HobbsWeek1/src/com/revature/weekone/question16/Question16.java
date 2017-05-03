package com.revature.weekone.question16;

/**
 * Displays the number of characters for a string input on the command line.
 * 
 * @author Michael Hobbs
 *
 */
public class Question16 {

	/**
	 * Accesses a string from the command line and prints out its number of characters.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// if there was something input on the command line then display its number of characters
		if (args.length == 0) { //there was nothing input on the command line
			System.out.println("Nothing was input");
		}
		else { //there was something input on the command line
			String input = args[0]; //get what was input
			System.out.println("The string that was input: " + input); //print out what was input
			System.out.println("The number of characters input: " + input.length()); //print out its number of characters
		}
	}

}

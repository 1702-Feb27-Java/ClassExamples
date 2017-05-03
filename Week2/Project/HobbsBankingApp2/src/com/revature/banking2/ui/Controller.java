package com.revature.banking2.ui;

import java.util.Scanner;

/**
 * Provides an interface to get keyboard input.
 * 
 * @author Michael Hobbs
 *
 */
public class Controller {

	private static Scanner input;
	
	private Controller() {
		
	}
	
	private static Scanner getController() {
		if (input == null) {
			input = new Scanner(System.in);
		}
		return input;
	}
	
	/**
	 * Gets a String from the user.
	 * 
	 * @return a String of user input
	 */
	public static String getInput() {
		input = getController();
		return input.nextLine();
	}
	
	@Override
	public void finalize() {
		if (input != null) {
			input.close();
		}
	}
	
}

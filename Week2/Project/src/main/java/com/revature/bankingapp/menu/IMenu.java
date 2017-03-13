package com.revature.bankingapp.menu;

import java.util.Scanner;

public interface IMenu {
	
	/**
	 * Open a menu display for user to interact with
	 * 
	 * @param scan - Scanner object to system.in to prompt the user with.
	 * @return a new Menu object for the next 'panel' to show the user if available, otherwise return null.
	 */
	public IMenu openMenu(Scanner scan);
}

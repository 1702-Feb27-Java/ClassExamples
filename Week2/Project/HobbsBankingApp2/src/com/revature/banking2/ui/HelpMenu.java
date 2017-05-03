package com.revature.banking2.ui;

public class HelpMenu implements Menu {
	
	public void displayHelp() {
		System.out.println("Revature Banking App man page:\n" +
				"\n" +
				"SYNOPSIS\n" +
				"Main [option]\n" +
				"\n" +
				"DESCRIPTION\n" +
				"Revature Banking App models basic banking operations like what might be " +
				"handled at an ATM or electronic teller.\n" +
				"\n" +
				"OPTIONS\n" +
				"Option includes:\n" +
				"<none> | customer\n" +
				"\topens the customer menu.\n" +
				"employee | bank\n" +
				"\topens the employee menu.\n" +
				"admin\n" +
				"\topens the admin menu.\n" +
				"help | man\n" +
				"\tdisplays this help message.\n" +
				"\n" +
				"AUTHOR\n" +
				"Michael Hobbs" +
				"\n");
	}

	@Override
	public void open() {
		displayHelp();
	}

}

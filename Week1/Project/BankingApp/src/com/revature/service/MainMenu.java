package com.revature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

	// main menu functionality
	// takes user input
	public static void functionality() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int mainMenuInput = 0;

		try {
			mainMenuInput = Integer.parseInt(br.readLine());
			switch (mainMenuInput) {
			case 1:
				// log into account
				MenuClass.showLogInMenu();
				break;
			case 2:
				// make an account
				AccountCreation ac = new AccountCreation();
				ac.makeAccount();
				break;
			case 3:
				// quit the program
				System.out.println("Goodbye. Thanks for using Revature Bank!");
				System.exit(0);
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NullPointerException e3) {
			e3.printStackTrace();
		}
	}

}

package com.mory;

import java.util.Scanner;

public class BankApp {
	static Scanner keypad = new Scanner(System.in);

	public static void main(String[] args) {

	}

	public static void firstHeader() {
		System.out.println("+----------------------------------");
		System.out.println("+---------Wecome to Mory's---------");
		System.out.println("+------------Banking App-----------");
	}

	public static void firstMenu() {
		System.out.println("Please choose an option");
		System.out.println("Enter 1: Create a User Account");
		System.out.println("Enter 3: Create a Banker Account");
		System.out.println("Enter 4: Login");
		System.out.println("Enter anything else to exit the application.");
		switch (Integer.parseInt(keypad.nextLine())) {
		case 1:
			// CreateUserAcount
			break;
		case 2:
			// CreateBankerAccount
			break;
		case 3:
			// createBankerAccount
			break;
		case 4:
			// login
		default:
			System.exit(0);
		}

	}

	public static void accountCreaptionMenu() {
		System.out.println("Which type of Account would you like to create?");
		System.out.println("Enter 1: to ");
		int accountCreateOption = Integer.parseInt(keypad.nextLine());
		switch (accountCreateOption) {
		case 1:
			// createUserAccount
			break;
		case 2:
			// createAdminAccount
			break;
		case 3:

			System.exit(0);
		}

	}

}

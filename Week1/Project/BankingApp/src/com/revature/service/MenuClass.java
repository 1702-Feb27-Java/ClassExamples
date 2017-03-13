package com.revature.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

// this stores all the menus in this program
public class MenuClass {

	// simple void method to print stuff
	public static void showMainMenu() {
		System.out.println("---------------------------");
		System.out.println("Welcome to Revature Bank!");
		System.out.println("Menu options:");
		System.out.println("1. Log into your account");
		System.out.println("2. Create an account");
		System.out.println("3. Exit");
	}

	// log in menu that takes user input as to what type of account it is
		public static void showLogInMenu() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int logInMenuInput = 0;

		System.out.println("---------------------------");
		System.out.println("Are you a: ");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("3. Admin");

		try {
			logInMenuInput = Integer.parseInt(br.readLine());
			// calls the method to implement which account it is
			accountTypeSelection(logInMenuInput);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void showCustomerMenu() {
		System.out.println("--------------------------------");
		System.out.println("Welcome back, Revature customer!");
		System.out.println("Menu options:");
		System.out.println("1. Open accounts");
		System.out.println("2. Check balance");
		System.out.println("3. Deposit");
		System.out.println("4. Withdraw");
		System.out.println("5. Back to main menu");
	}

	public static void showEmployeeMenu() {
		System.out.println("---------------------------");
		System.out.println("Welcome back, employee!");
		System.out.println("Menu options:");
		System.out.println("1. View pending account applications");
		System.out.println("2. View all accounts");
		System.out.println("3. Back to main menu");
	}

	public static void showAdminMenu() {
		System.out.println("---------------------------");
		System.out.println("Welcome back, admin!");
		System.out.println("Menu options:");
		System.out.println("1. View pending account applications");
		System.out.println("2. View all accounts");
		System.out.println("3. Edit accounts");
		System.out.println("4. Back to main menu");
	}

	public static void accountTypeSelection(int accountType) {

		switch (accountType) {
		case 1: // logs customer in and show customer menu
			LogInClass.customerLogIn();
			break;
		case 2:
			// this logs the employee in and show the employee menu
			LogInClass.employeeLogIn();
			EmployeeMenu.functionality();
			break;
		case 3: // logs admin in and show admin menu
			LogInClass.adminLogIn();
			AdminMenu.functionality();
			break;
		default:
			System.out.println("You cannot make that selection. Try again.");
			break;
		}
	}
	
	public static void showEditMenu(){
		
		System.out.println("What would you like to edit?");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Username");
		System.out.println("4. Password");
		System.out.println("5. Exit to previous menu.");
	}

}

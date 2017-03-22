package com.revature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Set;

import com.revature.dao.DAOAccountImp;
import com.revature.dao.DAOUserImp;
import com.revature.pojo.UserClass;

public class AccountCreation {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static UserClass user = new UserClass();
	static UserClass createdUser = new UserClass();
	static DAOUserImp daoUser = new DAOUserImp();
	static DAOAccountImp daoAccount = new DAOAccountImp();
	
	static Hashtable<String, String> allUnamePW = new Hashtable<String, String>();

	// this method creates a new customer account

	public void makeAccount() {

		// the fields for customer info
		String firstName;
		String lastName;
		String username;
		String password;
		String confirmPW;
		int input;

		try {

			// now the program will prompt the user for info
			// self-explanatory

			System.out.println("Please enter your first name: ");
			firstName = br.readLine();
			user.setFirstName(firstName);

			System.out.println("Please enter your last name: ");
			lastName = br.readLine();
			user.setLastName(lastName);

			System.out.println("Please choose a username: ");
			//System.out.println("Please note: your username is not case-sensitive but your password will be!");
			username = br.readLine();
			user.setUsername(username);

			System.out.println("Please choose a password: ");
			password = br.readLine();

			System.out.println("Confirm your password: ");
			confirmPW = br.readLine();
			if (confirmPW.equals(password)) {
				user.setPassword(confirmPW);
			} else {
				System.out.println("The password does not match. Try again.");
				System.out.println("Confirm your password: ");
				confirmPW = br.readLine(); // check this later
			}

			
//			allUnamePW = daoUser.getUsernamePW();
//			Set<String> usernames = allUnamePW.keySet();
//			for (usernames : allUnamePW) {
//				
//			}
			
			// then we call the DAO User Implementation method to add a user to the database
			daoUser.addUser(user);

			// return to the main menu now
			System.out.println("Your account is has been created.");
			System.out.println("---------------------------");

			// gives option to create accounts
			System.out.println("Would you like to create your checking or savings accounts now?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			input = Integer.parseInt(br.readLine());

			switch (input) {
			case 1: // yes
				createdUser = daoUser.getUserByUsername(user.getUsername());
				makeChSav();
			case 2: // no
				System.out.println("Returning you to the main menu now...");
				MenuClass.showMainMenu();
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}

		} catch (IOException e) {
			e.getStackTrace();
		}

	}
	
	// method to make a checking or savings account for ONE user
	// can be called as many times as the user wants

	public static void makeChSav() {
		int input;

		try {

			// self-explanatory
			System.out.println("What account would you like to make today?");
			System.out.println("1. Checking");
			System.out.println("2. Savings");
			input = Integer.parseInt(br.readLine());

			switch (input) {
			case 1: // checking
				input = 0;
				// use the DAO Implementation method to add an account
				daoAccount.addAccount(1, createdUser);
				System.out.println("---------------------------");
				System.out.println("Checking account created. It is currently pending approval.");
				System.out.println("Would you like to make another account?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				input = Integer.parseInt(br.readLine());
				
				switch(input){
				case 1: // yes
					makeChSav();
					break;
				case 2: // no
					System.out.println("Returning you to the main menu now...");
					MenuClass.showMainMenu();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}
				
				break;
			case 2: // savings
				daoAccount.addAccount(2, createdUser);
				System.out.println("---------------------------");
				System.out.println("Savings account created. It is currently pending approval.");
				System.out.println("Would you like to make another account?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				input = Integer.parseInt(br.readLine());
				
				switch(input){
				case 1: // yes
					makeChSav();
					break;
				case 2: // no
					System.out.println("Returning you to the main menu now...");
					MenuClass.showMainMenu();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}

		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}

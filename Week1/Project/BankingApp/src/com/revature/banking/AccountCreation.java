package com.revature.banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class AccountCreation {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	String firstName;
	String lastName;
	String username;
	String password;
	String confirmPW;
	int howManyAccounts;

	static double ch;
	static double sav;

	CustomerClass cc = new CustomerClass();

	StringBuilder customerInfo = new StringBuilder();

	public void makeAccount() {

		try {
			File file = new File("customeraccounts.txt");

			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			try {
				
				System.out.println("Please enter your first name: ");
				firstName = br.readLine();
				cc.setFirstName(firstName);

				System.out.println("Please enter your last name: ");
				lastName = br.readLine();
				cc.setLastName(lastName);

				System.out.println("Please choose a username: ");
				System.out.println("Please note: your username is not case-sensitive but your password will be!");
				username = br.readLine();
				cc.setUsername(username);

				System.out.println("Please choose a password: ");
				password = br.readLine();

				System.out.println("Confirm your password: ");
				confirmPW = br.readLine();
				if (confirmPW.equals(password)) {
					cc.setPassword(confirmPW);
				} else {
					System.out.println("The password does not match. Try again.");
					System.out.println("Confirm your password: ");
					confirmPW = br.readLine(); // check this later
				}

				String uuid = (UUID.randomUUID().toString()).substring(0, 8);
				cc.setId(uuid);

				customerInfo.append("n" + cc.getId() + ":" + cc.getFirstName() + ":" + cc.getLastName() + ":"
						+ cc.getUsername() + ":" + cc.getPassword() + ":");

				System.out.println("Would you like to make your checking and savings accounts now? 1 for YES and 2 for NO");

				int confirm = Integer.parseInt(br.readLine());

				makeAccounts(confirm);

				cc.setCheckingAmount(ch);
				cc.setSavingsAmount(sav);

				customerInfo.append("c" + Double.toString(cc.getCheckingAmount()) + ":");
				customerInfo.append("s" + Double.toString(cc.getSavingsAmount()));

				bw.write(customerInfo.toString());
				bw.newLine();
				

				System.out.println("Your account is currently pending approval.");
				System.out.println("---------------------------");
				System.out.println("Returning you to the main menu now...");
				MenuClass.showMainMenu();

			} catch (IOException e) {
				e.getStackTrace();
			} catch (NullPointerException e) {
				e.getStackTrace();
			}

			bw.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void makeAccounts(int confirm) {

		switch (confirm) {
		case 1: // yes
			makeChecking();
			makeSavings();
			break;
		case 2: //no
			break;
		default:
			System.out.println("You cannot make that selection. Try again.");
			break;
		}

	}

	public static void makeChecking() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("---------------------------");
		System.out.println("Checking account created.");
		double amount = 0;
		ch = amount;
		System.out.println("Success. You currently have " + ch + " dollars in your checking account.");
	}

	public static void makeSavings() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("---------------------------");
		System.out.println("Savings account created.");
		double amount = 0;
		sav = amount;
		System.out.println("Success. You currently have " + sav + " dollars in your savings account.");
	}

}

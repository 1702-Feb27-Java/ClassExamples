package com.revature.banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class LogInClass {

	public static void customerLogIn() {

		// bufferedreader for reading user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// bufferedreader for the employee account
		try {
			BufferedReader brc = new BufferedReader(new FileReader("customeraccounts.txt"));

			String thisLine;
			// this is a hash table for the customer username and password
			Hashtable<String, String> hashC = new Hashtable<String, String>();

			// this is a hash table for the customer username and id 
			// used to determine if account is new or not (if the id has length 9)
			Hashtable<String, String> hashID = new Hashtable<String, String>();

			while ((thisLine = brc.readLine()) != null) {
				String[] lineArr = thisLine.split(":");
				hashC.put(lineArr[3], lineArr[4]);
				hashID.put(lineArr[3], lineArr[0]);
			}

			System.out.println("For the sake of convenience, I will print out the entire list of username and passwords created so far.");
			System.out.println(hashC);

			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			Set<String> keys = hashC.keySet();
			for (String key : keys) {
				if (username.toLowerCase().equals(key.toLowerCase())) {
					// both username and password entered are correct
					if (password.equals(hashC.get(key))) {

						// now we have to check if the customer account is
						// pending approval or not
						// that means the id still has a length of 9
						if (hashID.get(key).length() == 9) {
							System.out.println("Your account is pending approval and cannot be used at this time.");
							System.out.println("Returning you to the main menu...");
							MenuClass.showMainMenu();
						} else { // customer account not new, can be used!
							System.out.println("Success! Welcome back, Revature Customer.");
							MenuClass.showCustomerMenu();
							CustomerMenu.functionality(username);
						}
					} else {
						System.out.println("You have entered an incorrect username or password. Try again.");
						customerLogIn();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void employeeLogIn() {

		// bufferedreader for reading user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// bufferedreader for the employee account
		try {
			BufferedReader bre = new BufferedReader(new FileReader("employeeaccount.txt"));

			String thisLine;
			Hashtable hashEm = new Hashtable();

			while ((thisLine = bre.readLine()) != null) {
				String[] lineArr = thisLine.split(":");
				hashEm.put(lineArr[0], lineArr[1]);
			}

			System.out.println("The default employee login is 'default' for username and '1234' for password.");
			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			Set<String> keys = hashEm.keySet();
			for (String key : keys) {
				if (username.equals(key) & password.equals(hashEm.get(key))) {
					System.out.println("Success! Welcome back, Revature Employee.");
					MenuClass.showEmployeeMenu();
				} else {
					System.out.println("You have entered an incorrect username or password. Try again.");
					employeeLogIn();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void adminLogIn() {

		// bufferedreader for reading user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// bufferedreader for the employee account
		try {
			BufferedReader bra = new BufferedReader(new FileReader("adminaccount.txt"));

			String thisLine;
			Hashtable hashA = new Hashtable();

			while ((thisLine = bra.readLine()) != null) {
				String[] lineArr = thisLine.split(":");
				hashA.put(lineArr[0], lineArr[1]);
			}

			System.out.println("The default admin login is 'admin' for username and 1234 for password.");
			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			Set<String> keys = hashA.keySet();
			for (String key : keys) {
				if (username.equals(key) & password.equals(hashA.get(key))) {
					System.out.println("Success! Welcome back, Revature Admin.");
					MenuClass.showAdminMenu();
				} else {
					System.out.println("You have entered an incorrect username or password. Try again.");
					adminLogIn();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

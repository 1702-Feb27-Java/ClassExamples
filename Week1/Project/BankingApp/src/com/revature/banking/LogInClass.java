package com.revature.banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

// this is the class that checks for all account logins
// customer, employee, and admin
// must have right username and password combos

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
			// used to determine if account is new or not (if the id has length
			// 9)
			Hashtable<String, String> hashID = new Hashtable<String, String>();

			while ((thisLine = brc.readLine()) != null) {
				String[] lineArr = thisLine.split(":");
				hashC.put(lineArr[3], lineArr[4]);
				hashID.put(lineArr[3], lineArr[0]);
			}
			
			brc.close();

			System.out.println(
					"For the sake of convenience, I will print out the entire list of username and passwords created so far.");
			System.out.println(hashC);

			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			// store all keys into a set
			Set<String> keys = hashC.keySet();
			for (String s : keys) {
				if (username.equals(s)) { // if username is in this line
					// check for username and pw combos
					if (username.equals(s) & password.equals(hashC.get(s))) {
						// both username and password entered are correct

						// now we have to check if the customer account is
						// pending approval or not
						// that means the id still has a length of 9
						if (hashID.get(s).length() == 9) {
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
				} else // if username not on this line, go to the next iteration
					continue;

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
			Hashtable<String, String> hashEm = new Hashtable<String, String>();

			// store employee username and id into a hashtable
			while ((thisLine = bre.readLine()) != null) {
				String[] lineArr = thisLine.split(":");
				hashEm.put(lineArr[0], lineArr[1]);
			}
			
			bre.close();

			System.out.println("The default employee login is 'default' for username and '1234' for password.");
			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			// here we check for username and pw the same way as customers
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
			Hashtable<String,String> hashA = new Hashtable<String,String>();

			// store admin username and pw into a hashtable
			while ((thisLine = bra.readLine()) != null) {
				String[] lineArr = thisLine.split(":");
				hashA.put(lineArr[0], lineArr[1]);
			}
			bra.close();

			System.out.println("The default admin login is 'admin' for username and 1234 for password.");
			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			// check for pw the same way as above.
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

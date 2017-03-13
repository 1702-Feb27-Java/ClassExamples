package com.revature.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Set;

import com.revature.service.CustomerMenu;
import com.revature.dao.DAOUserImp;

// this is the class that checks for all account logins
// customer, employee, and admin
// must have right username and password combos

public class LogInClass {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static DAOUserImp daoUser = new DAOUserImp();
	static Hashtable<String, String> hashC = new Hashtable<String, String>();

	public static void customerLogIn() {
		hashC = daoUser.getUsernamePW();

		try {

			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();
			
			// preventing customers from accessing admin and employee accounts
			if (username.equals("dtang") & password.equals("1234")) {
				System.out.println("Restricted access! Try again.");
				customerLogIn();				
				}
			
			if (username.equals("default") & password.equals("1234")) {
				System.out.println("Restricted access! Try again.");
				customerLogIn();				
				}

			// now we authenticate the login
			int it = 0;
			// store all keys into a set
			Set<String> keys = hashC.keySet();
			for (String s : keys) {
				it++;
				if (username.equals(s)) {
					if (password.equals(hashC.get(s))) {
						// both username and password entered are correct

						System.out.println("Success! Welcome back, Revature Customer.");
						MenuClass.showCustomerMenu();
						CustomerMenu.functionality(username);

					} else {
						System.out.println("You have entered an incorrect username or password. Try again.");
						customerLogIn();
					}
				} else {
					if (it < hashC.size()) {
						continue;
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
		hashC = daoUser.getUsernamePW();

		try {

			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			int it = 0;
			// store all keys into a set
			Set<String> keys = hashC.keySet();
			for (String s : keys) {
				it++;
				if (username.equals(s)) {
					if (password.equals(hashC.get(s))) {
						// both username and password entered are correct

						System.out.println("Success! Welcome back, Revature Employee.");
						MenuClass.showEmployeeMenu();
						EmployeeMenu.functionality();

					} else {
						System.out.println("You have entered an incorrect username or password. Try again.");
						employeeLogIn();
					}
				} else {
					if (it < hashC.size()) {
						continue;
					} else {
						System.out.println("You have entered an incorrect username or password. Try again.");
						employeeLogIn();
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void adminLogIn() {
		hashC = daoUser.getUsernamePW();

		try {

			System.out.println("username: ");
			String username = br.readLine();
			System.out.println("password: ");
			String password = br.readLine();

			int it = 0;
			// store all keys into a set
			Set<String> keys = hashC.keySet();
			for (String s : keys) {
				it++;
				if (username.equals(s)) {
					if (password.equals(hashC.get(s))) {
						// both username and password entered are correct

						System.out.println("Success! Welcome back, Revature Admin.");
						MenuClass.showAdminMenu();
						AdminMenu.functionality();

					} else {
						System.out.println("You have entered an incorrect username or password. Try again.");
						adminLogIn();
					}
				} else {
					if (it < hashC.size()) {
						continue;
					} else {
						System.out.println("You have entered an incorrect username or password. Try again.");
						adminLogIn();
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

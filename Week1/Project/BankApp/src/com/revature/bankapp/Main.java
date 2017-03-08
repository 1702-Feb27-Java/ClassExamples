package com.revature.bankapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Main {

	static final Logger l = Logger.getRootLogger();

	public static void main(String[] args) {

		ArrayList<UserAccount> userArray = new ArrayList<UserAccount>();

		String ans = null;
		Scanner k = new Scanner(System.in);

		Person p1 = new Person("admin1", "admin1", "1231231233", "admin@email.com");
		AdminAccount aa = new AdminAccount("admin", "admin", p1);
		userArray.add(aa);
		UserAccountMethods.serUserAccount(userArray);

		// loops the app infinitely until force close
		while (true) {
			// INTRO TO THE APP :)
			// flag to determine whether login was successful or not.

			userArray = (ArrayList<UserAccount>) UserAccountMethods.deserUserAccount();
			int index = 0;
			boolean successLogin = false;
			while (!successLogin) {
				System.out.println("=====WELCOME TO EDWARD'S COOL BANK APP. DO YOU HAVE AN ACCOUNT WITH US?\nY/N");
				l.info("New bank sessions");
				
				// WILL LOOP UNTIL CORRECT INPUT
				ans = getAnswer();

				if (ans.equalsIgnoreCase("n")) {
					System.out.println("=====WOULD YOU LIKE TO CREATE AN ACCOUNT WITH US?\nY/N");
					ans = getAnswer();

					if (ans.equalsIgnoreCase("n")) {
						System.out.println("=====OKAY THANKS BYE");
						break;
					} else {
						EditBankingData.createAccount(userArray);
					}
				} else {
					while (!successLogin) {
						System.out.println("======PLEASE ENTER YOUR USERNAME: \n=====TYPE \"E\" TO QUIT AT ANY TIME");
						String username = k.nextLine();
						if (username.equalsIgnoreCase("e"))
							break;
						System.out.println("=====PLEASE ENTER YOUR PASSWORD: ");
						String password = k.nextLine();
						if (password.equalsIgnoreCase("e"))
							break;
						if (LogInMethods.checkPassword(username, password, userArray)) {
							successLogin = true;
							index = UserAccountMethods.getArrayIndex(username, userArray);
						} else {
							System.out.println("=====INCORRECT MATCH");
						}
					}

				}

			}

			if (successLogin) {
				ans = "y";
				while (ans.equalsIgnoreCase("y")) {
					System.out.println(
							"---------------------------------------------------------------------------------------");
					System.out.println("Hello " + userArray.get(index).getPerson().getFname());

					int accessLevel = userArray.get(index).getAccessLevel();

					switch (accessLevel) {
					case 0:
						if ((userArray.get(index).getChecking() == null)
								|| (userArray.get(index).getSaving() == null)) {
							UserAccountMethods.requestOptions(userArray.get(index));
						}
						if ((userArray.get(index).getChecking() != null)
								|| (userArray.get(index).getSaving() != null)) {
							UserAccountMethods.bankOptions(userArray.get(index));
						}
						break;
					case 1:
						System.out.println("Enter 1: View all accounts.");
						System.out.println("Enter 2: Approve accounts ");
						String option = k.nextLine();
						while (!option.equals("1") && !option.equals("2")) {
							System.out.println("INCORRECT INPUT");
							option = k.nextLine();
						}
						switch (option) {
						case "1":
							UserAccountMethods.viewAccounts(userArray);
							break;
						case "2":
							UserAccountMethods.approveRequests(userArray);
							break;
						}
						break;

					case 2:
						
						System.out.println("Enter 1: View and edit all accounts.");
						System.out.println("Enter 2: Create employee acconut.");
						System.out.println("Enter 0: Exit");
						option = "";
						option = k.next();
						
						while((!option.equals("1"))&&(!option.equals("2"))&&(!option.equals("0"))){
							System.out.println("Incorrect option.");
							option = k.next();
						}
							
						if(option.equals("1"))
							UserAccountMethods.editAccounts(userArray);
						else if(option.equals("2"))
							UserAccountMethods.createEmpAccount(userArray);
							

					}
					System.out.println("Would you like another transaction? \nY/N");
					UserAccountMethods.serUserAccount(userArray);
					ans = getAnswer();
				}
				l.info("Session terminated");

			}

		}

	}

	public static String getAnswer() {
		Scanner k = new Scanner(System.in);
		String ans = "";
		while (true) {
			try {
				ans = k.nextLine();
			} catch (Exception e) {

			}
			if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n"))
				break;
			else {
				System.out.println("Incorrect input. Please enter Y/N");
			}
		}

		return ans;

	}

}

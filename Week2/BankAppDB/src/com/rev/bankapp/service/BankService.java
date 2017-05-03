package com.rev.bankapp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.rev.bankapp.connector.DBConnector;
import com.rev.bankapp.dao.BankDAO;

public class BankService {
	public static Scanner k;

	public static void createAccount() {
		boolean invalid = true;
		int userID = 0;
		k = new Scanner(System.in);
		String user = "";
		while (invalid) {
			System.out.println("Please enter your desired username: ");
			user = k.nextLine().toLowerCase();
			invalid = BankDAO.checkUsername(user);
			if (invalid)
				System.out.println("Username taken!");
		}
		System.out.println("Please enter your desired password: ");
		String pw = k.nextLine();
		System.out.println("Please enter your first name: ");
		String fname = k.nextLine();
		System.out.println("Please enter your last name: ");
		String lname = k.nextLine();

		BankDAO.createNewUser(fname, lname, user, pw, 1);

		System.out.println("Account creation successful!");
		BankDAO.logEvent("New user created with username " + user);
	}

	public static int logIn() {
		k = new Scanner(System.in);
		boolean logInSuccess = false;
		int userID = 0;
		String username = "";
		String password = "";
		while (!logInSuccess) {
			System.out.println("Enter your username: \nType \"e\" to quit.");
			username = k.nextLine().toLowerCase();
			if (username.equalsIgnoreCase("e")) {
				break;
			}
			System.out.println("Enter your password: ");
			password = k.nextLine();
			if (password.equalsIgnoreCase("e")) {
				break;
			}

			String realUserPass = BankDAO.getPassword(username);
			if (password.equals(realUserPass)) {
				logInSuccess = true;
				System.out.println("Log in success!");
			} else {
				System.out.println("-----Username or password incorrect----- ");
			}
		}
		userID = BankDAO.getUserID(username);
		BankDAO.logEvent("User " + userID + " logged in");

		return userID;
	}

	public static String getAnswer() {
		k = new Scanner(System.in);
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

	public static void getAllAccountBalances(ArrayList<Integer> allAccounts) {
		for (int i = 0; i < allAccounts.size(); i++) {
			double balance = BankDAO.getAccountBalance(i);
			int accountType = BankDAO.getAccountType(i);
			String accType = "";
			if (accountType == 1) {
				accType = "Checkings";
			} else {
				accType = "Savings";
			}
			System.out.println("Account " + i + " " + accType + " " + "Balance: " + balance);

		}
	}

	public static void updateBalance(int accountID, int amount, int operation) {
		int balance = BankDAO.getAccountBalance(accountID);
		// DEPOSIT
		if (operation == 1) {
			balance += amount;
			BankDAO.setBalance(accountID, balance);
			System.out.println("Deposited in account " + accountID);

		} else if (amount == 2 && amount > balance) {
			System.out.println("Insufficient funds.");

		} else {
			balance -= amount;
			BankDAO.setBalance(accountID, balance);
			System.out.println("Withdrew from account " + accountID);
		}
	}

	public static void requestAccount(int userID) {
		k = new Scanner(System.in);
		String accountTypeID = "";
		System.out.println("Enter 1 to request a new Checking Account.");
		System.out.println("Enter 2 to request a new Savings Account.");
		boolean notInt = true;
		while (notInt) {
			accountTypeID = k.nextLine();
			if (!accountTypeID.equals("1") && !accountTypeID.equals("2")) {
				System.out.println("Incorrect input");
			} else {
				notInt = false;
			}
		}
		BankDAO.requestAccount(userID, Integer.parseInt(accountTypeID));
		System.out.println("New account requested.");
		BankDAO.logEvent("User " + userID + " requested account type " + accountTypeID);
		
	}

	// A SINGLE USRS PENDING ACCOUNTS
	public static void getPendingAccounts(int userID) {
		ArrayList<Integer> allAccounts = BankDAO.getListOfAllAccounts(userID);
		boolean isPending = false;
		int count = 0;
		for (int i = 0; i < allAccounts.size(); i++) {
			isPending = BankDAO.checkPending(allAccounts.get(i));
			if (isPending) {
				System.out.println("Account number " + allAccounts.get(i) + " is pending.");
				System.out.println("---------------------------------------------------");
				count++;
			}
		}
		if (count == 0) {
			System.out.println("You do not have any pending acconuts.");
		}
		BankDAO.logEvent("User " + userID + " checked pending accounts");

	}

	public static void approveOrRejectPending(int userID) {

		ArrayList<Integer> allPending = BankDAO.getAllPendingAccounts();
		for (int i = 0; i < allPending.size(); i++) {
			System.out.println("INDEX " + i + "::" + BankDAO.getPendingAcconutString(allPending.get(i)));
			System.out.println("---------------------------------------------------");
		}
		System.out.println("Select the index of the account you would like to approve or reject.");
		k = new Scanner(System.in);
		int index = k.nextInt();

		System.out.println("Would you like to \n2: Approve account. \n3: Reject account. ");
		int approveOrReject = k.nextInt();

		BankDAO.editAccountPending(allPending.get(index), approveOrReject, userID);
		String aorj;
		if (approveOrReject == 2)
			aorj = "approved";
		else
			aorj = "rejected";
		
		BankDAO.logEvent("Employee " + userID + " " + aorj + " account " + allPending.get(index));

	}

	public static void printAllUsers() {
		ArrayList<Integer> allUsers = BankDAO.getAllUsers();
		for (int i = 0; i < allUsers.size(); i++) {
			System.out.println(BankDAO.getUserString(allUsers.get(i)));
			System.out.println("---------------------------------------------------");
		}
	}

	public static void employeeOptions(int userID) {
		System.out.println("---------------------------------------------------");
		System.out.println("Welcome employee " + BankDAO.getUsername(userID));
		System.out.println("---------------------------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("1: View all accounts");
		System.out.println("2: Approve or reject pending accounts");
		System.out.println("0: Exit");

		k = new Scanner(System.in);
		boolean correctOption = false;
		String option = "";
		while (!correctOption) {
			option = k.nextLine();
			if (!option.equals("1") && !option.equals("2") && !option.equals("0")) {
				System.out.println("Incorrect input.");
			} else {
				correctOption = true;
			}
		}

		switch (option) {

		case "1":
			printAllUsers();
			break;
		case "2":
			approveOrRejectPending(userID);
			break;

		default:
			break;
		}

	}

	// USERS AND ALL THEIR OPTIONS AND CHOICES. MAKES CALLS TO OTHER METHODS
	public static void userOptions(int userID) {
		System.out.println("---------------------------------------------------");
		System.out.println("Welcome " + BankDAO.getUsername(userID) + "!");
		System.out.println("---------------------------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("1: Request a new Checkings or Savings acconut");
		System.out.println("2: Check if you have pending accounts");
		System.out.println("3: Deposit into an account");
		System.out.println("4: Withdraw from an account");
		System.out.println("5: Check all account balances");
		System.out.println("0: Exit");

		k = new Scanner(System.in);
		boolean correctOption = false;
		String option = "";
		while (!correctOption) {
			option = k.nextLine();
			if (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")
					&& !option.equals("0") && !option.equals("5")) {
				System.out.println("Incorrect input.");
			} else {
				correctOption = true;
			}
		}

		switch (option) {
		case "1":
			requestAccount(userID);
			break;
		case "2":
			getPendingAccounts(userID);
			break;
		case "3":
			System.out.println("Select the account you would like to deposit into: ");
			int index = 0;
			ArrayList<Integer> allAccounts = BankDAO.getListOfAllAccounts(userID);
			ArrayList<Integer> approvedAccounts = new ArrayList<Integer>();
			boolean isApproved = false;
			for (int i = 0; i < allAccounts.size(); i++) {
				isApproved = BankDAO.checkApproved(allAccounts.get(i));
				if (isApproved) {
					approvedAccounts.add(allAccounts.get(i));
				}
			}

			if (approvedAccounts.size() < 1) {
				System.out.println("No accounts.");
			} else {
				for (int i = 0; i < approvedAccounts.size(); i++) {
					System.out.println(i + ": Account number " + approvedAccounts.get(i));
					System.out.println("---------------------------------------------------");
				}
				k = new Scanner(System.in);
				index = k.nextInt();
				System.out.println("How much would you like to deposit?");
				int ammount = k.nextInt();
				updateBalance(approvedAccounts.get(index), ammount, 1);
				BankDAO.logEvent("User " + userID + " deposited into account " + approvedAccounts.get(index));
			}
			break;
		case "4":
			System.out.println("Select the account you would like to withdraw from: ");
			index = 0;
			allAccounts = BankDAO.getListOfAllAccounts(userID);
			approvedAccounts = new ArrayList<Integer>();
			isApproved = false;
			for (int i = 0; i < allAccounts.size(); i++) {
				isApproved = BankDAO.checkApproved(allAccounts.get(i));
				if (isApproved) {
					approvedAccounts.add(allAccounts.get(i));
				}
			}
			if (approvedAccounts.size() < 1) {
				System.out.println("No accounts.");
			} else {
				for (int i = 0; i < approvedAccounts.size(); i++) {
					System.out.println(i + ": Account number " + approvedAccounts.get(i));
					System.out.println("---------------------------------------------------");
				}
				k = new Scanner(System.in);
				index = k.nextInt();
				System.out.println("How much would you like to withdraw?");
				int ammount = k.nextInt();
				int balance = BankDAO.getAccountBalance(approvedAccounts.get(index));
				if (ammount > balance) {
					System.out.println("Insuffiecient funds.");
				} else {
					updateBalance(approvedAccounts.get(index), ammount, 2);
					BankDAO.logEvent("User " + userID + " withdrew from account " + approvedAccounts.get(index));
				}
			}
			break;
		case "5": 
			allAccounts = BankDAO.getListOfAllAccounts(userID);
			approvedAccounts = new ArrayList<Integer>();
			isApproved = false;
			for (int i = 0; i < allAccounts.size(); i++) {
				isApproved = BankDAO.checkApproved(allAccounts.get(i));
				if (isApproved) {
					approvedAccounts.add(allAccounts.get(i));
				}
			}
			if(approvedAccounts.isEmpty()){
				System.out.println("No accounts.");
			}
			for(int i = 0; i < approvedAccounts.size(); i++){
				System.out.println("Account " + approvedAccounts.get(i) + " balance: " + BankDAO.getAccountBalance(approvedAccounts.get(i)));
				System.out.println("---------------------------------------------------");
			}
			
			break;
		}

	}

	public static void adminOptions(int userID) {
		System.out.println("---------------------------------------------------");
		System.out.println("Welcome " + BankDAO.getUsername(userID) + "!");
		System.out.println("---------------------------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("1: View and Edit all accounts");
		System.out.println("0: Exit");

		k = new Scanner(System.in);
		boolean correctOption = false;
		String option = "";
		while (!correctOption) {
			option = k.nextLine();
			if (!option.equals("1") && !option.equals("0")) {
				System.out.println("Incorrect input.");
			} else {
				correctOption = true;
			}
		}
		if (option.equals("1")) {
			ArrayList<Integer> allUsers = BankDAO.getAllUsers();
			for (int i = 0; i < allUsers.size(); i++) {
				System.out.println("Index " + i + "::" + BankDAO.getUserString(allUsers.get(i)));
				System.out.println("---------------------------------------------------");
			}
			System.out.println("Select the index of the user you would like to edit. ");
			k = new Scanner(System.in);
			int index = k.nextInt();
			System.out.println("What would you like to edit for " + BankDAO.getUserString(allUsers.get(index)));
			int editUserID = allUsers.get(index);
			System.out.println("1: First name");
			System.out.println("2: Last name");
			System.out.println("3: Username");
			System.out.println("4: Password");
			System.out.println("5: Role");
			System.out.println("0: Exit");

			index = 0;
			index = k.nextInt();
			switch (index) {
			case 1:
				System.out.println("Enter new first name: ");
				String fname = k.next();
				BankDAO.editUserFname(editUserID, fname);
				break;
			case 2:
				System.out.println("Enter new last name: ");
				String lname = k.next();
				BankDAO.editUserLname(editUserID, lname);
				break;
			case 3:
				System.out.println("Enter new username: ");
				String username = k.next();
				BankDAO.editUserUsername(editUserID, username);
				break;

			case 4:
				System.out.println("Enter new password: ");
				String password = k.next();
				BankDAO.editUserPassword(editUserID, password);
				break;
			case 5:
				System.out.println("Enter new role: \n1: User\n2: Employee\n3: Admin");
				int role = k.nextInt();
				BankDAO.editUserRole(editUserID, role);
				break;
			}
			
			BankDAO.logEvent("Admin " + userID + " accessed all accounts");

		}

	}

	public static void roleCheck(int roleID, int userID) {
		switch (roleID) {
		case 1:
			userOptions(userID);
			break;
		case 2:
			employeeOptions(userID);
			break;
		case 3:
			adminOptions(userID);
			break;
		default:
			break;

		}
	}
	

}

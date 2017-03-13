package com.revature.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import com.revature.dao.DAOAccountImp;
import com.revature.dao.DAOUserImp;
import com.revature.pojo.AccountClass;
import com.revature.pojo.UserClass;

public class CustomerMenu {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static DAOUserImp daoUser = new DAOUserImp();
	static DAOAccountImp daoAccount = new DAOAccountImp();

	// this method implements functionality for a customer menu
	// takes user input
	public static void functionality(String username) {

		try {
			int cMenuInput = Integer.parseInt(br.readLine());

			// depending on user choice, implement stuff
			switch (cMenuInput) {
			case 1:
				// check balance
				checkBalance(username);
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
				break;
			case 2: // deposit
				deposit(username);
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
				break;
			case 3: // withdraw
				withdraw(username);
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
				break;
			case 4:
				MenuClass.showMainMenu();
				MainMenu.functionality();
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
		}
	}

	// method for check balance
	public static void checkBalance(String username) {
		UserClass currentUser = new UserClass();
		currentUser = daoUser.getUserByUsername(username);

		ArrayList<AccountClass> allUserAccounts = new ArrayList<AccountClass>();
		allUserAccounts = daoAccount.getAccountsByUserID(currentUser.getUserID());

		System.out.println("These are all your accounts and balances.");
		allUserAccounts.forEach(System.out::println);

	}

	// deposit method for the customer
	public static void deposit(String username) {

		UserClass currentUser = new UserClass();
		currentUser = daoUser.getUserByUsername(username);

		ArrayList<AccountClass> allUserAccounts = new ArrayList<AccountClass>();
		allUserAccounts = daoAccount.getAccountsByUserID(currentUser.getUserID());

		try {
			System.out.println("You have chosen to deposit. Enter the id of the account you want to deposit in.");
			int accountID;

			accountID = Integer.parseInt(br.readLine());

			AccountClass currentAccount = new AccountClass();
			currentAccount = daoAccount.getAccountByID(accountID);
			
			if (currentAccount.getStatusID() == 1){
				System.out.println("We're sorry, but your account hasn't been approved. Returning you to the previous menu.");
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
			}

			System.out.println("How much would you like to deposit?");
			double amount = Double.parseDouble(br.readLine());
			
			// add old balance and new balance together
			double balance = currentAccount.getBalance() + amount;
			
			// then we call the DAO Account method to update balance
			daoAccount.updateBalance(currentAccount, balance);
			
			System.out.println("Deposit success. Would you like to deposit in another account?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			
			int input = Integer.parseInt(br.readLine());
			
			switch(input){
			case 1: // yes
				deposit(username);
				break;
			case 2: // no
				System.out.println("Returning you to the previous menu.");
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// withdraw method for the customer
	public static void withdraw(String username) {

		UserClass currentUser = new UserClass();
		currentUser = daoUser.getUserByUsername(username);

		ArrayList<AccountClass> allUserAccounts = new ArrayList<AccountClass>();
		allUserAccounts = daoAccount.getAccountsByUserID(currentUser.getUserID());

		try {
			System.out.println("You have chosen to withdraw. Enter the id of the account you want to withdraw from.");
			int accountID;

			accountID = Integer.parseInt(br.readLine());

			AccountClass currentAccount = new AccountClass();
			currentAccount = daoAccount.getAccountByID(accountID);
			
			if (currentAccount.getStatusID() == 1){
				System.out.println("We're sorry, but your account hasn't been approved. Returning you to the previous menu.");
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
			}

			System.out.println("How much would you like to withdraw?");
			double amount = Double.parseDouble(br.readLine());
			
			// subtract new amount from old amount
			double balance = currentAccount.getBalance() - amount;
			
			if (balance < 0){
				System.out.println("You do not have enough balance to withdraw. Try again.");
				withdraw(username);
			}
			
			// then we call the DAO Account method to update balance
			daoAccount.updateBalance(currentAccount, balance);
			
			System.out.println("Withdrawal success. Would you like to withdraw from another account?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			
			int input = Integer.parseInt(br.readLine());
			
			switch(input){
			case 1: // yes
				withdraw(username);
				break;
			case 2: // no
				System.out.println("Returning you to the previous menu.");
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

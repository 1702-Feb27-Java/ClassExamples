package com.revature.banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;

public class CustomerMenu {
	
	// this method implements functionality for a customer menu
	// takes user input
	public static void functionality(String username) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			String thisLine;
			String hashCh = null;
			String hashSav = null;

			// make a hashtable for checking and savings balance depending on the user
			while ((thisLine = br.readLine()) != null) {
				String[] lineArr = thisLine.split(":");

				if (lineArr[3].equals(username)) {
					hashCh = lineArr[5].substring(1, lineArr[5].length());
					hashSav = lineArr[6].substring(1, lineArr[6].length());
				}
			}

			System.out.println("You currently have " + hashCh + " dollars in your checking and " + hashSav + " dollars in your savings accounts.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// deposit method for the customer
	public static void deposit(String username){
		System.out.println("You have chosen to deposit. Which account do you want to deposit in?");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		Scanner sc = new Scanner(System.in);
				
		try {
			
			int input = sc.nextInt();
			// this reads the amount the user wants to change
			BufferedReader data = new BufferedReader(new FileReader("customeraccounts.txt"));
			
			// username and checking balance pairs
			Hashtable<String, String> containsCh = new Hashtable<String, String>();
			// username and savings balance pairs
			Hashtable<String, String> containsSav = new Hashtable<String, String>();
			
			String s;
			
			// lets store username and checking and savings balance
			while ((s = data.readLine()) != null){
				containsCh.put(username, s.split(":")[5]);
				containsSav.put(username, s.split(":")[6]);
			}
			
			double amount;
			
			switch(input){
			case 1: // checking deposit
				System.out.println("How much would you like to deposit?");
				amount = sc.nextDouble();
				
				// calls the overloaded method
				// method args are (String, String, double)
				
				//System.out.println(oldAmount);
				ReplaceClass.deposit(username, "c", amount);
				break;
			case 2: // savings deposit
				System.out.println("How much would you like to deposit?");
				amount = sc.nextDouble();
				ReplaceClass.deposit(username, "s", amount);				
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
	public static void withdraw(String username){
		System.out.println("You have chosen to withdraw. Which account do you want to withdraw from?");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		Scanner sc = new Scanner(System.in);
				
		try {
			
			int input = sc.nextInt();
			// this reads the amount the user wants to change
			BufferedReader data = new BufferedReader(new FileReader("customeraccounts.txt"));
			
			// username and checking balance pairs
			Hashtable<String, String> containsCh = new Hashtable<String, String>();
			// username and savings balance pairs
			Hashtable<String, String> containsSav = new Hashtable<String, String>();
			
			String s;
			
			// lets store username and checking and savings balance
			while ((s = data.readLine()) != null){
				containsCh.put(username, s.split(":")[5]);
				containsSav.put(username, s.split(":")[6]);
			}
			
			double amount;
			
			switch(input){
			case 1: // checking withdraw
				System.out.println("How much would you like to withdraw?");
				amount = sc.nextDouble();
				
				// calls the overloaded method
				// method args are (String, String, double)
				
				ReplaceClass.withdraw(username, "c", amount);
				break;
			case 2: // savings withdraw
				System.out.println("How much would you like to withdraw?");
				amount = sc.nextDouble();
				ReplaceClass.withdraw(username, "s", amount);				
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

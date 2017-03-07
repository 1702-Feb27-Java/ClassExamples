package com.revature.banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;

public class CustomerMenu {

	public static void functionality(String username) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int cMenuInput = Integer.parseInt(br.readLine());

			switch (cMenuInput) {
			case 1:
				// check balance
				checkBalance(username);
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
				break;
			case 2:
				deposit(username);
				MenuClass.showCustomerMenu();
				CustomerMenu.functionality(username);
				break;
			case 3:
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

	public static void checkBalance(String username) {

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			String thisLine;
			String hashCh = null;
			String hashSav = null;

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
	
	public static void deposit(String username){
		System.out.println("You have chosen to deposit. Which account do you want to deposit in?");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		Scanner sc = new Scanner(System.in);
		
		// br.close();
		
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
			case 1: // checking
				System.out.println("How much would you like to deposit?");
				amount = sc.nextDouble();
				
				// calls the overloaded method
				// method args are (String, double, double)
				//double oldAmount = Double.parseDouble(containsCh.get(username).substring(1, containsCh.get(username).length()));
				
				//System.out.println(oldAmount);
				ReplaceClass.deposit(username, "c", amount);
				break;
			case 2: // savings
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
	
	public static void withdraw(String username){
		System.out.println("You have chosen to withdraw. Which account do you want to withdraw from?");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		Scanner sc = new Scanner(System.in);
		
		// br.close();
		
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
			case 1: // checking
				System.out.println("How much would you like to withdraw?");
				amount = sc.nextDouble();
				
				// calls the overloaded method
				// method args are (String, double, double)
				//double oldAmount = Double.parseDouble(containsCh.get(username).substring(1, containsCh.get(username).length()));
				
				//System.out.println(oldAmount);
				ReplaceClass.withdraw(username, "c", amount);
				break;
			case 2: // savings
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

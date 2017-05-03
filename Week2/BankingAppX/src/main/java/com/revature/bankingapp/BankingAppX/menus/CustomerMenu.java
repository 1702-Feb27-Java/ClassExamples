package com.revature.bankingapp.BankingAppX.menus;

import java.util.Scanner;

import com.revature.bankingapp.BankingAppX.account.Account;
import com.revature.bankingapp.BankingAppX.admin.Admin;
import com.revature.bankingapp.BankingAppX.users.User;

public class CustomerMenu
{
	static User cust = new User();
	static Scanner scan = new Scanner(System.in);
	static String username, password;
	static int role;
	
	public static void custMenu()
	{
		System.out.println("You've chosen the Customer Menu");
		System.out.println("Please choose on of the options below:");
		System.out.println("Enter 1 to make an account.");
		System.out.println("Enter 2 to login to an account.");
		System.out.println("Enter 3 to exit.");
	}
	
	public static void custCreate()
	{
		System.out.println("You have chosen to create an account");
		cust.setRole(3);
		System.out.println("Please enter a First Name:");
		cust.setFirstName(scan.next());
		System.out.println("Please enter a Last Name:");
		cust.setLastName(scan.next());
		System.out.println("Please enter a username:");
		cust.setUserName(scan.next());
		System.out.println("Please enter a password:");
		cust.setPassword(scan.next());
		
		System.out.println("Please select 1 for Savings, or 2 for Checking:");
		int choice = scan.nextInt();
		if(choice == 1)
		{
			cust.setAccountType("Savings");
		}
		else if(choice == 2)
		{
			cust.setAccountType("Checking");
		}
		else
		{
			System.out.println("Invalid choice");
			cust.setAccountType("null");
		}
		System.out.println("Please enter starting balance:");
		double bal = scan.nextDouble();
		System.out.println("You entered $" + bal);
		cust.setAccountBalance(bal);
		System.out.println("Your account status is pending until approved by an employee.");
		System.out.println("Thank you!");
	}
	
	public static void custLogin()
	{
		System.out.println("You have chosen to login to an account");
		System.out.println("Please enter a username: ");
		username = scan.next();
		System.out.println("Please enter a password: ");
		password = scan.next();
	}
	
	public static User getUser()
	{
		return cust;
	}
	
	public static void setUser(User user)
	{
		CustomerMenu.cust = user;
	}
	
	public static String getUsername()
	{
		return username;
	}

	public static void setUsername(String username)
	{
		CustomerMenu.username = username;
	}

	public static String getPassword()
	{
		return password;
	}

	public static void setPassword(String password)
	{
		CustomerMenu.password = password;
	}

	public static int getRole()
	{
		return role;
	}

	public static void setRole(int role)
	{
		CustomerMenu.role = role;
	}
}

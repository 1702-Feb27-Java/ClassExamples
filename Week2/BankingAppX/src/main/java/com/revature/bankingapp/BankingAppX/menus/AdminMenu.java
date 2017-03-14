package com.revature.bankingapp.BankingAppX.menus;

import java.util.*;
import com.revature.bankingapp.BankingAppX.admin.Admin;

public class AdminMenu
{
	static Admin admin = new Admin();
	static Scanner scan = new Scanner(System.in);
	static String username, password, adminPin;
	
	public static void adminMenu()
	{
		System.out.println("You have chosen the Admin menu");
		System.out.println("Please choose one of the options below:\n");
		System.out.println("Enter 1 to make an account.");
		System.out.println("Enter 2 to login to an account.");
		System.out.println("Enter 3 to exit.");
	}
	
	public static void adminCreate()
	{
		System.out.println("You have chose to create a new admin.\n");
		admin.setRole(1);
		System.out.println("Please enter a First Name:");
		admin.setFirstName(scan.next());
		System.out.println("Please enter a Last Name:");
		admin.setLastName(scan.next());
		System.out.println("Please enter an Username:");
		admin.setUserName(scan.next());
		System.out.println("Please enter a Password:");
		admin.setPassword(scan.next());
		System.out.println("Please enter a 4-digit Admin Pin:");
		admin.setAdminPin(scan.next());
	}
	
	public static void adminLogin()
	{
		System.out.println("You have chosen to login to an Admin account.\n");
		System.out.println("Please enter an Username:");
		username = scan.next();
		System.out.println("Please enter a Password:");
		password = scan.next();
		System.out.println("Please enter an Admin ID:");
		adminPin = scan.next();
	}
	
	public static Admin getAdmin()
	{
		return admin;
	}

	public static void setAdmin(Admin admin)
	{
		AdminMenu.admin = admin;
	}

	public static String getUsername()
	{
		return username;
	}

	public static void setUsername(String username)
	{
		AdminMenu.username = username;
	}

	public static String getPassword()
	{
		return password;
	}

	public static void setPassword(String password)
	{
		AdminMenu.password = password;
	}

	public static String getAdminPin()
	{
		return adminPin;
	}

	public static void setAdminPin(String adminPin)
	{
		AdminMenu.adminPin = adminPin;
	}

	public static Admin getAdminObject()
	{
		return admin;
	}
}

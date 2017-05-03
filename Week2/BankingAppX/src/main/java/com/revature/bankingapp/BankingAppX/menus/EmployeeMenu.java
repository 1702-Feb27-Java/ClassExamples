package com.revature.bankingapp.BankingAppX.menus;

import java.util.Scanner;

import com.revature.bankingapp.BankingAppX.users.User;

public class EmployeeMenu
{
	static User emp = new User();
	static Scanner scan = new Scanner(System.in);
	static String username, password;
	static int role;
	
	public static void empMenu()
	{
		System.out.println("You've chosen the Employee menu:");
		System.out.println("Please choose one of the options below:");
		System.out.println("Enter 1 to make an account.");
		System.out.println("Enter 2 to login to an account.");
		System.out.println("Enter 3 to exit.");
	}
	
	public static void empCreate()
	{
		System.out.println("You have chosen to create a new Employee");
		emp.setRole(2);
		System.out.println("Please enter a First Name:");
		emp.setFirstName(scan.next());
		System.out.println("Please enter a Last Name:");
		emp.setLastName(scan.next());
		System.out.println("Please enter a username: ");
		emp.setUserName(scan.next());
		System.out.println("Please enter a password: ");
		emp.setPassword(scan.next());
		System.out.println("Employee account created!");
		System.out.println("Thanks!");
	}
	
	public static void empLogin()
	{
		System.out.println("You have chosen to login to an employee account");
		System.out.println("Please enter your username: ");
		username = scan.next();
		System.out.println("Please enter a password: ");
		password = scan.next();
	}
	
	public static User getEmp()
	{
		return emp;
	}

	public static void setEmp(User emp)
	{
		EmployeeMenu.emp = emp;
	}

	public static String getUsername()
	{
		return username;
	}

	public static void setUsername(String username)
	{
		EmployeeMenu.username = username;
	}

	public static String getPassword()
	{
		return password;
	}

	public static void setPassword(String password)
	{
		EmployeeMenu.password = password;
	}

	public static int getRole()
	{
		return role;
	}

	public static void setRole(int role)
	{
		EmployeeMenu.role = role;
	}
}

package com.sqlbank.testProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;
import com.sqlbank.service.ServiceAdminImp;
import com.sqlbank.service.ServiceImplementation;
import org.apache.log4j.Logger;

public class TestProject
{
	static Scanner sc = new Scanner(System.in);
	static ServiceImplementation serve = new ServiceImplementation();
	static ServiceAdminImp aServe = new ServiceAdminImp();
	static final Logger l = Logger.getRootLogger();

	public static void startBank()
	{
		boolean result;
		result = getStartMenu();
		if(result)
			getLoginMenu();
		else
			createUserMenu();
	}
	//================================================START MENU==========================================================================
	/**
	 * ASKS USER IF THEY HAVE A UNAME AND PASSWORD
	 * 
	 * @return TRUE IF USER HAS CRED ELSE FALSE
	 */
	public static boolean getStartMenu()
	{
		String input;
		do
		{
			l.info("USER ENTERING BANK APP");
			System.out.println("============= WELCOME TO THE BANKING APP =============");
			System.out.println("Do you have a username and password?");
			System.out.print( "[Y/N] : ");
			input = sc.next();
			if (input.equals("Y"))
			{
				return true;
			}
			else if (input.equals("N"))
			{
				return false;
			}
			l.warn("User entered incorrect option");
			System.out.println("PLEASE ENTER Y OR N\n");
		}while (!(input.equals("Y")) && !(input.equals("N")));
		return false;	
	}
    
	//================================================LOGIN MENU==========================================================================
	/**
	 * GETS LOGIN MENU
	 * 	ENTER UNAME:
	 * 	ENTER PWORD:
	 */
	public static void getLoginMenu()
	{
		int result;
		String username, password;
		do
		{
			l.trace("USER IN LOGIN MENU");
			System.out.println("============= LOGIN =============");
			System.out.print("Enter your username: ");
			username = sc.next();
			System.out.print("Enter your password: ");
			password = sc.next();
		
			result = serve.isUserValid(username, password);
			if(result > 1)
			{
				l.error("DATABASE RETURNED MULTIPLE USERS WITH SAME USERNAME/PASSWORD");
				System.out.println("Error in Database");
					
			}
			else if (result == 0)
			{
				l.warn("USER ENTERED WRONG CREDENTIALS");
				System.out.println("WRONG USERNAME OR PASSWORD \n");
				break;
			}

		}while(result == 0);
		boolean heExit = false;
		switch(result)
		{
			case 1:
				l.trace("ADMIN IS LOGGED IN");
				System.out.println("Admin");
				Admin ad = serve.getAdmin(username);
				do{
					
					heExit = doAdminStuff(ad);
				}while (!heExit);
				break;
				
			case 2:
				l.trace("EMPLOYEE IS LOGGED IN");
				System.out.println("Employee");
				Employee em = serve.getEmployee(username);
				doEmployeeStuff(em);
				break;
			case 3:
				l.trace("CUSTOMER IS LOGGED IN");
				System.out.println("Customer");
				Customer cus = serve.getCustomer(username);
				doCustomerStuff(cus);
				break;
		}
	}
	
	//================================================CREATE USER MENU==========================================================================
	public static boolean createUserMenu()
	{
		boolean loginCheck;
	//	do
	//	{
			l.info("USER CREATING CREDENTIALS");
			String name, username, password;
			System.out.println("============= CREATE LOGIN =============");
			System.out.print("Enter your first name: ");
			name = sc.next();
			System.out.print("Enter your username: ");
			username = sc.next();
			System.out.print("Enter your password: ");
			password = sc.next();
		
			loginCheck = serve.isUsernameValid(username);
		
			//USER DOESNT EXISTS
			if (loginCheck)
			{
				serve.createNewCus(name, username, password);
				System.out.println("USER CREATED \n");
				l.info("USER CREATED NEW MEMBER");
				return true;
			}
			l.warn("USER ATTEMPTED TO CREATE USER  WITH USERAME THAT ALREADY EXISTS");
			System.out.println("THE USERNAME YOU ARE TRYING TO CREATE ALREADY EXISTS PLEASE TRY AGAIN\n");
	//	}while(!loginCheck);
		return false;	
	}
	
	//================================================ GET USERNAME TO VIEW ================================================================
	public static String getName()
	{
		System.out.println("ENTER THE USERNAME YOU WANT TO VIEW: ");
		return sc.next();
	}
	
	//================================================ ADMIN STUFF ==========================================================================
	public static boolean doAdminStuff(Admin ad)
	{
		boolean heExit = false;
		int opt = -1;
		do
		{
			ad.getMenu();
			try
			{
				opt = sc.nextInt();
				switch (opt)
				{
					default: 
						System.out.println("PLEASE CHOOSE A NUMBER WITHIN 0-4");
						break;
					case 1: //MY ACCOUNT
						heExit = false;
						ad.viewMyAccount();
						break;
					case 2: //ALL ACOUNTS
						heExit = false;
						ArrayList<People> users = new ArrayList<People>(aServe.viewAccounts());
						for (People x : users)
						{
							x.printAccount();
						}
						break;
					case 3: //CERTAIN ACCOUNT//
						boolean worked = false;
						String check = getName();
						worked = aServe.viewAaccount(check);
						if(!worked)
						{
							System.out.println("USER DOES NOT EXIST TRY AGAIN");
						}
						heExit = false;
						
						break;
					case 4: //CERTAIN ACCOUNT//
						heExit = false;
						break;
					case 0:
						heExit = true;
						break;
				}
			}
			catch (InputMismatchException e)
			{
				sc.next();
				l.trace("\t ADMIN ENTERED INVALID OPTION");
				System.out.println("INVALID OPTION PLEASE TRY AGAIN");
			}
		}while(opt < 0 );
		return heExit;
		
	}
	//================================================ EMPLOYEE STUFF ==========================================================================
	public static boolean doEmployeeStuff(Employee em)
	{
		
		return false;
	}
	//================================================ CUSTOMER STUFF ==========================================================================
	public static boolean doCustomerStuff(Customer cus)
	{
		
		return false;
	}
	//==================================== MAIN METHOD =======================================================================================
	public static void main(String[] args)
	{
		do
		{
			startBank();
		}while(true);
	}

}

package com.revature.bankingapp.BankingAppX;

import java.io.*;
import java.util.*;

import com.revature.bankingapp.BankingAppX.account.Account;
import com.revature.bankingapp.BankingAppX.admin.Admin;
import com.revature.bankingapp.BankingAppX.menus.*;
import com.revature.bankingapp.BankingAppX.users.User;
import com.revature.bankingapp.BankingAppX.dao.*;
import com.revature.bankingapp.BankingAppX.service.*;
import com.revature.bankingapp.BankingAppX.util.*;

public class App 
{
    public static void main( String[] args ) throws NoSuchFieldException, SecurityException
    {
    	Service serv = new Service();
    	ArrayList<User> adminList = new ArrayList();
    	ArrayList<User> employeeList = new ArrayList();
    	ArrayList<User> customerList = new ArrayList();
    	boolean programActive = true;
    	boolean loggedIn = false;
    	boolean adminLoggedIn = false;
    	Scanner scan = new Scanner(System.in);
    	int choice;
    	
    	while(programActive)
    	{
    		StartMenu.welcome();
    		choice = scan.nextInt();
    		switch(choice)
    		{
    		case 0:
    			AdminMenu.adminMenu();
    			choice = scan.nextInt();
    			switch(choice)
    			{
    			case 1:
    				AdminMenu.adminCreate();
    				adminList.add(AdminMenu.getAdminObject());
    				serv.addAdmin(AdminMenu.getAdminObject().getFirstName(), AdminMenu.getAdminObject().getLastName(),
    						AdminMenu.getAdminObject().getUserName(), AdminMenu.getAdminObject().getPassword(),
    						AdminMenu.getAdminObject().getRole(), AdminMenu.getAdminObject().getAdminPin());
    				break;
    			case 2:
    				AdminMenu.adminLogin();
    				User temp = new User();
    				temp = serv.viewCertainAccountForAdmin(AdminMenu.getUsername());
    				adminList.add(temp);
    				if(adminList.get(0).getUserName().equals(AdminMenu.getUsername()) && adminList.get(0).getPassword().equals(AdminMenu.getPassword()) 
    						&& adminList.get(0).getAdminPin().equals(AdminMenu.getAdminPin()))
    				{
    					System.out.println("You have logged in!");
    					adminLoggedIn = true;
    					while(adminLoggedIn)
    					{
    						System.out.println("Welcome Administrator");
    						System.out.println("Here are your bank's current accounts: ");
    						serv.viewAllAccounts();
    						System.out.println("\n Thanks for viewing our accounts!");
    						System.out.println("Logging out!");
    						adminLoggedIn = false;
    					}
    				}
    				else
    				{
    					System.out.println("You have entered invalid information.");
    				}
    				break;
    			}
    			break;
    		case 1:
    			EmployeeMenu.empMenu();
    			choice = scan.nextInt();
    			switch(choice)
    			{
    			case 1:
    				EmployeeMenu.empCreate();
    				employeeList.add(EmployeeMenu.getEmp());
    				serv.addUser(EmployeeMenu.getEmp().getFirstName(), EmployeeMenu.getEmp().getLastName(), 
    						EmployeeMenu.getEmp().getUserName(), EmployeeMenu.getEmp().getPassword(), 
    						EmployeeMenu.getEmp().getRole());
    				break;
    			case 2:
    				EmployeeMenu.empLogin();
    				User temp = new User();
    				temp = serv.viewCertainAccount(EmployeeMenu.getUsername());
    				employeeList.add(temp);
    				if(employeeList.get(0).getUserName().equals(EmployeeMenu.getUsername()) && employeeList.get(0).getPassword().equals(EmployeeMenu.getPassword()))
    				{
    					System.out.println("You have logged in!");
    					loggedIn = true;
    					while(loggedIn)
    					{
    						System.out.println("Would you like to see if there are pending accounts?");
    						System.out.println("Please enter 1 for yes, and 2 for no.");
    						int check = scan.nextInt();
    						if(check == 1)
    						{
    							ArrayList<Account> accs = new ArrayList();
    							accs = serv.viewPendingAccounts();
    							String[] strArr = new String[accs.size()];
    							int count = 0;
    							for(Account a : accs)
    							{
    								strArr[count] = a.toString();
    								count++;
    							}
    							for(int x = 0; x < strArr.length; x++)
    							{
    								System.out.println(strArr[x]);
    							}
    							System.out.println("Would you like to validate the pending accounts?");
    							System.out.println("Please enter 1 for yes, and 2 for no.");
    							int checked = scan.nextInt();
    							if(checked == 1)
    							{
    								System.out.println("Now printing pending accounts!\n");
    								for(Account a : accs)
    								{
    									serv.validateAccounts(EmployeeMenu.getEmp(), a);
    								}
    								System.out.println("Thanks for validating accounts!");
    							}
    							else if(checked == 2)
    							{
    								System.out.println("Thanks for looking at the pending accounts!");
    								System.out.println("Logging out");
    							}
    							else
    							{
    								System.out.println("Invalid command, logging out!");
    							}
    							loggedIn = false;
    						}
    						else if(check == 2)
    						{
    							System.out.println("Alright, thanks for logging in!");
    							loggedIn = false;
    						}
    						else
    						{
    							System.out.println("Invalid command, thanks for logging in!");
    							loggedIn = false;
    						}
    					}
    				}
    				break;
    			}
    			break;
    		case 2:
    			CustomerMenu.custMenu();
    			choice = scan.nextInt();
    			switch(choice)
    			{
    			case 1:
    				CustomerMenu.custCreate();
    				customerList.add(CustomerMenu.getUser());
    				serv.addUser(customerList.get(0).getFirstName(), customerList.get(0).getLastName(), customerList.get(0).getUserName(), 
    						customerList.get(0).getPassword(), customerList.get(0).getRole());
    				serv.addAccount(CustomerMenu.getUser());
    				break;
    			case 2:
    				CustomerMenu.custLogin();
    				User temps = new User();
    				temps = serv.viewCertainAccount(CustomerMenu.getUsername());
    				customerList.add(temps);
    				if(customerList.get(0).getUserName().equals(CustomerMenu.getUsername()) && customerList.get(0).getPassword().equals(CustomerMenu.getPassword()))
    				{
    					System.out.println("You have logged in!");
    					loggedIn = true;
    					while(loggedIn)
    					{
    						if(customerList.get(0).getActive() == true)
    						{
    							System.out.println("Press 1 for deposit");
    							System.out.println("Press 2 for withdrawl");
    							System.out.println("Press 3 to exit");
    							int option = scan.nextInt();
    							if(option == 1)
    							{
    								System.out.println("How much would you like to deposit");
    								double dep = scan.nextDouble();
    								serv.accountDeposit(CustomerMenu.getUsername(), dep);
    								User u = serv.viewCertainAccount(CustomerMenu.getUsername());
    								System.out.println("Your balance is: " + (u.getAccountBalance() + dep));
    							}
    							else if(option == 2)
    							{
    								System.out.println("How much would you like to withdrawl");
    								double with = scan.nextDouble();
    								serv.accountWithdrawl(CustomerMenu.getUsername(), with);
    								User u = serv.viewCertainAccount(CustomerMenu.getUsername());
    								System.out.println("Your balance is: " + (u.getAccountBalance() - with));
    							}
    							else if(option == 3)
    							{
    								System.out.println("Logging out!");
    								loggedIn = false;
    							}
    							else
    							{
    								System.out.println("Invalid Option");
    								System.out.println("Exiting");
    							}
    						}
    						else
    						{
    							System.out.println("Sorry, your account has not been activated yet!");
    							System.out.println("Please check back later.");
    							loggedIn = false;
    						}
    					}
    				}
    			}
    			break;
    		case 3:
    			System.out.println("Thanks for banking with us!");
    			programActive = false;
    		}
    	}
    }
}

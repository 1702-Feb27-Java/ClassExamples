package com.bankingapp.testbanking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


import org.apache.log4j.Logger;

import com.bankingapp.peoplepack.Admin;
import com.bankingapp.peoplepack.Customer;
import com.bankingapp.peoplepack.Employee;
import com.bankingapp.peoplepack.People;

public class TestMain2
{
	//Scanner sc = new Scanner(System.in);
	//SEE IF THE PERSON HAS A USERNAME AND PASSWORD ==============================================
	public static boolean hasCredentials()
	{
		String input;
		Scanner sc = new Scanner(System.in);
		do
		{
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
			System.out.println("PLEASE ENTER Y OR N\n");
		}while (!(input.equals("Y")) && !(input.equals("N")));
		return false;
	}
	
	//CREATE A USERNAME AND PASSWORD FOR THE PERSON ===================================================
	public static boolean createCredentials()
	{
		boolean loginCheck;
		Scanner sc = new Scanner(System.in);
		do
		{
			String username, password;
			System.out.println("============= CREATE LOGIN =============");
			System.out.print("Enter your username: ");
			username = sc.next();
			System.out.print("Enter your password: ");
			password = sc.next();
		
			loginCheck = People.ifExists(username);
		
			if (!loginCheck)
			{
				try
				{
					FileWriter fw = new FileWriter("logins.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					String wrt = "\n3:"+username+":"+password+":N/A:N/A:N/A:N/A";
					bw.write(wrt);
					bw.close();
					System.out.println("USER CREATED \n");

					return true;
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("THE USERNAME YOU ARE TRYING TO USE ALREADY EXISTS PLEASE TRY AGAIN\n");
		}while(loginCheck);
		return false;
	}
	//================= ENTER CREDENTIALS ===========================================
	public static String enterCredentials ()
	{
		String loginUser;
		Scanner sc = new Scanner(System.in);
		String username, password;
		do
		{
			System.out.println("============= LOGIN =============");
			System.out.print("Enter your username: ");
			username = sc.next();
			System.out.print("Enter your password: ");
			password = sc.next();
		
			loginUser = People.login(username, password);
			
			if (loginUser.equals("0"))
				System.out.println("WRONG USERNAME OR PASSWORD \n");
		}while(loginUser.equals("0"));
		return loginUser+":"+username+":"+password;
		
	}
	//RETURNS ALL THE DATA FOR AN ACCOUNT WITH USERNAME MATCING NAME================================
	public static String getType(String name)
	{
		BufferedReader br =null;
    	try
		{
			br=new BufferedReader(new FileReader("logins.txt"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	String line;
    	String[] fields= null;
    	try
		{
			while((line=br.readLine()) != null)
			{
				if (line.isEmpty())
				{
					continue;
				}
				fields = line.split(":");
				if (fields[1].equals(name))
				{
					return line;
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	return "0";
	}
	//ADMIN STUFF ==========================================================================
	public static void doAdminStuff (Admin admin)
	{
		Scanner sc = new Scanner(System.in);
		String choice;
		do 
		{
			Admin.getMenu(admin);
			choice = sc.next();
		
			if (choice.equals("1"))
			{
				admin.viewAccounts();
			}
			else if (choice.equals("2"))
			{
				System.out.print("Enter the account username you want to view: ");
				String name = sc.next();
				admin.viewAccounts(name);
			}
			else if (choice.equals("3"))
			{
				System.out.print("Enter the account username you want to edit: ");
				String name = sc.next();
				String res = getType(name);
				String input;
				
				//IF ITS AN ADMIN
				if(res.startsWith("1"))
				{
					Admin na = new Admin(res);
					do
					{
						Admin.getEditMenu(na);
						input = sc.next();
						String newValue;
						if (input.equals("1"))
						{
							boolean tryAgain = false;
							do
							{
								System.out.print("Enter the new username you want to change it too: ");
								newValue=sc.next();
								tryAgain = admin.editUsername(na,newValue);
								System.out.println(tryAgain);
							}while(!tryAgain);
						}
					}while (!input.equals("0"));
				}
				
				//IF ITS AN EMPLOYEE
				else if(res.startsWith("2"))
				{
					Employee ne = new Employee(res);
					do
					{
						Admin.getEditMenu(ne);
						input = sc.next();
						String newValue;
						if (input.equals("1"))
						{
							boolean tryAgain = false;
							do
							{
								System.out.print("Enter the new username you want to change it too: ");
								newValue=sc.next();
								tryAgain = admin.editUsername(ne,newValue);
							
								if(!tryAgain)
									System.out.println("THE USERNAME ALREADY EXISTS\n");
							}while(!tryAgain);
						}
						else if (input.equals("2"))
						{
							System.out.print("Enter the new password you want to change it too: ");
							newValue=sc.next();
							 admin.editPassword(ne,newValue);
							
						}
					}while (!input.equals("0"));
				}
				
				//IF ITS A CUSTOMER
				else if(res.startsWith("3"))
				{
					Customer nc = new Customer(res);
					do
					{
						Admin.getEditMenu(nc);
						input = sc.next();
						String newValue;
						if (input.equals("1"))
						{
							boolean tryAgain = false;
							do
							{
								System.out.print("Enter the new username you want to change it too: ");
								newValue=sc.next();
								tryAgain = admin.editUsername(nc,newValue);
							
								if(!tryAgain)
									System.out.println("THE USERNAME ALREADY EXISTS\n");
							}while(!tryAgain);
						}
						else if (input.equals("2"))
						{
							System.out.print("Enter the new password you want to change it too: ");
							newValue=sc.next();
							 admin.editPassword(nc,newValue);
							
						}
						else if (input.equals("3") || input.equals("4"))
						{

							System.out.println("Enter the balance");
							int change;
							try
							{
								change=sc.nextInt();
								if (input.equals("3"))
								{
									nc.check.setAmount(change);
								}
								else
								{
									nc.savings.setAmount(change);
								}
							}
							catch (InputMismatchException e)
							{
								System.out.println("YOU DID NOT ENTER A INTERGER" );
								System.out.println("GOING BACK TO MENU");
								sc.next();
							}
						}
					}while(!input.equals("0"));
				}
			}
		}while (!choice.equals("0"));
	}
	public static void doEmployeeStuff (Employee emp)
	{
		String choice;
		Scanner sc = new Scanner(System.in);
		do 
		{
			Employee.getMenu(emp);
			choice = sc.next();
		
			if (choice.equals("1"))
			{
				emp.viewCustomers();
			}
			else if (choice.equals("2"))
			{
				String combo;
				int count =0;
				Customer hasUser = null;
				do
				{
					System.out.print("Enter the account username you want to approve: ");
					String name = sc.next();
					combo = emp.checkAccounts(name);
					if (combo == null)
					{
						System.out.println("ENTER A VALID NAME\n");
					}
				}while(combo == null);
				String [] userInfo = combo.split(",");
				hasUser= new Customer(userInfo[0]);
				count = Integer.parseInt(userInfo[1]);
				if (hasUser.getHasChecking() && hasUser.getHasSavings())
				{
					System.out.println("THE USER DOES NOT HAVE ANY PENDING APPROVALS\n");
				}
				else if (count == 3)
				{
					System.out.println("HAS PENDING CHECKING AND SAVINGS");
				}
				else if (count == 1)
				{
					System.out.println("HAS A PENDING CHECKING");
				}
				else if (count == 2)
				{
					System.out.println("HAS A PENDING SAVINGS");
				}
			}
		}while (!choice.equals("0"));
	}
		
	//================================================================================
	static final Logger l = Logger.getRootLogger();
	public static void main(String[] args)
	{
		Admin admin = null;
		Employee emp = null;
		Customer customer = null;
		boolean result;
		String checkCreds;
		//FIRST CHECK IF THEY HAVE CREDENTIALS FOR BANKAPP
		result = hasCredentials();
			//NO GO TO SIGNUP
		if (!result)
		{
			createCredentials();
		}
		//DO GO TO LOGIN PAGE
		//LOGIN PAGE
			//USERNAME // PASSWORD
			//INCORRECT DO AGAIN
		checkCreds = enterCredentials();
		String [] user = checkCreds.split(":");

		//CORRECT PRINT OUT MENU FOR THAT TYPE OF PERSON
		switch (user[0])
		{
			case "1":
				admin = new Admin(user[1], user[2]);
				doAdminStuff(admin);
				break;
			case "2":
				emp = new Employee(user[1], user[2]);
				doEmployeeStuff(emp);
				break;
			case "3":
				Customer.getMenu();
				customer = new Customer(user[1], user[2]);
				break;
		}
	}
}

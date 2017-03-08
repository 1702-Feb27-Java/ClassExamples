package com.bankingapp.testbanking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


import org.apache.log4j.Logger;

import com.bankingapp.bankaccount.CheckingAccount;
import com.bankingapp.bankaccount.SavingsAccount;
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
	//CREATE A USERNAME AND PASSWORD FOR THE PERSON ===================================================
	public static boolean createCredentials()
	{
		boolean loginCheck;
		Scanner sc = new Scanner(System.in);
		do
		{
			l.info("USER CREATING CREDENTIALS");
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
					String wrt = "\n3:"+username+":"+password+":N/A:0:N/A:0";
					bw.write(wrt);
					bw.close();
					System.out.println("USER CREATED \n");
					fw.close();
					l.info("USER CREATED NEW MEMBER");
					return true;
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			l.warn("USER ATTEMPTED USER  WITH USERAME THAT ALREADY EXISTS");
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
			l.trace("USER IN LOGIN MENU");
			System.out.println("============= LOGIN =============");
			System.out.print("Enter your username: ");
			username = sc.next();
			System.out.print("Enter your password: ");
			password = sc.next();
		
			loginUser = People.login(username, password);
			
			if (loginUser.startsWith("0"))
			{
				l.warn("USER ENTERED WRONG CREDENTIALS");
				System.out.println("WRONG USERNAME OR PASSWORD \n");
			}
		}while(loginUser.startsWith("0"));
		return loginUser;
		
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
					br.close();
					return line;
				}
			}
			br.close();
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
							Employee t = new Employee("x","x");
							try
							{
								change=sc.nextInt();
								if (input.equals("3"))
								{
									nc.setHasChecking(true);
									nc.check = new CheckingAccount(change);
									nc.check.setAccountNumber(nc.getUsername().hashCode());
									t.updateFile(nc);
								}
								else
								{
									nc.setHasSavings(true);
									nc.savings = new SavingsAccount(change);
									nc.savings.setAccountNumber(nc.getUsername().hashCode()+1);
									t.updateFile(nc);
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
	//EMPLOYEE STUFF ==========================================================================
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
				//System.out.println(combo);
				hasUser= new Customer(userInfo[0]);
				count = Integer.parseInt(userInfo[1]);
				
				String approve;
				if ((hasUser.getHasChecking() && hasUser.getHasSavings()) || count == 0)
				{
					System.out.println("THE USER DOES NOT HAVE ANY PENDING APPROVALS\n");
				}
				else if (count == 3)
				{
					System.out.println("HAS PENDING CHECKING AND SAVINGS");
					Employee.getOption(hasUser, "both");
					approve = sc.next();
					if(approve.equals("1"))
					{
						emp.approveChkAccount(hasUser, true);
						emp.approveSavAccount(hasUser, true);
					}
					else if(approve.equals("2"))
					{
						emp.approveChkAccount(hasUser, false);
						emp.approveSavAccount(hasUser, false);
					}
					emp.updateFile(hasUser);
				}
				else if (count == 1)
				{
					System.out.println("HAS A PENDING CHECKING");
					Employee.getOption(hasUser, "checking");
					approve = sc.next();
					if(approve.equals("1"))
					{
						emp.approveChkAccount(hasUser, true);
					}
					else if(approve.equals("2"))
					{
						emp.approveChkAccount(hasUser, false);
					}
					emp.updateFile(hasUser);
				}
				else if (count == 2)
				{
					System.out.println("HAS A PENDING SAVINGS");
					Employee.getOption(hasUser, "savings");
					approve = sc.next();
					if(approve.equals("1"))
					{
						emp.approveSavAccount(hasUser, true);
					}
					else if(approve.equals("2"))
					{
						emp.approveSavAccount(hasUser, false);
					}
					emp.updateFile(hasUser);
				}
			}
		}while (!choice.equals("0"));
	}
	//CUSTOMER STUFF ==========================================================================
	public static void doCustomerStuff (Customer cus)
	{
		Employee t = new Employee("x","x");
		String choice;
		Scanner sc = new Scanner(System.in);
		do 
		{
			Customer.getMenu(cus);
			choice = sc.next();
		
			boolean applySucc=false;
			if (choice.equals("1"))
			{
				applySucc = cus.applyAccnt("Checking");
				if(applySucc)
				{
					System.out.println("APPLIED SUCCESSFULLY\n");
					//t.updateFile(cus);
				}
				else
					System.out.println("YOU EITHER HAVE A PENDING OR OPEN ACCOUNT ALREADY\n");
			}
			else if (choice.equals("2"))
			{
				applySucc = cus.applyAccnt("Savings");
				if(applySucc)
				{
					System.out.println("APPLIED SUCCESSFULLY\n");
					//t.updateFile(cus);
				}
				else
					System.out.println("YOU EITHER HAVE A PENDING OR OPEN ACCOUNT ALREADY\n");
			}
			else if (choice.equals("3") || choice.equals("4"))
			{
				int amount=0;
				do
				{
					System.out.println("Enter Amount: ");
					int transaction = 1;
					try
					{
						amount=sc.nextInt();
						if(choice.equals("3"))
							transaction = cus.deposit(amount, "Checking");
						else if(choice.equals("4"))
							transaction = cus.deposit(amount, "Savings");
					}
					catch (InputMismatchException e)
					{
						System.out.println("YOU DID NOT ENTER A INTERGER" );
						System.out.println("GOING BACK TO MENU");
						sc.next();
						break;
					}
					if(transaction == 0)
					{
						System.out.println("TRANSACTION COMPLETE");
						if(choice.equals("3"))
							System.out.println("NEW BALANACE: "+cus.check.getAmount()+"\n");
						else if(choice.equals("4"))
							System.out.println("NEW BALANACE: "+cus.savings.getAmount()+"\n");
						
						//UPDATE FILE
						t.updateFile(cus);
					}
					else if(transaction == 1)
					{
						System.out.println("TRANSACTION ERROR: NOT ENOUGH FUNDS\n");
					}
					else if(transaction == 2)
					{
						System.out.println("NO ACCOUNT: OR STILL PENDING APPROVAL\n");
//						System.out.println("NO ACCOUNT: PLEASE APPLY FOR ONE\n");
					}
				}while(amount==0);
			}
			else if (choice.equals("5") || choice.equals("6"))
			{
				int amount=0;
				do
				{
					System.out.println("Enter Amount: ");
					int transaction = 1;
					try
					{
						amount=sc.nextInt();
						if(choice.equals("5"))
							transaction = cus.withdraw(amount, "Checking");
						else if(choice.equals("6"))
							transaction = cus.withdraw(amount, "Savings");
					}
					catch (InputMismatchException e)
					{
						System.out.println("YOU DID NOT ENTER A INTERGER" );
						System.out.println("GOING BACK TO MENU");
						sc.next();
						break;
					}
					if(transaction == 0)
					{
						System.out.println("TRANSACTION COMPLETE");
						if(choice.equals("5"))
							System.out.println("NEW BALANACE: "+cus.check.getAmount()+"\n");
						else if(choice.equals("6"))
							System.out.println("NEW BALANACE: "+cus.savings.getAmount()+"\n");
						
						//UPDATE FILE
						t.updateFile(cus);
						//System.out.println("UPDATE THE FILE");
					}
					else if(transaction == 1)
					{
						System.out.println("TRANSACTION ERROR: NOT ENOUGH FUNDS\n");
					}
					else if(transaction == 2)
					{
						System.out.println("NO ACCOUNT: OR STILL PENDING APPROVAL\n");
					}
				}while(amount==0);	
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
				l.trace("ADMIN SIGNED IN");
				admin = new Admin(user[1], user[2]);
				doAdminStuff(admin);
				break;
			case "2":
				l.trace("EMPLOYEE SIGNED IN");
				emp = new Employee(user[1], user[2]);
				doEmployeeStuff(emp);
				break;
			case "3":
				l.trace("CUSTOMER SIGNED IN");
				customer = new Customer(checkCreds);
				doCustomerStuff(customer);
				break;
		}
	}
}

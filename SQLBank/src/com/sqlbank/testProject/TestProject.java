package com.sqlbank.testProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;
import com.sqlbank.service.ServiceAdminImp;
import com.sqlbank.service.ServiceCusImp;
import com.sqlbank.service.ServiceEmpImp;
import com.sqlbank.service.ServiceImplementation;
import org.apache.log4j.Logger;
import org.w3c.dom.CDATASection;

public class TestProject
{
	static Scanner sc = new Scanner(System.in);
	static ServiceImplementation serve = new ServiceImplementation();
	static ServiceAdminImp aServe = new ServiceAdminImp();
	static ServiceEmpImp eServe = new ServiceEmpImp();
	static ServiceCusImp cServe = new ServiceCusImp();
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
			if(result > 4 || result < 0)
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
				do{
					heExit =doEmployeeStuff(em);
				}while (!heExit);
				break;
			case 3:
				l.trace("CUSTOMER IS LOGGED IN");
				System.out.println("Customer");
				Customer cus = serve.getCustomer(username);
				do{
					heExit=doCustomerStuff(cus);
				}while (!heExit);
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
		System.out.println("ENTER THE USERNAME: ");
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
						int worked = 0;
						String check = getName();
						worked = aServe.viewAaccount(check);
						if(worked <1)
						{
							System.out.println("USER DOES NOT EXIST TRY AGAIN");
						}
						heExit = false;
						
						break;
					case 4: //EDIT ACCOUNT//
						heExit = false;
						worked = 0;
						check = getName();
						worked = aServe.viewAaccount(check);
						if(worked < 1)
						{
							System.out.println("USER DOES NOT EXIST TRY AGAIN");
							break;
						}
						//GET WHAT THEY WANT TO CHANGE
						int opt2=0;
						switch (worked)
						{
							case 1:
								ad.getEditMenu(serve.getAdmin(check));
								break;
							case 2:
								ad.getEditMenu(serve.getEmployee(check));
								break;
							case 3:
								ad.getEditMenu(serve.getCustomer(check));
								break;
						}
						try
						{
							opt2 = sc.nextInt();
						}
						catch(InputMismatchException e)
						{
							sc.next();
							System.out.println("\nEnter a valid number");
						}
						if (opt2 > 0 && opt2 < 3)
						{
							if(opt2 == 1)
							{
								String newName="";
								System.out.println("ENTER NEW USERNAME");
								newName=sc.next();
								if(aServe.isUsernameValid(newName))
								{
									if (serve.updateUsername(check, newName))
										System.out.println("SUCCESS");
								}
								else
									System.out.println("USER NAME ALREADY IN USE PLEASE TRY AGAIN");
							}
							else if(opt2 == 2)
							{
								String newPass="";
								System.out.println("ENTER NEW PASSWORD");
								newPass=sc.next();
								if (serve.updatePassword(check, newPass))
										System.out.println("SUCCESS");
							}
						}
						else if (opt2 == 3)
						{
							Customer c = aServe.getCustomer(check);
							c.accounts = new ArrayList<Account>(aServe.accounts(c));
							System.out.println("==========================");
							List<Integer> ids = new ArrayList<Integer> ();
							for (Account x: c.accounts)
							{
								System.out.println(x.getId() +":$" + x.getAmount());
								ids.add(x.getId());
							}
							System.out.println("==========================");
							int opt3 =0;
							System.out.println("Choose an account by id: ");
							try
							{
								opt3 = sc.nextInt();
							}
							catch (InputMismatchException e)
							{
							sc.next();
							System.out.println("\nEnter a valid account number");
							}
							double newBalance =0;
							if(ids.contains(opt3))
							{
								System.out.println("ENTER NEW AMOUNT");
								
								try
								{
									newBalance = sc.nextDouble();
								}
								catch(InputMismatchException e)
								{
									sc.next();
									System.out.println("Please enter a valid amount");
								}
								for ( int i =0; i < c.accounts.size(); i++)
								{
									if (c.accounts.get(i).getId() == opt3)
									{
										c.accounts.get(i).setAmount(newBalance);
										if(aServe.updateBalance(c.accounts.get(i)))
										{
											System.out.println("SUCCESS");
										}
										else
										{
											System.out.println("ERROR");
										}
										break;
									}
								}
							}
							else
								System.out.println("ENTER A VALID ACCOUNT ID");

						}
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
		boolean heExit = false;
		int opt = -1;
		do
		{
			em.getMenu();
			try
			{
				opt = sc.nextInt();
				
			}
			catch(InputMismatchException e)
			{
				System.out.println("Please enter a valid integer option");
			}
			if(opt == 1)
			{
				heExit= false;
				ArrayList<Customer> myCus = new ArrayList<Customer>(eServe.viewMyCustomers(em.getUsername()));
				if(myCus.isEmpty())
				{
					System.out.println("YOU HAVE NOT APPROVED ANY CUSTOMERS");
				}
				else
				{
					for (Customer x : myCus)
					{
						System.out.println("----------------- CUSTOMER --------------------");
						x.printAccount();
						for (Account a : x.accounts)
						{
							a.printAccount();
						}
						System.out.println("-------------------------------------------------");
					}
				}
				
			}
			else if (opt == 2)
			{
				heExit= false;
				ArrayList<Customer> pendingCus = new ArrayList<>(eServe.getPending());
				Map <Integer, Integer> account_users = new HashMap<Integer, Integer> () ;
				if (pendingCus.isEmpty())
				{
					System.out.println("THERE ARE NOT PENDING ACCOUNTS ");
				}
				else
				{
					List<Integer> ids = new ArrayList<>();
					for (int i = 0; i < pendingCus.size(); i++)
					{
						System.out.println("----------------- PENDING CUSTOMER --------------------");
						Customer x = pendingCus.get(i);
						x.printAccount();
						for (Account a : x.accounts)
						{
							a.printAccount();
							
							account_users.put(a.getId(), i);
							ids.add(a.getId());
						}
					}
					System.out.println("-------------------------------------------------");
					int opt3 =0;
					System.out.println("Choose an account by id: ");
					System.out.println("OR 0 TO EXIT");
					try
					{
						opt3 = sc.nextInt();
					}
					catch (InputMismatchException e)
					{
					sc.next();
					System.out.println("\nEnter a valid account number");
					}
					if(ids.contains(opt3))
					{
						for ( Account x: pendingCus.get(account_users.get(opt3)).accounts)
						{
							Customer c =  pendingCus.get(account_users.get(opt3));
							//APPROVE OR DECLINE
							int option = 0;
							em.getOption(pendingCus.get(account_users.get(opt3)), x.getType());
							try
							{
								option = sc.nextInt();
							}
							catch (InputMismatchException e)
							{
								sc.next();
								System.out.println("INVALID OPTION");	
							}
							
							if (x.getId() == opt3)
							{
								if (option == 1)
								{
									x.setResolver(em.getUsername());
									x.setStatus("Approved");
									if(eServe.updateAccount(x.getId(), em.getId(), 3))
									{
										System.out.println("SUCCESS");
									}
								}
								else if (option == 2)
								{
									x.setResolver(em.getUsername());
									x.setStatus("Declined");
									if(eServe.updateAccount(x.getId(), em.getId(), 2))
									{
										System.out.println("SUCCESS");
									}
								}
							}
							
						}
						
					}
					else if (opt3 == 0)
					{
						break;
					}
					else
					{
						System.out.println("INVALID ACCOUNT ID");
					}
				}
			}
			else if(opt == 0)
			{
				heExit = true;
			}
			else
				System.out.println("\nNOT A VALID OPTION");
		}while (opt < 0);
		
		return heExit;
	}
	//================================================ CUSTOMER STUFF ==========================================================================
	public static boolean doCustomerStuff(Customer cus)
	{
		boolean heExit = false;
		int opt = -1;
		do
		{
			cus.getMenu();
			try
			{
				opt = sc.nextInt();
				if (opt == 1)
				{
					heExit = false;
					cServe.viewAccount(cus);
				}
				else if (opt == 2)
				{
					heExit = false;
					String newName="";
					System.out.println("ENTER NEW USERNAME");
					newName=sc.next();
					if(cServe.isUsernameValid(newName))
					{
						if (serve.updateUsername(cus.getUsername(), newName))
						{
							cus.setUsername(newName);
							System.out.println("SUCCESS");
						}
					}
					else
						System.out.println("USER NAME ALREADY IN USE PLEASE TRY AGAIN");	
				}
				else if (opt == 3 )
				{
					heExit = false;
					String newPass="";
					System.out.println("ENTER NEW PASSWORD");
					newPass=sc.next();
					if (serve.updatePassword(cus.getUsername(), newPass))
					{
							cus.setPassword(newPass);
							System.out.println("SUCCESS");
					}
				}
				else if ( opt == 4) //SIGN UP
				{
					heExit = false;
					int acctype =0;
					cus.signAccount();
					try
					{
						acctype = sc.nextInt();
					}
					catch (InputMismatchException e)
					{
						sc.next();
						System.out.println("ENTER A VALID DIGIT");
					}
					if( acctype == 1)
					{
						if(cServe.applyAccount (cus, 1))
						{
							System.out.println("SUCCESS");
						}
					}
					else if (acctype == 2)
					{
						if(cServe.applyAccount (cus, 2))
						{
							System.out.println("SUCCESS");
						}
					}
				}
				else if ( opt == 5) //DEPOSIT 
				{
					heExit = false;
					int acc_id =0;
					ArrayList<Account> vAccounts = cServe.getValidAccounts(cus);
					if(vAccounts.isEmpty())
					{
						System.out.println("CANNOT DEPOSIT NO ACTIVE ACCOUNTS");
					}
					else
					{
						System.out.println("Choose and account by id: ");
						System.out.println("Anything else will return to menu");
						try
						{
							acc_id = sc.nextInt();
						} catch (InputMismatchException e)
						{
							System.out.println("INVALID ENTRY");
							// TODO: handle exception
						}
						for(Account x : vAccounts)
						{
							if (x.getId() == acc_id)
							{
								double amnt = 0;
								System.out.println("Enter amount to deposit");
								try
								{
									amnt = sc.nextDouble();
								}
								catch(InputMismatchException e)
								{
									System.out.println("Enter a valid amount");
								}
								x.deposit(amnt);
								cServe.updateBalance(x);
							}
						}
					}
				}
				else if ( opt == 6) //WITHDRAW 
				{
					int acc_id =0;
					heExit = false;
					ArrayList<Account> vAccounts = cServe.getValidAccounts(cus);
					if(vAccounts.isEmpty())
					{
						System.out.println("CANNOT WITHDRAW NO ACTIVE ACCOUNTS");
					}
					else
					{
						System.out.println("Choose and account by id: ");
						System.out.println("Anything else will return to menu");
						try
						{
							acc_id = sc.nextInt();
						} catch (InputMismatchException e)
						{
							System.out.println("INVALID ENTRY");
							// TODO: handle exception
						}
						for(Account x : vAccounts)
						{
							if (x.getId() == acc_id)
							{
								double amnt = 0;
								System.out.println("Enter amount to withdraw");
								try
								{
									amnt = sc.nextDouble();
								}
								catch(InputMismatchException e)
								{
									System.out.println("Enter a valid amount");
								}
								if (x.withdraw(amnt))
								{
									cServe.updateBalance(x);
								}
								else
								{
									System.out.println("INSUFFICIENT FUNDS");
								}
							}
						}
					}
				}
				else if (opt == 0)
				{
					heExit = true;
				}
			}
			catch(InputMismatchException e)
			{
				sc.next();
				System.out.println("Please enter a valid option");
			}
			
			
		}while(opt < 0);
		return heExit;
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
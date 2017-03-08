package com.revature.bankingapp.BankingAppX;

import java.io.*;
import java.util.*;

import com.revature.bankingapp.BankingAppX.admin.Admin;
import com.revature.bankingapp.BankingAppX.adminMenu.AdminMenu;
import com.revature.bankingapp.BankingAppX.customerMenu.CustomerMenu;
import com.revature.bankingapp.BankingAppX.employeeMenu.EmployeeMenu;
import com.revature.bankingapp.BankingAppX.startMenu.StartMenu;
import com.revature.bankingapp.BankingAppX.users.User;

public class App 
{
	static BufferedReader br1;
	static FileReader fr1;
	static BufferedWriter bw1;
	static FileWriter fw1;
	static BufferedReader br2;
	static FileReader fr2;
	static BufferedWriter bw2;
	static FileWriter fw2;
	static BufferedReader br3;
	static FileReader fr3;
	static BufferedWriter bw3;
	static FileWriter fw3;
	static String[] line;
    public static void main( String[] args ) throws NoSuchFieldException, SecurityException
    {
    	File file1 = new File("adminFile.txt");
    	File file2 = new File("employeeFile.txt");
    	File file3 = new File("customerFile.txt");
    	ArrayList<Admin> adminList = new ArrayList();
    	ArrayList<User> employeeList = new ArrayList();
    	ArrayList<User> customerList = new ArrayList();
    	StringBuilder sb = new StringBuilder();
    	boolean programActive = true;
    	boolean loggedIn = false;
    	boolean adminLoggedIn = false;
    	Scanner scan = new Scanner(System.in);
    	int choice;
    	
    	try
		{
			br1 = new BufferedReader(fr1 = new FileReader(file1));
		} 
    	catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	
    	try
		{
			bw1 = new BufferedWriter(fw1 = new FileWriter(file1));
		} 
    	catch (IOException e)
		{
			e.printStackTrace();
		}
    	
    	try
		{
			br2 = new BufferedReader(fr2 = new FileReader(file2));
		} 
    	catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	
    	try
		{
			bw2 = new BufferedWriter(fw2 = new FileWriter(file2));
		} 
    	catch (IOException e)
		{
			e.printStackTrace();
		}
    	
    	try
		{
			br3 = new BufferedReader(fr3 = new FileReader(file3));
		} 
    	catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	
    	try
		{
			bw3 = new BufferedWriter(fw3 = new FileWriter(file3));
		} 
    	catch (IOException e)
		{
			e.printStackTrace();
		}
    	
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
    				sb = new StringBuilder();
    				sb.append(adminList.get(0).getUserName() + ",");
    				sb.append(adminList.get(0).getPassword() + ",");
    				sb.append(adminList.get(0).getRole());
    				try
					{
						bw1.write(sb.toString());
					} catch (IOException e)
					{
						e.printStackTrace();
					}
    				finally
    				{
    					try
						{
							bw1.close();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
    				}
    				break;
    			case 2:
    				AdminMenu.adminLogin();
    				try
					{
						line = br1.readLine().split(",");
					} catch (IOException e)
					{
						e.printStackTrace();
					}
    				adminList.add(new Admin());
    				adminList.get(0).setUserName(line[0]);
    				adminList.get(0).setPassword(line[1]);
    				adminList.get(0).setAdminPin(line[2]);
    				if(adminList.get(0).getUserName().equals(AdminMenu.getUsername()) && adminList.get(0).getPassword().equals(AdminMenu.getPassword()) 
    						&& adminList.get(0).getAdminPin().equals(AdminMenu.getAdminPin()))
    				{
    					System.out.println("You have logged in!");
    					adminLoggedIn = true;
    					while(adminLoggedIn)
    					{
    						
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
    				sb = new StringBuilder();
    				sb.append(employeeList.get(0).getUserName() + ",");
    				sb.append(employeeList.get(0).getPassword() + ",");
    				sb.append(employeeList.get(0).getRole());
    				try
					{
						bw2.write(sb.toString());
					} catch (IOException e)
					{
						e.printStackTrace();
					}
    				finally
    				{
    					try
						{
							bw2.close();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
    				}
    				break;
    			case 2:
    				EmployeeMenu.empLogin();
    				try
					{
						line = br2.readLine().split(",");
					} catch (IOException e)
					{
						e.printStackTrace();
					}
    				employeeList.add(new User());
    				employeeList.get(0).setUserName(line[0]);
    				employeeList.get(0).setPassword(line[1]);
    				employeeList.get(0).setRole(line[2]);
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
    							if(customerList.get(0).getActive() == false)
    							{
    								System.out.println("Would you like to validate this account?");
    								System.out.println("1 for yes and, 2 for no.");
    								int yea = scan.nextInt();
    								if(yea == 1)
    								{
    									boolean flaggy = true;
    									customerList.get(0).setActive(flaggy);
    									System.out.println("Account Validated");
    								}
    								else if(yea == 2)
    								{
    									System.out.println("Account remains invalidated");
    								}
    								else
    								{
    									System.out.println("Invalid option, account stays invalidated");
    								}
    							}
    							else
    							{
    								System.out.println("There are no accounts to validate.");
    								System.out.println("Thanks!");
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
    				sb = new StringBuilder();
    				sb.append(customerList.get(0).getUserName() + ",");
    				sb.append(customerList.get(0).getPassword() + ",");
    				sb.append(customerList.get(0).getRole() + ",");
    				sb.append(customerList.get(0).getAccountType() + ",");
    				sb.append(customerList.get(0).getAccountBalance() + ",");
    				sb.append(customerList.get(0).getActive());
    				try
					{
						bw3.write(sb.toString());
					} catch (IOException e)
					{
						e.printStackTrace();
					}
    				finally
    				{
    					try
						{
							bw3.close();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
    				}
    				break;
    			case 2:
    				CustomerMenu.custLogin();
    				try
					{
						line = br3.readLine().split(",");
					} catch (IOException e)
					{
						e.printStackTrace();
					}
    				customerList.add(new User());
    				customerList.get(0).setUserName(line[0]);
    				customerList.get(0).setPassword(line[1]);
    				customerList.get(0).setRole(line[2]);
    				customerList.get(0).setAccountType(line[3]);
    				customerList.get(0).setAccountBalace(Double.parseDouble(line[4]));
    				customerList.get(0).setActive(Boolean.parseBoolean(line[5]));
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
    								int dep = scan.nextInt();
    								customerList.get(0).setAccountBalace(customerList.get(0).getAccountBalance() + dep);
    								System.out.println("Your balance is: " + customerList.get(0).getAccountBalance());
    							}
    							else if(option == 2)
    							{
    								System.out.println("How much would you like to withdrawl");
    								int with = scan.nextInt();
    								customerList.get(0).setAccountBalace(customerList.get(0).getAccountBalance() - with);
    								System.out.println("Your balance is: " + customerList.get(0).getAccountBalance());
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

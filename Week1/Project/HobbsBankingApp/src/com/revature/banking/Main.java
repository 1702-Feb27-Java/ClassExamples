package com.revature.banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.banking.account.BankAccount;
import com.revature.banking.account.user.*;

import org.apache.log4j.Logger;

public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class); //get a logger that identifies this class in logs originating from it
	
	public static void printHelp() {
		System.out.println(
				"Banking App Help:" +
				"Invoke without arguments to bring up the customer interface.\n" +
				"Invoke with bank to bring up the employee interface.\n" +
				"Invoke with admin to bring up the admin interface.\n" +
				"Invoke with help to bring up this help message.");
	}

	public static void main(String[] args) {
		String cli = null;
		if (args.length > 0) {
			cli = args[0];
		}
		
		////////////////////////////////////////////////////////////////////////
		// CUSTOMER INTERFACE
		if (cli == null) {
			System.out.println("Customer interface");
			Scanner input = new Scanner(System.in);
			
			////////////////////////////////////////////////////////////////////
			// CUSTOMER MAIN MENU
			System.out.println("(1) Sign in\n(2) Create account");
			String option = "";
			do {
				option = input.nextLine();
			}
			while (!option.equals("1") && !option.equals("2"));
			System.out.println(option);
			
			if (option.equals("1")) { //try sign in
				////////////////////////////////////////////////////////////////
				// CUSTOMER SIGN IN
				
				ArrayList<CustomerUserAccount> customerAccounts = new ArrayList<>();
				ObjectInputStream objectInputStream = null;
				try {
					objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					customerAccounts = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
					objectInputStream.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				boolean login = false;
				String user = "", password = "";
				int customerAccountIndex = -1;
				do {
					System.out.println("Enter customer account name:");
					option = input.nextLine();
					for (int i = 0; i < customerAccounts.size(); i++) {
						if (option.equals(customerAccounts.get(i).getUsername())) {
							user = option;
							customerAccountIndex = i;
							do {
								System.out.println("Enter password:");
								option = input.nextLine();
							}
							while (!option.equals(customerAccounts.get(i).getPassword()));
							password = option;
							login = true;
						}
					}
					if (user.isEmpty()) {
						System.out.println("Customer does not exist");
						break;
					}
				}
				while (!login);
				
				while (login) {
					////////////////////////////////////////////////////////////
					// CUSTOMER HOME MENU 
					
					System.out.println("Customer logged in");
					
					System.out.println("Welcome " + user);
					System.out.println("(1) View Accounts\n(2) Logout");
					System.out.println("Select an option:");
					do {
						option = input.nextLine();
					}
					while (!option.equals("1") && !option.equals("2"));
					System.out.println(option);
					
					if (option.equals("1")) {
						////////////////////////////////////////////////////////
						// CUSTOMER VIEW ALL ACCOUNTS MENU
						
						boolean viewingAllAccountsMenu = true;
						do {
							System.out.println("Viewing all accounts");
							for (int i = 0; i < customerAccounts.get(customerAccountIndex).getAccounts().size(); i++) {
								System.out.println("(" + (i+1) + ") Account #" + (i+1));
							}
							System.out.println("(C) Create bank account");
							System.out.println("(Q) Quit");
							
							System.out.println("Select an option:");
							do {
								option = input.nextLine();
							}
							while (!option.toLowerCase().equals("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= customerAccounts.get(customerAccountIndex).getAccounts().size());
							System.out.println(option);
							
							if (option.toLowerCase().equals("q")) {
								viewingAllAccountsMenu = false;
							}
							else if (option.toLowerCase().equals("c")) {
								////////////////////////////////////////////////
								// CUSTOMER CREATE NEW BANK ACCOUNT
								
								System.out.println("Creating new bank account...");
								customerAccounts.get(customerAccountIndex).openNewAccount();
								logger.trace("New bank account opened for customer #" + customerAccountIndex);
								ObjectOutputStream objectOutputStream;
								try {
									objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
									objectOutputStream.writeObject(customerAccounts);
									objectOutputStream.flush();
									objectOutputStream.close();
									
									objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
									try {
										customerAccounts = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
										objectInputStream.close();
									} catch (ClassNotFoundException e) {
										e.printStackTrace();
									}
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								} //write out new bank account
								System.out.println("Created new bank account");
							}
							else {
								////////////////////////////////////////////////
								// CUSTOMER VIEW AN ACCOUNT MENU
								
								int accountIndex = Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0;
								System.out.println("Viewing Account #" + accountIndex);
								
								boolean viewingAnAccountMenu = true;
								do {
									System.out.println("(1) Open checking/savings\n(2) Deposit\n(3) Withdraw\n(Q) Quit");
									System.out.println("Select an option:");
									do {
										option = input.nextLine();
									}
									while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.toLowerCase().equals("q"));
									
									if (option.equals("1")) {
										////////////////////////////////////////
										// OPEN CHECKING/SAVINGS ACCOUNT MENU
										
										System.out.println("Opening a checking/savings for account #" + accountIndex);
										
										boolean openingCheckingSavingsMenu = true;
										do {
											System.out.println("(1) Open checking\n(2) Open savings\n(3)Quit");
											System.out.println("Select an option:");
											do {
												option = input.nextLine();
											}
											while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
											
											if (option.equals("1")) {
												if (customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkCheckingStatus() != null) {
													System.out.println("Applying for a checking account for current bank account...");
													customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).openChecking();
													System.out.println("Checking account application submitted");
													logger.trace("Checking account opened for account #" + accountIndex + " for customer #" + customerAccountIndex);
												}
												else {
													System.out.println("Checking account already open. Current status: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkCheckingStatus());
												}
											}
											else if (option.equals("2")) {
												if (customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkSavingsStatus() != null) {
													System.out.println("Applying for a savings account for current bank account...");
													customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).openSavings();
													System.out.println("Savings account application submitted");
													logger.trace("Savings account opened for account #" + accountIndex + " for customer #" + customerAccountIndex);
												}
												else {
													System.out.println("Savings account already open. Current status: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkSavingsStatus());
												}
											}
											else if (option.equals("3")) {
												System.out.println("Closing checking/savings menu...");
												openingCheckingSavingsMenu = false;
											}
										}
										while (openingCheckingSavingsMenu);
									}
									else if (option.equals("2")) {
										////////////////////////////////////////
										// DEPOSIT MENU
										
										System.out.println("Depositing into account #" + accountIndex);
										
										boolean depositing = true;
										do {
											System.out.println("(1) Deposit into checking\n(2) Deposit into savings\n(3)Quit");
											System.out.println("Select an option:");
											do {
												option = input.nextLine();
											}
											while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
											
											if (option.equals("1")) {
												if (customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkCheckingStatus() != null) {
													System.out.println("Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getCheckingBalance());
													System.out.println("Enter amount to deposit:");
													try {
														Double amount = Double.parseDouble(input.nextLine());
														customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).depositChecking(amount);
														
														ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
														objectOutputStream.writeObject(customerAccounts);
														objectOutputStream.flush();
														objectOutputStream.close();
														
														objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
														try {
															customerAccounts = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
															objectInputStream.close();
															
															System.out.println("Deposited into checking account. Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getCheckingBalance());
														} catch (ClassNotFoundException e) {
															e.printStackTrace();
														}
													}
													catch (NumberFormatException e) {
														System.out.println("Invalid dollar amount");
													} catch (FileNotFoundException e1) {
														e1.printStackTrace();
													} catch (IOException e1) {
														e1.printStackTrace();
													}
												}
												else {
													System.out.println("Checking account cannot be deposited into at this time. Current status: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkCheckingStatus());
												}
											}
											else if (option.equals("2")) {
												if (customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkSavingsStatus() != null) {
													System.out.println("Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getSavingsBalance());
													System.out.println("Enter amount to deposit:");
													try {
														Double amount = Double.parseDouble(input.nextLine());
														customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).depositSavings(amount);
														
														ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
														objectOutputStream.writeObject(customerAccounts);
														objectOutputStream.flush();
														objectOutputStream.close();
														
														objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
														try {
															customerAccounts = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
															objectInputStream.close();
															
															System.out.println("Deposited into savings account. Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getSavingsBalance());
														} catch (ClassNotFoundException e) {
															e.printStackTrace();
														}
													}
													catch (NumberFormatException e) {
														System.out.println("Invalid dollar amount");
													} catch (FileNotFoundException e1) {
														e1.printStackTrace();
													} catch (IOException e1) {
														e1.printStackTrace();
													}
												}
												else {
													System.out.println("Savings account cannot be deposited into at this time. Current status: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkSavingsStatus());
												}
											}
											else if (option.equals("3")) {
												System.out.println("Closing deposit menu...");
												depositing = false;
											}
										}
										while (depositing);
									}
									else if (option.equals("3")) {
										////////////////////////////////////////
										// WITHDRAW MENU
										
										System.out.println("Withdrawing from account #" + accountIndex);
										
										boolean withdrawing = true;
										do {
											System.out.println("(1) Withdraw from checking\n(2) Withdraw from savings\n(3)Quit");
											System.out.println("Select an option:");
											do {
												option = input.nextLine();
											}
											while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
											
											if (option.equals("1")) {
												if (customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkCheckingStatus() != null) {
													System.out.println("Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getCheckingBalance());
													System.out.println("Enter amount to withdraw:");
													try {
														Double amount = Double.parseDouble(input.nextLine());
														customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).withdrawChecking(amount);
														
														ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
														objectOutputStream.writeObject(customerAccounts);
														objectOutputStream.flush();
														objectOutputStream.close();
														
														objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
														try {
															customerAccounts = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
															objectInputStream.close();
															
															System.out.println("Withdrew from checking account. Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getCheckingBalance());
														} catch (ClassNotFoundException e) {
															e.printStackTrace();
														}
													}
													catch (NumberFormatException e) {
														System.out.println("Invalid dollar amount");
													} catch (FileNotFoundException e1) {
														e1.printStackTrace();
													} catch (IOException e1) {
														e1.printStackTrace();
													}
												}
												else {
													System.out.println("Checking account cannot be withdrawn from at this time. Current status: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkCheckingStatus());
												}
											}
											else if (option.equals("2")) {
												if (customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkSavingsStatus() != null) {
													System.out.println("Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getSavingsBalance());
													System.out.println("Enter amount to withdraw:");
													try {
														Double amount = Double.parseDouble(input.nextLine());
														customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).withdrawSavings(amount);
														
														ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
														objectOutputStream.writeObject(customerAccounts);
														objectOutputStream.flush();
														objectOutputStream.close();
														
														objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
														try {
															customerAccounts = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
															objectInputStream.close();
															
															System.out.println("Withdrew from savings account. Current balance: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).getSavingsBalance());
														} catch (ClassNotFoundException e) {
															e.printStackTrace();
														}
													}
													catch (NumberFormatException e) {
														System.out.println("Invalid dollar amount");
													} catch (FileNotFoundException e1) {
														e1.printStackTrace();
													} catch (IOException e1) {
														e1.printStackTrace();
													}
												}
												else {
													System.out.println("Savings account cannot be withdrawn from at this time. Current status: " + customerAccounts.get(customerAccountIndex).getAccounts().get(accountIndex).checkSavingsStatus());
												}
											}
											else if (option.equals("3")) {
												System.out.println("Closing withdraw menu...");
												withdrawing = false;
											}
										}
										while (withdrawing);
									}
									else if (option.toLowerCase().equals("q")) {
										System.out.println("Closing account menu...");
										viewingAnAccountMenu = false;
									}
									
								} while(viewingAnAccountMenu);
							}
						}
						while (viewingAllAccountsMenu);
					}
					else if (option.equals("2")) {
						System.out.println("Logging out...");
						login = false;
					}
				}
			}
			else if (option.equals("2")) { //do create user account
				////////////////////////////////////////////////////////////////
				// CUSTOMER USER ACCOUNT CREATION
				
				String user = "", pass = "", email = "";
				
				System.out.println("Enter customer account username (alphanumeric characters and _ permitted):");
				
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_]*"));
				System.out.println("User account name entered: " + option);
				user = option;
				
				System.out.println("Enter password for " + user + " (alphanumeric characters, _, -, and special characters are permitted): ");
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_!@#$%^&*()-]*"));
				System.out.println("User password entered");
				pass = option;
				
				System.out.println("Enter email for " + user + "(alphanumeric characters and _ are permitted): ");
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_.]*@[A-Za-z0-9_.]*"));
				System.out.println("Customer password entered");
				email = option;
				
				System.out.println("Creating customer account...");
				
				try {
					ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt")); //read existing customer
					ArrayList<CustomerUserAccount> customers = new ArrayList<>();
					try {
						customers = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
						objectInputStream.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					customers.add(new CustomerUserAccount(user, pass, email)); //add new customer
					
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt")); //write out additional customer
					objectOutputStream.writeObject(customers);
					System.out.println("Created account");
					objectOutputStream.flush();
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		////////////////////////////////////////////////////////////////////////
		// EMPLOYEE INTERFACE
		else if (cli.equals("bank")) {
			System.out.println("Employee interface");
			Scanner input = new Scanner(System.in);
			
			////////////////////////////////////////////////////////////////////
			// EMPLOYEE MAIN MENU
			System.out.println("(1) Sign in\n(2) Create account");
			String option = "";
			do {
			option = input.nextLine();
			}
			while (!option.equals("1") && !option.equals("2"));
			System.out.println(option);
			
			if (option.equals("1")) { //try sign in
				////////////////////////////////////////////////////////////////
				// EMPLOYEE SIGN IN
				
				ArrayList<EmployeeUserAccount> employeeAccounts = new ArrayList<>();
				ObjectInputStream objectInputStream = null;
				try {
					objectInputStream = new ObjectInputStream(new FileInputStream("accounts_employee.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					employeeAccounts = (ArrayList<EmployeeUserAccount>)objectInputStream.readObject();
					objectInputStream.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				boolean login = false;
				String user = "", password = "";
				do {
					System.out.println("Enter employee account name:");
					option = input.nextLine();
					for (int i = 0; i < employeeAccounts.size(); i++) {
						if (option.equals(employeeAccounts.get(i).getUsername())) {
							user = option;
							do {
								System.out.println("Enter password:");
								option = input.nextLine();
							}
							while (!option.equals(employeeAccounts.get(i).getPassword()));
							password = option;
							login = true;
						}
					}
					if (user.isEmpty()) {
						System.out.println("Employee does not exist");
						break;
					}
				}
				while (!login);
				
				while (login) {
					////////////////////////////////////////////////////////////
					// EMPLOYEE HOME MENU 
					
					System.out.println("Employee logged in");
					
					System.out.println("Welcome " + user);
					System.out.println("(1) View Customer Accounts\n(2) Logout");
					System.out.println("Select an option:");
					do {
						option = input.nextLine();
					}
					while (!option.equals("1") && !option.equals("2"));
					System.out.println(option);
					
					if (option.equals("1")) { //employee view customer accounts
						////////////////////////////////////////////////////////
						// EMPLOYEE VIEW ALL CUSTOMERS ACCOUNTS MENU
						
						ArrayList<CustomerUserAccount> customerAccounts = new ArrayList<>();
						objectInputStream = null;
						try {
							objectInputStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							customerAccounts = (ArrayList<CustomerUserAccount>)objectInputStream.readObject();
							objectInputStream.close();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						boolean viewingAllCustomersMenu = true;
						do {
							System.out.println("Viewing customer accounts");
							for (int i = 0; i < customerAccounts.size(); i++) {
								System.out.println("(" + (i+1) + ") View " + customerAccounts.get(i).getUsername());
							}
							System.out.println("(Q) Quit");
							
							System.out.println("Select an option:");
							do {
								option = input.nextLine();
							}
							while (!option.toLowerCase().equals("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= customerAccounts.size());
							System.out.println(option);
							
							if (option.toLowerCase().equals("q")) {
								viewingAllCustomersMenu = false;
							}
							else {
								////////////////////////////////////////////////
								// EMPLOYEE VIEW A CUSTOMER MENU
								
								int customerAccountIndex = Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0;
								System.out.println("Viewing " + customerAccounts.get(customerAccountIndex).getUsername());
								
								boolean viewingACustomerMenu = true;
								do {
									System.out.println("(1) View accounts pending approval\n(2) Save changes to customer account\n(3) Discard changes");
									System.out.println("Select an option:");
									do {
										option = input.nextLine();
									}
									while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
									
									if (option.equals("1")) {
										////////////////////////////////////////
										// EMPLOYEE VIEW PENDING ACCOUNTS MENU
										
										System.out.println("Viewing pending accounts");
										
										for (int i = 0; i < customerAccounts.get(customerAccountIndex).getAccounts().size(); i++) {
											if ((customerAccounts.get(customerAccountIndex).getAccounts().get(i).checkCheckingStatus() != null && customerAccounts.get(customerAccountIndex).getAccounts().get(i).checkCheckingStatus() == BankAccount.Status.pending) ||
													(customerAccounts.get(customerAccountIndex).getAccounts().get(i).checkSavingsStatus() != null && customerAccounts.get(customerAccountIndex).getAccounts().get(i).checkSavingsStatus() == BankAccount.Status.pending)) {
												System.out.println("(" + (i+1) + ") pending bank accounts");
											}
											else {
												System.out.println("(" + (i+1) + ") no open or pending bank accounts");
											}
										}
										System.out.println("(Q) Quit");
										
										System.out.println("Select an option:");
										do {
											option = input.nextLine();
										}
										while (!option.toLowerCase().equals("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= customerAccounts.size());
										System.out.println(option);
										
										if (option.toLowerCase().equals("q")) {
											viewingACustomerMenu = false;
										}
										else {
											////////////////////////////////////
											// EMPLOYEE VIEW A PENDING ACCOUNT MENU
											int pendingAccountIndex = Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0;
											
											System.out.println("Viewing pending accounts to approve for account #" + pendingAccountIndex);
											
											boolean approvingAccounts = true;
											do {
												System.out.println("(1) Checking " + (customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkCheckingStatus() != null ? (customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkCheckingStatus() == BankAccount.Status.pending ? "(pending approval)\n" : "(" + customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkCheckingStatus() + ")\n") : "(not open)\n") + 
														"(2) Savings " + (customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkSavingsStatus() != null ? (customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkSavingsStatus() == BankAccount.Status.pending ? "(pending approval)\n" : "(" + customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkSavingsStatus() + ")\n") : "(not open)\n") +
														"(3) Finish approving");
												System.out.println("Select an option:");
												do {
													option = input.nextLine();
												}
												while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
												
												if (option.equals("1")) {
													if (customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkCheckingStatus() != null) {
														System.out.println("Approving checking account of account #" + pendingAccountIndex);
														customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).setCheckingStatus(BankAccount.Status.approved);
														System.out.println("Approved");
													}
													else {
														System.out.println("There is no checking account open for account #" + pendingAccountIndex);
													}
												}
												else if (option.equals("2")) {
													if (customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).checkSavingsStatus() != null) {
														System.out.println("Approving savings account of account #" + pendingAccountIndex);
														customerAccounts.get(customerAccountIndex).getAccounts().get(pendingAccountIndex).setSavingsStatus(BankAccount.Status.approved);
														System.out.println("Approved");
													}
													else {
														System.out.println("There is no savings account open for account #" + pendingAccountIndex);
													}
												}
												else if (option.equals("3")) {
													System.out.println("Closing approval menu...");
													approvingAccounts = false;
												}
											}
											while (approvingAccounts);
										}
										
									}
									else if (option.equals("2")) {
										System.out.println("Saving changes to customer...");
										ObjectOutputStream objectOutputStream;
										try {
											objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
											objectOutputStream.writeObject(customerAccounts);
											objectOutputStream.flush();
											objectOutputStream.close();
										} catch (FileNotFoundException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}
										viewingACustomerMenu = false;
										System.out.println("Saved changes");
									}
									else if (option.equals("3")) {
										System.out.println("Discarding changes...");
										viewingACustomerMenu = false;
										System.out.println("Discarded changes");
									}
								}
								while (viewingACustomerMenu);
							}
						}
						while (viewingAllCustomersMenu);
					}
					else if (option.equals("2")) { //employee logout
						System.out.println("Logging out...");
						login = false;
					}
				}
			}
			else if (option.equals("2")) { //do create account
				////////////////////////////////////////////////////////////////
				// EMPLOYEE USER ACCOUNT CREATION
				
				String user = "", pass = "", email = "";
				
				System.out.println("Enter employee account username (alphanumeric characters and _ permitted):");
				
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_]*"));
				System.out.println("User account entered: " + option);
				user = option;
				
				System.out.println("Enter password for " + user + " (alphanumeric characters, _, -, and special characters are permitted): ");
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_!@#$%^&*()-]*"));
				System.out.println("User password entered");
				pass = option;
				
				System.out.println("Enter email for " + user + "(alphanumeric characters and _ are permitted): ");
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_.]*@[A-Za-z0-9_.]*"));
				System.out.println("Employee password entered");
				email = option;
				
				System.out.println("Creating employee account...");
				
				try {
					ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("accounts_employee.txt")); //read existing employees
					ArrayList<EmployeeUserAccount> employees = new ArrayList<>();
					try {
						employees = (ArrayList<EmployeeUserAccount>)objectInputStream.readObject();
						objectInputStream.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					employees.add(new EmployeeUserAccount(user, pass, email)); //add new employee
					
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("accounts_employee.txt")); //write out additional employee
					objectOutputStream.writeObject(employees);
					System.out.println("Created account");
					objectOutputStream.flush();
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		////////////////////////////////////////////////////////////////////////
		// ADMIN INTERFACE
		else if (cli.equals("admin")) {
			System.out.println("Admin interface");
			Scanner input = new Scanner(System.in);
			
			////////////////////////////////////////////////////////////////////
			// MAIN MENU
			System.out.println("(1) Sign in\n(2) Create account");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = input.nextLine();
			}
			while (!option.equals("1") && !option.equals("2"));
			System.out.println(option);
			
			HashMap<String, String> adminAccounts = new HashMap<>();
			if (option.equals("1")) { //try sign in
				boolean login = false;
				String user = "";
				do {
					System.out.println("Enter admin account name:");
					option = input.nextLine();
					File file = new File("accounts_admin.txt");
					try {
						BufferedReader fileReader = new BufferedReader(new FileReader(file));
						String userPass = "";
						while ((userPass = fileReader.readLine()) != null) {
							String[] up = userPass.split(",");
							adminAccounts.put(up[0], up[1]);
						}
						fileReader.close();
						if (adminAccounts.containsKey(option)) {
							String p = adminAccounts.get(option);
							
							do {
								System.out.println("Enter password:");
								option = input.nextLine();
							}
							while (!option.equals(p));
							user = option;
							login = true;
						}
						else {
							System.out.println("Account does not exist");
							break;
						}
					} catch (FileNotFoundException e) { //filereader
						e.printStackTrace();
					} catch (IOException e) { //bufferedreader
						e.printStackTrace();
					}
				}
				while (!login);
				
				if (login) {
					System.out.println("Admin logged in");
					
					System.out.println("Welcome " + user);
					System.out.println("(1) View Customer Accounts\n(2) View Employee Accounts\n(3) View Admin Accounts\n(4) Logout");
					System.out.println("Select an option:");
					do {
						option = input.nextLine();
					}
					while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4"));
					System.out.println(option);
					
					////////////////////////////////////////////////////////////
					// VIEW/EDIT CUSTOMER ACCOUNTS
					if (option.equals("1")) {
						boolean customers = true;
						do {
							ArrayList<CustomerUserAccount> customerAccounts = new ArrayList<>();
							
							try {
								ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
								try {
									customerAccounts = (ArrayList<CustomerUserAccount>)objectStream.readObject();
									objectStream.close();
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} 
							
							System.out.println("Viewing customers");
							
							for (int i = 0; i < customerAccounts.size(); i++) {
								System.out.println("(" + (i+1) + ") Edit " + customerAccounts.get(i).getUsername());
							}
							System.out.println("(Q) Quit");
							
							System.out.println("Select an option:");
							do {
								option = input.nextLine();
							}
							while (!option.toLowerCase().equals("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= customerAccounts.size());
							System.out.println(option);
							
							if (option.equals("q")) {
								customers = false;
							}
							else {
								int userAccount = Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0;
								System.out.println("Editing " + customerAccounts.get(userAccount).getUsername());
								
								boolean customer = true;
								do {
									System.out.println("(1) Edit Bank Accounts\n(2) Edit password\n(3) Edit email\n(4) Save changes to customer accounts\n(5) Discard changes");
									System.out.println("Select an option:");
									do {
										option = input.nextLine();
									}
									while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5"));
									System.out.println(option);
									
									if (option.equals("1")) {
										System.out.println("Editing bank accounts");
										
										ArrayList<BankAccount> bankAccounts = new ArrayList<>();
										
										try {
											ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream("bank_accounts.txt"));
											try {
												bankAccounts = (ArrayList<BankAccount>)objectStream.readObject();
												objectStream.close();
											} catch (ClassNotFoundException e) {
												e.printStackTrace();
											}
										} catch (FileNotFoundException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}
										
										boolean accounts = true;
										do {
											for (int i = 0; i < bankAccounts.size(); i++) {
												System.out.println("(" + (i+1) + ") Edit bank account#" + (i+1));
											}
											System.out.println("(Q) Quit");
											
											System.out.println("Select an option:");
											do {
												option = input.nextLine();
											}
											while (!option.toLowerCase().equals("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= bankAccounts.size());
											System.out.println(option);
											
											if (option.equals("q")) {
												accounts = false;
											}
											else {
												int bankAccount = Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0;
												System.out.println("Editing account#" + bankAccount);
												boolean account = true;
												do {
													System.out.println("(1) Adjust approval status\n(2) Adjust Balance\n(3) Save changes to bank accounts\n(4) Discard changes");
													System.out.println("Select an option:");
													do {
														option = input.nextLine();
													}
													while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4"));
													System.out.println(option);
													
													if (option.equals("1")) {
														
													}
													else if (option.equals("2")) {
														
													}
													else if (option.equals("3")) {
														try {
															System.out.println("Saving changes...");
															ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream("bank_accounts.txt"));
															objectStream.writeObject(bankAccounts);
															System.out.println("Saved");
															objectStream.close();
															account = false;
														} catch (FileNotFoundException e) {
															e.printStackTrace();
														} catch (IOException e) {
															e.printStackTrace();
														}
													}
													else if (option.equals("4")) {
														System.out.println("Discarding changes...");
														account = false;
													}
												}
												while (account);
											}
										}
										while (accounts);
									}
									else if (option.equals("2")) {
										String pass = "";
										System.out.println("Enter new password for " + option + " (alphanumeric characters, _, -, and special characters are permitted): ");
										do {
											option = input.nextLine();
										}
										while (!option.matches("[A-Za-z0-9_!@#$%^&*()-]*"));
										System.out.println("User password entered");
										pass = option;
										
										customerAccounts.get(userAccount).setPassword(pass);
										
										System.out.println("Password set");
									}
									else if (option.equals("3")) {
										String email = "";
										System.out.println("Enter new email for " + option + " (alphanumeric characters, _, -, and special characters are permitted): ");
										do {
											option = input.nextLine();
										}
										while (!option.matches("[A-Za-z0-9_.]*@[A-Za-z0-9_.]*"));
										System.out.println("User email entered");
										email = option;
										
										customerAccounts.get(userAccount).setEmail(email);
										
										System.out.println("Password set");
									}
									else if (option.equals("4")) {
										try {
											System.out.println("Saving changes...");
											ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
											objectStream.writeObject(customerAccounts);
											System.out.println("Saved");
											objectStream.close();
											customer = false;
										} catch (FileNotFoundException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									else if (option.equals("5")) {
										System.out.println("Discarding changes...");
										customer = false;
									}
								}
								while (customer);
								
								
							}
						}
						while (customers);
					}
					////////////////////////////////////////////////////////////
					// VIEW/EDIT EMPLOYEE ACCOUNTS
					else if (option.equals("2")) {
						boolean employees = true;
						do {
							
						}
						while (employees);
					}
					////////////////////////////////////////////////////////////
					// VIEW/EDIT ADMIN ACCOUNTS
					else if (option.equals("3")) {
						boolean admins = true;
						do {
							
						}
						while (admins);
					}
					////////////////////////////////////////////////////////////
					// LOGOUT
					else if (option.equals("4")) {
						System.out.println("Logging out...");
					}
					
				}
			}
			else if (option.equals("2")) { //do create account
				System.out.println("Enter admin account username (alphanumeric characters and _ permitted):");
				
				String user = "", pass = "";
				
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_]*"));
				System.out.println("User account entered: " + option);
				user = option;
				
				System.out.println("Enter password for " + option + " (alphanumeric characters, _, -, and special characters are permitted): ");
				do {
					option = input.nextLine();
				}
				while (!option.matches("[A-Za-z0-9_!@#$%^&*()-]*"));
				System.out.println("User password entered");
				pass = option;
				
				System.out.println("Creating account...");
				File file = new File("accounts_admin.txt");
				try {
					BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
					fileWriter.write(user + "," + pass);
					fileWriter.newLine();
					System.out.println("Created account");
					fileWriter.close();
				} catch (IOException e) { //filewriter
					e.printStackTrace();
				}
			}
		}
		else if (cli.equals("help")){
			System.out.println("Help printout");
			Main.printHelp();
		}
		else {
			System.out.println("Unknown cli argument");
			Main.printHelp();
		}
		
		System.out.println("bye"); //program end
	}

}

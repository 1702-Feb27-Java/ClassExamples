package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.log4j.Logger;

import com.revature.dao.DAOEmployeeImpl;
import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.service.CustomerService;
import com.revature.service.EmployeeService;

public class Main {
	static final Logger l =  Logger.getRootLogger();
	static Scanner scan = new Scanner(System.in);
	static CustomerService serveCust = new CustomerService();
	static EmployeeService serveEmp = new EmployeeService();
	
	public static void main(String[] args) {

		l.info("in main");
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		mainMenuOption(br);
		DAOEmployeeImpl daoEmp = new DAOEmployeeImpl();
/*		int accountId = 2;
		String fn = "employee2";
		String ln = "lastname2";
		String un = "empBen2";
		String pw = "pass";
		System.out.println(serveEmp.getUnapprovedAccounts());*/
		//System.out.println(accountId);
		//serve.getAccounts(accountId);
	}
	
	/**
	 * Abstracted away from main method to allow a scanner to be passed in
	 * @param sc the file reader passed in for testing 
	 */
	public static void mainMenuOption(BufferedReader sc){
		menuOption();
		try{
			int response = Integer.parseInt(sc.readLine()); 
			
			switch (response) {
			case 1:
				if(createCustomerAccountOption(sc)){
					System.out.println("Customer Account Created.");
					mainMenuOption(sc);
				}
				else{
					System.out.println("Username already in use.");
					mainMenuOption(sc);
				}
				break;
			case 2:
				int customerLoginResult = 0;
				
				while(customerLoginResult == 0 || customerLoginResult == -1)
					customerLoginResult = customerLoginOption(sc);
				customerLoggedInMenu(customerLoginResult, sc);
				break;
			case 3:
				//presents options for creating account
				if(createEmployeeAccountOption(sc)){
					System.out.println("Employee Account Created.");
					mainMenuOption(sc);
				}
				else{
					System.out.println("Username already in use.");
					mainMenuOption(sc);
				}
				break;
			case 4:
				int employeeLoginResult = 0;
				
				while(employeeLoginResult == 0 || employeeLoginResult == 1)
					employeeLoginResult = employeeLoginOption(sc);
				employeeLoggedInMenu(employeeLoginResult, sc);
				break;
			case 5:
				boolean adminLoginResult = false;
				
				while(!adminLoginResult){
					adminLoginResult = adminLoginOption(sc);
				}
				viewCustomerAccounts(sc);
				break;
			default:
				System.out.println("You didn't enter a correct number option");
				main(null);//give menu options again
				break;
			}
		}
		catch(Exception e){
			l.error(e);
			System.out.println("General Exception");
		}
	}
	
	/**
	 * Presents main menu option
	 */
	public static void menuOption(){
		//Menu for banking program
		System.out.println("\nEnter a number option");
		System.out.println("----------------------");
		System.out.println("1: Customer Sign up");
		System.out.println("2: Customer Login");
		System.out.println("3: Employee Sign up");
		System.out.println("4: Employee Login");
		System.out.println("5: Admin Login");
	}
	
	/**
	 * Tries to create a customer account, returns the result
	 * 
	 * @param sc input scanner to read user input
	 * @return String the result of signing up for an account
	 */
	public static boolean createCustomerAccountOption(BufferedReader sc){
		String firstname = "";
		String lastname = "";
		String username = "";
		String password = "";
		try
		{
			System.out.println("Customer Sign Up Page");
			System.out.println("-----------------------");
			System.out.println("Enter first name:");
			firstname = sc.readLine();
			System.out.println("Enter last name:");
			lastname = sc.readLine();
			System.out.println("Enter username:");
			username = sc.readLine();
			System.out.println("Enter password:");	
			password = sc.readLine();
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		catch(Exception e){
			l.error(e);
			System.out.println(e);
		}
		boolean result = serveCust.addCustomer(firstname, lastname, username, password);
		return result;
	}
	
	/**
	 * Tries to login with username and password input
	 * 
	 * @param sc input scanner
	 * @return integer value of customer id
	 */
	public static int customerLoginOption(BufferedReader sc){
		int customerLoggedInId = 0;
		try{
			String username, password;
			System.out.println("Enter username: ");
			username = sc.readLine();
			System.out.println("Enter password: ");
			password = sc.readLine();
			int result = serveCust.loginCustomer(username, password);
			if(result == 0){
				customerLoggedInId = result;
				System.out.println("Username or password incorrect.");
			}
			else if(result == -1){
				customerLoggedInId = result;
				System.out.println("Username or password incorrect");
			}
			else{
				customerLoggedInId = result;
			}
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		catch(Exception e){
			l.error(e);
			System.out.println(e);
		}
		l.info("Customer Logged in");
		return customerLoggedInId;
		
	}
	
	/**
	 * Presents menu for logged in customer
	 */
	public static void customerLoggedInMenuOption(){
		//Menu for logged in customer
		System.out.println("\nWelcome to your account");
		System.out.println("----------------------");
		System.out.println("1: Sign up for Savings Acccount");
		System.out.println("2: Sign up for Checking Account");
		System.out.println("3: View Accounts");
		System.out.println("4: Exit to main menu.");
	}
	
	/**
	 * Deals with customer logged in menu input
	 * 
	 * @param customerId the foreign key for the account
	 * @param sc input scanner
	 */
	public static void customerLoggedInMenu(int customerId, BufferedReader sc){
		customerLoggedInMenuOption();
		try{
			int response = Integer.parseInt(sc.readLine());
			
			switch(response){
			case 1://create savings
				if(serveCust.applyForAccount(customerId, 1, 1)){
					System.out.println("Applied for Savings Account.");
					customerLoggedInMenu(customerId, sc);
					break;
				}
				else{
					System.out.println("Applying for savings account failed.");
					customerLoggedInMenu(customerId, sc);
					break;
				}
			case 2://create checking
				if(serveCust.applyForAccount(customerId, 1, 2)){
					System.out.println("Applied for Checking Account.");
					customerLoggedInMenu(customerId, sc);
					break;
				}
				else{
					System.out.println("Applying for checking account failed.");
					customerLoggedInMenu(customerId, sc);
					break;
				}
			case 3://view accounts
				ArrayList<Account> accounts = serveCust.getAccounts(customerId);
				System.out.println("Enter account number that you would like to enter!");
				System.out.println("-------------------------------------------------");
				String accountType;
				if(accounts.size() == 0){
					System.out.println("No Active Accounts");
				}
				for(Account act : accounts){
					accountType = serveCust.getAccountType(act.getTypeId());
					System.out.println("Account #" + act.getAccountId() + "(" + accountType +"): " + act.getBalance());
				}			
				System.out.println("-------------------------------------------------");
				System.out.println("Press 0 to go back.");
				int accountSelect = Integer.parseInt(sc.readLine());
				if(accountSelect == 0){
					customerLoggedInMenu(customerId, sc);
					break;
				}
				
				boolean actFound = false;
				System.out.println("What would you like to do with your account?");
				System.out.println("-------------------------------------------------");
				for(Account act : accounts){
					accountType = serveCust.getAccountType(act.getTypeId());
					if(act.getAccountId() == accountSelect){
						System.out.println("Account #" + act.getAccountId() + "(" + accountType +"): " + act.getBalance() +"\n");
						actFound = true;
					}
				}
				if(actFound){
					System.out.println("Press 1 to Deposit");
					System.out.println("Press 2 to Withdraw");
					int action = Integer.parseInt(sc.readLine());
					if(action == 1){
						System.out.println("How much would you like to deposit?");
						double dep = Double.parseDouble(sc.readLine());
						System.out.println("CI" + customerId);
						double newBal = serveCust.depositMoney(accountSelect, dep);
						System.out.println("Deposited $" + dep);
						System.out.println("New Balance: $" + newBal);
						customerLoggedInMenu(customerId, sc);
						break;
					}
					else if(action == 2){
						System.out.println("How much would you like to withdraw?");
						double with = Double.parseDouble(sc.readLine());
						double newBal = serveCust.withdrawMoney(accountSelect, with);
						System.out.println("Withdrew $" + with);
						System.out.println("New Balance: $" + newBal);
						customerLoggedInMenu(customerId, sc);
						break;
					}
					else{
						System.out.println("Invalid Option");
						customerLoggedInMenu(customerId, sc);
						break;
					}
				}
				else{
					System.out.println("No Account with that account number found");
					customerLoggedInMenu(customerId, sc);
					break;
				}
			case 4:
				mainMenuOption(sc);
				break;
			default:
				System.out.println("Not a valid option");
				customerLoggedInMenu(customerId, sc);
				break;
			}
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		catch(Exception e){
			l.error(e);
			System.out.println(e);
		}		
	}
	
	/**
	 * Deposit money into account type specified in loggedinmenu
	 * @param customerId foreign key for customer account
	 * @param sc input scanner 
	 * @param accountType savings or checking picked by customer
	 */
	public static void depositMoney(int customerId, BufferedReader sc, String accountType){
		Customer depositMoney = new Customer();
		int amount = 0;
		
		try{
			System.out.println("How much money do you want to deposit?");
			amount = Integer.parseInt(sc.readLine());
		}
		catch(IOException e){
			l.error(e);
			System.out.println("IO Exception");
		}
		
		int newBalance = depositMoney.depositMoney(customerId, accountType, amount);
		l.info("deposited money");
		System.out.println("New Balance: $" + newBalance);
	}
	
	/**
	 * Withdraw money from account type specified in logginmenu
	 * @param customerId foreign key for customer account
	 * @param sc input scanner
	 * @param accountType savings or checking picked by customer
	 */
	public static void withdrawMoney(int customerId, BufferedReader sc, String accountType){
		Customer withdrawMoney = new Customer();
		int amount = 0;
		
		try{
			System.out.println("How much money do you want to withdraw?");
			amount = Integer.parseInt(sc.readLine());
		}
		catch(IOException e){
			l.error(e);
			System.out.println("IO Exception");
		}
		
		int newBalance = withdrawMoney.withdrawMoney(customerId, accountType, amount);
		l.info("Withdrew money");
		System.out.println("New Balance: $" + newBalance);
	}

	/**
	 * attempts to create employee account
	 * @param sc input scanner
	 * @return String result of what happened
	 */
	public static boolean createEmployeeAccountOption(BufferedReader sc){
		String firstname = "";
		String lastname = "";
		String username = "";
		String password = "";
		try
		{
			System.out.println("Employee Sign Up Page");
			System.out.println("-----------------------");
			System.out.println("Enter first name:");
			firstname = sc.readLine();
			System.out.println("Enter last name:");
			lastname = sc.readLine();
			System.out.println("Enter username:");
			username = sc.readLine();
			System.out.println("Enter password:");	
			password = sc.readLine();
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		catch(Exception e){
			l.error(e);
			System.out.println(e);
		}
		boolean result = serveEmp.addEmployee(firstname, lastname, username, password);
		return result;
	}

	/**
	 * Tries to login with username and password input
	 * 
	 * @param sc input scanner
	 * @return integer of employee id
	 */
	public static int employeeLoginOption(BufferedReader sc){
		int employeeLoggedInId = 0;
		try{
			String username, password;
			System.out.println("Enter username: ");
			username = sc.readLine();
			System.out.println("Enter password: ");
			password = sc.readLine();
			int result = serveEmp.loginEmployee(username, password);
			if(result == 0){
				employeeLoggedInId = result;
				System.out.println("Username or password incorrect.");
			}
			else if(result == 1){
				employeeLoggedInId = result;
				System.out.println("Username or password incorrect");
			}
			else{
				employeeLoggedInId = result;
			}
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		catch(Exception e){
			l.error(e);
			System.out.println(e);
		}
		l.info("Employee Logged in");
		return employeeLoggedInId;
		
	}
	
	/**
	 * Presents menu for logged in employee
	 */
	public static void employeeLoggedInMenuOption(){
		//Menu for logged in employee
		System.out.println("\nEmployee Menu: Enter Selection");
		System.out.println("------------------------------");
		System.out.println("1: View all of your customers.");
		System.out.println("2: See your customer's account applications to approve/decline.");
		System.out.println("3: Open calculator");
		System.out.println("4: Exit to main menu");
	}

	/**
	 * Handle logged in employee selections
	 * @param employeeId the unique employee id
	 * @param sc the input scanner
	 */
	public static void employeeLoggedInMenu(int employeeId, BufferedReader sc){
		employeeLoggedInMenuOption();
		
		try{
			int response = Integer.parseInt(sc.readLine());
			
			switch(response){
			case 1://view customer accounts
				ArrayList<Account> accounts = new ArrayList<Account>();
				accounts = serveEmp.getAccounts(employeeId);
				boolean actFound = false;
				String accountType;
				String statusType;
				System.out.println("");
				for(Account act : accounts){
					accountType = serveCust.getAccountType(act.getTypeId());
					statusType = serveCust.getStatusType(act.getStatusId());
					System.out.println("Account #" + act.getAccountId() + "(" + accountType +"): " 
							+ act.getBalance() + " (" + statusType + ")\n");
					actFound = true;
				}
				if(!actFound){
					System.out.println("\nAccounts not found.");
				}
				System.out.println("-------------------------------");
				employeeLoggedInMenu(employeeId, sc);
				break;
			case 2://see customer's account applications to approve/decline
				ArrayList<Account> accounts2 = serveEmp.getUnapprovedAccounts();
				System.out.println("Enter account number that you would like to approve!");
				System.out.println("-------------------------------------------------");
				String accountType2;
				String statusType2;
				
				for(Account act : accounts2){
					accountType = serveCust.getAccountType(act.getTypeId());
					statusType = serveCust.getStatusType(act.getStatusId());
					System.out.println("Account #" + act.getAccountId() + "(" + accountType +"): " 
								+ act.getBalance() + " (" + statusType + ")");
				}				
				System.out.println("-------------------------------------------------");
				System.out.println("Press 0 to go back.");
				int accountSelect = Integer.parseInt(sc.readLine());
				if(accountSelect == 0){
					employeeLoggedInMenu(employeeId, sc);
					break;
				}
				
				boolean actFound2 = false;
				System.out.println("Aprove or Decline?");
				System.out.println("-------------------------------------------------");
				for(Account act : accounts2){
					accountType2 = serveCust.getAccountType(act.getTypeId());
					statusType2 = serveCust.getStatusType(act.getStatusId());
					if(act.getAccountId() == accountSelect){
						System.out.println("Account #" + act.getAccountId() + "(" + accountType2 +"): " 
								+ act.getBalance() + " (" + statusType2 + ")\n");
						actFound2 = true;
					}
				}
				if(actFound2){
					System.out.println("Press 2 to Approve");
					System.out.println("Press 3 to Decline");
					int action = Integer.parseInt(sc.readLine());
					if(action == 2){
						serveEmp.editAccountStatus(accountSelect, action);
						serveEmp.setResolverId(employeeId, accountSelect);
						System.out.println("Account successfully approved.");
						employeeLoggedInMenu(employeeId, sc);
						break;
					}
					else if(action == 3){
						serveEmp.editAccountStatus(accountSelect, action);
						serveEmp.setResolverId(employeeId, accountSelect);
						System.out.println("Account denied.");
						employeeLoggedInMenu(employeeId, sc);
						break;
					}
					else{
						System.out.println("Invalid Option");
						employeeLoggedInMenu(employeeId, sc);
						break;
					}
				}
				else{
					System.out.println("No Account with that account number found");
					employeeLoggedInMenu(employeeId, sc);
					break;
				}
			case 3:
				Employee calculate = new Employee();
				calculate.calculator(sc);
				employeeLoggedInMenu(employeeId, sc);
				break;
			case 4: 
				mainMenuOption(sc);
				break;
			default:
				System.out.println("Not a valid option");
				employeeLoggedInMenu(employeeId, sc);
				break;
			}
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		catch(Exception e){
			l.error(e);
			System.out.println(e);
		}
	}
	
	/**
	 * Calls get account applications and prints out the results
	 * @param sc input reader
	 */
	public static void approveAccountApplications(BufferedReader sc){
		Employee employee = new Employee();
		String[] application = employee.getAccountApplications(sc);
		
		if(application[0].equals("")){
			System.out.println("No accounts to approve.");
		}
		else{
			if(application[2].equals("true")){
				//prints out results of approved
				System.out.println(application[0] + " account " + application[1] + " approved!");
				l.info("customer approved");
			}
			else if(application[2].equals("false")){
				//prints out results of declined
				System.out.println(application[0] + " account " + application[1] + " declined!");
				l.info("customer denied");
			}
		}
	}
	
	/**
	 * Tries to log in as admin, calling admin class, then returning true or false
	 * @param sc input scanner
	 * @return the boolean whether login was successful
	 */
	public static boolean adminLoginOption(BufferedReader sc){
		boolean adminLoginResult = false;
		try{
			System.out.println("Enter username: ");
			String un = sc.readLine();
			System.out.println("Enter password: ");
			String pw = sc.readLine();
			
			adminLoginResult = serveEmp.loginAdmin(un, pw);
			
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		catch(Exception e){
			l.error(e);
			System.out.println(e);
		}
		l.info("admin logged in");
		return adminLoginResult;
	}
	
	/**
	 * Retrieve customerid from data teid to username input
	 * @param sc input scanner
	 */
	public static void viewCustomerAccounts(BufferedReader sc){
		System.out.println("Enter username of customer to login to.");
		String customerUsername = "";
		try {
			customerUsername = sc.readLine();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		int customerId = serveCust.loginCustomer(customerUsername);
		if(customerId != 0){
			customerLoggedInMenu(customerId, sc);
		}
		else{
			System.out.println("Customer username not found");
			viewCustomerAccounts(sc);
		}
	}
 	
}

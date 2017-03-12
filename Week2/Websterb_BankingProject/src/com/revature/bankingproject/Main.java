package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.log4j.Logger;

import com.revature.dao.DAOCustomerImpl;
import com.revature.pojo.Account;
import com.revature.service.CustomerService;

public class Main {
	static final Logger l =  Logger.getRootLogger();
	static Scanner scan = new Scanner(System.in);
	static CustomerService serve = new CustomerService();
	
	public static void main(String[] args) {

		l.info("in main");
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		mainMenuOption(br);
		DAOCustomerImpl daoCust = new DAOCustomerImpl();
		int accountId = 2;
/*		daoCust.getBalance(10);
		System.out.println(daoCust.setBalance(10, 10.15));
		daoCust.getBalance(10);*/
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
				
				while(customerLoginResult == 0 || customerLoginResult == 1)
					customerLoginResult = customerLoginOption(sc);
				customerLoggedInMenu(customerLoginResult, sc);
				break;
			case 3:
				//presents options for creating account
				String createEmployeeAccountResult = createEmployeeAccountOption(sc);
				
				System.out.println(createEmployeeAccountResult);
				if(!(createEmployeeAccountResult.equals("Account created successfully!"))){
					main(null);
				}
				else if((createEmployeeAccountResult.equals("Account created successfully!"))){
					int employeeLoginResult = 0;
					
					while(employeeLoginResult == 0){
						employeeLoginResult = employeeLoginOption(sc);
					}
					employeeLoggedInMenu(employeeLoginResult, sc);
				}
				break;
			case 4:
				int employeeLoginResult = 0;
				
				while(employeeLoginResult == 0)
					employeeLoginResult = employeeLoginOption(sc);
				employeeLoggedInMenu(employeeLoginResult, sc);
				break;
			case 5:
				System.out.println("admin login");
				System.out.println("-------------");
				boolean adminLogin = adminLoginOption(sc);
				while(!adminLogin){
					System.out.println("Wrong login info, Mr. Admin");
					adminLogin = adminLoginOption(sc);
				}
				viewCustomerAccounts(sc);
				break;
			case 6: 
				System.out.println("Log in to previously created Customer to add joint account");
					int customerLoginResult2 = 0;
					
					while(customerLoginResult2 == 0)
						customerLoginResult2 = customerLoginOption(sc);
					customerLoggedInMenu(customerLoginResult2, sc);
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
		System.out.println("Enter a number option");
		System.out.println("----------------------");
		System.out.println("1: Customer Sign up");
		System.out.println("2: Customer Login");
		System.out.println("3: Employee Sign up");
		System.out.println("4: Employee Login");
		System.out.println("5: Admin Login");
		System.out.println("6: Create joint account with existing user");
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
		boolean result = serve.addCustomer(firstname, lastname, username, password);
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
			int result = serve.loginCustomer(username, password);
			if(result == 0){
				customerLoggedInId = result;
				System.out.println("Username or password incorrect.");
			}
			else if(result == 1){
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
		System.out.println("Welcome to your account");
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
				if(serve.applyForAccount(customerId, 1, 1)){
					System.out.println("Applied for Savings Account.");
				}
				else{
					System.out.println("Applying for savings account failed.");
				}
				break;
			case 2://create checking
				if(serve.applyForAccount(customerId, 1, 2)){
					System.out.println("Applied for Checking Account.");
				}
				else{
					System.out.println("Applying for checking account failed.");
				}
				break;
			case 3://view accounts
				ArrayList<Account> accounts = serve.getAccounts(customerId);
				System.out.println("Enter account number that you would like to enter!");
				System.out.println("-------------------------------------------------");
				String accountType;
				
				for(Account act : accounts){
					accountType = serve.getAccountType(act.getTypeId());
					System.out.println("Account #" + act.getAccountId() + "(" + accountType +"): " + act.getBalance());
				}
				int accountSelect = Integer.parseInt(sc.readLine());
				
				boolean actFound = false;
				System.out.println("What would you like to do with your account?");
				System.out.println("-------------------------------------------------");
				for(Account act : accounts){
					accountType = serve.getAccountType(act.getTypeId());
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
						double newBal = serve.depositMoney(accountSelect, dep);
						System.out.println("Deposited $" + dep);
						System.out.println("New Balance: $" + newBal);
						customerLoggedInMenu(customerId, sc);
					}
					else if(action == 2){
						System.out.println("How much would you like to withdraw?");
						double with = Double.parseDouble(sc.readLine());
						double newBal = serve.withdrawMoney(accountSelect, with);
						System.out.println("Withdrew $" + with);
						System.out.println("New Balance: $" + newBal);
						customerLoggedInMenu(customerId, sc);
					}
					else{
						System.out.println("Invalid Option");
						customerLoggedInMenu(customerId, sc);
					}
				}
				else{
					System.out.println("No Account with that account number found");
					customerLoggedInMenu(customerId, sc);
				}
				break;
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
	public static String createEmployeeAccountOption(BufferedReader sc){
		String username = "";
		String password = "";
		try
		{
			System.out.println("Employee Sign Up Page");
			System.out.println("-----------------------");
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
		
		//call sign up method from Customer class
		Employee createEmployee = new Employee();
		String createEmployeeResult = createEmployee.createEmployeeAccount(username, password);
		l.info("created employee account");
		return createEmployeeResult;
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
			
			//tries to login with username and password input
			Employee loginEmployee = new Employee();
			String employeeLoginResult = loginEmployee.login(username, password);
			
			//check if login result is password incorrect
			if((employeeLoginResult.equals("Password incorrect."))){
				System.out.println(employeeLoginResult);
				employeeLoggedInId = 0;
			}
			//check if login result is username not found
			else if((employeeLoginResult.equals("Username not found."))){
				System.out.println(employeeLoginResult);
				employeeLoggedInId = 0;
			}
			//check if login result are exceptions
			else if((employeeLoginResult.equals("General Exception"))){
				System.out.println("General Exception");
				employeeLoggedInId = 0;
			}
			else if((employeeLoginResult.equals("IO Exception"))){
				System.out.println("IO Exception");
				employeeLoggedInId = 0;
			}
			else{
				employeeLoggedInId = Integer.parseInt(employeeLoginResult);//save customerId
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
		return employeeLoggedInId;
		
	}
	
	/**
	 * Presents menu for logged in employee
	 */
	public static void employeeLoggedInMenuOption(){
		//Menu for logged in employee
		System.out.println("Employee Menu: Enter Selection");
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
				Employee employee = new Employee();
				//get array list of customer's accounts tied to employee
				ArrayList<String[]> customerAccounts = employee.viewCustomerAccounts(employeeId);
				
				//display list in visual way
				for(int i = 0; i < customerAccounts.size(); i++){
					String[] customerStringArray = customerAccounts.get(i);
					if((!(customerStringArray[0].equals("savings"))) && (!(customerStringArray[0].equals("checking"))))
							System.out.println("-------------------------------");
					System.out.println(customerStringArray[0] + " " + customerStringArray[1]);
				}
				System.out.println("-------------------------------");
				employeeLoggedInMenu(employeeId, sc);
				break;
			case 2://see customer's account applications to approve/decline
				approveAccountApplications(sc);
				employeeLoggedInMenu(employeeId, sc);
				break;
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
			String username, password;
			System.out.println("Enter username: ");
			username = sc.readLine();
			System.out.println("Enter password: ");
			password = sc.readLine();
			
			//tries to login with username and password input
			Admin adminLogin = new Admin();
			adminLoginResult = adminLogin.login(username, password);
			
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
		String customerUsername = "";
		System.out.println("What is the username of the customer you want to view?");
		try{
			customerUsername = sc.readLine();
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		Admin customerId = new Admin();
		String customerIdRetrieved = customerId.getCustomerId(customerUsername);
		//only move on if customer with username was found
		if(customerIdRetrieved.equals("")){
			System.out.println("No customer with that username");
		}
		else{
			//act as a customer logged in, but you are an admin in that account
			customerLoggedInMenu(Integer.parseInt(customerIdRetrieved), sc);
		}
	}
 	
}

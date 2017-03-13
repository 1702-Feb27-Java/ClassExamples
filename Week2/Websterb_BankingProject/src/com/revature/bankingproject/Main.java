package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.revature.dao.DAOEmployeeImpl;
import com.revature.pojo.Account;
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
			case 1://create customer account
				if(createCustomerAccountOption(sc)){
					System.out.println("Customer Account Created.");
					mainMenuOption(sc);
				}
				else{
					System.out.println("Username already in use.");
					mainMenuOption(sc);
				}
				break;
			case 2://login to customer account
				int customerLoginResult = 0;
				
				while(customerLoginResult == 0 || customerLoginResult == -1)
					customerLoginResult = customerLoginOption(sc);
				customerLoggedInMenu(customerLoginResult, sc);
				break;
			case 3://create employee account
				if(createEmployeeAccountOption(sc)){
					System.out.println("Employee Account Created.");
					mainMenuOption(sc);
				}
				else{
					System.out.println("Username already in use.");
					mainMenuOption(sc);
				}
				break;
			case 4://login to employee account
				int employeeLoginResult = 0;
				
				while(employeeLoginResult == 0 || employeeLoginResult == 1)
					employeeLoginResult = employeeLoginOption(sc);
				employeeLoggedInMenu(employeeLoginResult, sc);
				break;
			case 5://login to admin 
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
		{// get customer account info
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
			case 1://apply for savings
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
			case 2://apply for checking
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
				//get all accounts based on customerid
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
				//go into individual account
				boolean actFound = false;
				System.out.println("What would you like to do with your account?");
				System.out.println("-------------------------------------------------");
				for(Account act : accounts){
					accountType = serveCust.getAccountType(act.getTypeId());
					if(act.getAccountId() == accountSelect){
						System.out.println("Account #" + act.getAccountId() + "(" + accountType +"): " + act.getBalance() +"\n");
						actFound = true;
					}
				}//if account you entered is there, do something
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
				
				for(Account act2 : accounts2){
					accountType2 = serveCust.getAccountType(act2.getTypeId());
					statusType2 = serveCust.getStatusType(act2.getStatusId());
					System.out.println("Account #" + act2.getAccountId() + "(" + accountType2 +"): " 
								+ act2.getBalance() + " (" + statusType2 + ")");
				}				
				System.out.println("-------------------------------------------------");
				System.out.println("Press 0 to go back.");
				int accountSelect2 = Integer.parseInt(sc.readLine());
				if(accountSelect2 == 0){
					employeeLoggedInMenu(employeeId, sc);
					break;
				}
				
				boolean actFound2 = false;
				System.out.println("Aprove or Decline?");
				System.out.println("-------------------------------------------------");
				for(Account act2 : accounts2){
					accountType2 = serveCust.getAccountType(act2.getTypeId());
					statusType2 = serveCust.getStatusType(act2.getStatusId());
					if(act2.getAccountId() == accountSelect2){
						System.out.println("Account #" + act2.getAccountId() + "(" + accountType2 +"): " 
								+ act2.getBalance() + " (" + statusType2 + ")\n");
						actFound2 = true;
					}
				}
				if(actFound2){
					System.out.println("Press 2 to Approve");
					System.out.println("Press 3 to Decline");
					int action = Integer.parseInt(sc.readLine());
					if(action == 2){
						serveEmp.editAccountStatus(accountSelect2, action);
						serveEmp.setResolverId(employeeId, accountSelect2);
						System.out.println("Account successfully approved.");
						employeeLoggedInMenu(employeeId, sc);
						break;
					}
					else if(action == 3){
						serveEmp.editAccountStatus(accountSelect2, action);
						serveEmp.setResolverId(employeeId, accountSelect2);
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
				calculator();
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
	 * Retrieve and login as customer from data tied to username input
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
 	
	public static void calculator(){
			System.out.println("   Tiny Calculator " );
			System.out.println("  -----------------");
			System.out.println("|   7   8   9   /  |");
			System.out.println("|   4   5   6   x  |");
			System.out.println("|   1   2   3   -  |");
			System.out.println("|   0   .   +   =  |");
			System.out.println("  -----------------\n");
			System.out.println("Type your operations (Parentheses are okay)");
			
			boolean calculateMore = true;
			while(calculateMore){
				
				String calculateString = "5 * 50 3 / 3 1*10";
				findMultiplications( calculateString );
				
				calculateMore = false;
			}
	}
	
    public static void findMultiplications( String calculateString ) {

        Pattern multiplicationPattern = Pattern.compile( "\\d+\\s*\\|/|*|-|+|\\s*\\d+" );

        Matcher multiplicationMather = multiplicationPattern.matcher( calculateString );

        while ( multiplicationMather.find() ) {
        	if(multiplicationMather.group().length() != 0){
        		multiplicationMather.group().trim();
                int int1 = multiplicationMather.start();
                int int2 = multiplicationMather.end();
                
                System.out.println(int1 +" " + int2);
        	}


        }
    }	
		
    static int add(int int1, int int2) {
        return int1 + int2;
    }
    static int subtract(int int1, int int2) {
        return int1 - int2;
    }
    static int multiply(int int1, int int2) {
        return int1 * int2;
    }
    static int divide(int int1, int int2) {
        return int1 / int2;
    }
	    
	
	
}

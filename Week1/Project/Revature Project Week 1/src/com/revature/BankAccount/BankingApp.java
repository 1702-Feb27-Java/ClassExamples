package com.revature.BankAccount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class BankingApp extends Admin { // inherits instances of the
									// Admin,Employees,Customers
	// the file name
	static String customerList = "C:/Users/MarkisG/Documents/Eclipse/Assignment227/Revature Project Week 1/src/com/revature/BankAccount/CustomerBankingInfo.txt";
	static String employeeList = "C:/Users/MarkisG/Documents/Eclipse/Assignment227/Revature Project Week 1/src/com/revature/BankAccount/EmployeeBankingInfo.txt";
	static String adminList = "C:/Users/MarkisG/Documents/Eclipse/Assignment227/Revature Project Week 1/src/com/revature/BankAccount/AdminBankingInfo.txt";
	static Scanner theUserName, theUserPassword, userCommand, firstName, lastName, userName, passWord, accountChoice,
			userType, accountInput, depositInput, withdrawInput;
	static String u_username, u_password;
	static int u_command;
	static ArrayList<Customers> eachCustomer = new ArrayList<Customers>();
	static ArrayList<Employees> eachEmployee = new ArrayList<Employees>();
	static ArrayList<Admin> eachAdmin = new ArrayList<Admin>();

	public static void main(String[] args) {

		// PROGRAM START
		try {
			readInBankingInformation(customerList, employeeList, adminList);
		} catch (Exception e) {
			System.out.println("Unable to read in files.");
			e.printStackTrace();
		}
		welcomeUser();

	}// main

	// ****************METHODS********************
	static void welcomeUser() {
		System.out.println("**********************************************");
		System.out.println("************WELCOME TO BANKING APP************");
		System.out.println("1 - Sign Up");
		System.out.println("2 - Login");

		try {
			userCommand = new Scanner(System.in);
			u_command = userCommand.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (u_command == 1) {
			signUpUser();
		} else if (u_command == 2) {
			loginUser();
		}
	}// welcomeUser

	static void loginUser() {
		System.out.println("**********************************************");
		System.out.println("*******************LOGIN**********************");
		System.out.println("Enter in your username:");
		try {
			theUserName = new Scanner(System.in);
			u_username = theUserName.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Enter in your password:");
		try {
			theUserPassword = new Scanner(System.in);
			u_password = theUserPassword.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*************************************");
		}
		logInAs();

	}// loginUser
	
	static void logInAs(){
		System.out.println("**********************************************");
		System.out.println("*****************LOG IN AS:*******************");
		System.out.println("1 - Customer");
		System.out.println("2 - Employee");
		System.out.println("3 - Admin");
		int input = 0;
		userType = new Scanner(System.in);
		try {
			input = userType.nextInt();
		} catch (Exception e) {
			System.out.println("User Login Type Input Exception");
			e.printStackTrace();
		}

		switch (input) {
		case 1:
			checkUserLoginCredentialsForCustomers(eachCustomer);
			break;
		case 2:
			checkUserLoginCredentialsForEmployees(eachEmployee);
			break;
		case 3:
			checkUserLoginCredentialsForAdmins(eachAdmin);
			break;
		default:
			System.out.println("User Type Input Error!");
		}
	}

	static void signUpUser() {
		System.out.println("**********************************************");
		System.out.println("*******************SIGN UP********************");
		/* get the first name of new user */
		System.out.println("Enter your first name: ");
		firstName = new Scanner(System.in);
		String f_name = firstName.nextLine();

		/* get the last name of new user */
		System.out.println("Enter your last name: ");
		lastName = new Scanner(System.in);
		String l_name = lastName.nextLine();

		/* get the username of new user */
		System.out.println("Enter a username: ");
		userName = new Scanner(System.in);
		String u_name = userName.nextLine();

		/* get the password of new user */
		System.out.println("Enter a password: ");
		passWord = new Scanner(System.in);
		String p_word = passWord.nextLine();

		bankAccountSetUp();
		writeCredentialsToCustomerTextFile(customerList, f_name, l_name, u_name, p_word);

		System.out.println("Thanks for signing up!");
		System.out.println("Please log back in.");

		// run user login
		loginUser();

	}// signUpUser

	static boolean checkUserLoginCredentialsForEmployees(ArrayList<Employees> e) {
		for (int i = 0; i < e.size(); i++) {
			String employeeUserName = e.get(i).username;
			String employeeUserPassword = e.get(i).password;
			if ((employeeUserName.compareTo(u_username) == 0) & employeeUserPassword.compareTo(u_password) == 0) {
				// System.out.println("Username and Password Match in Text
				// File.");
				String employeeFirstName = e.get(i).firstname;
				String employeeLastName = e.get(i).lastname;

				/* WELCOME SCREEN */
				employeeWelcomeScreen(employeeFirstName, employeeLastName);
				return true;
			}
		}
		System.out.println("Match Not Found!");
		return false;
	}

	static boolean checkUserLoginCredentialsForAdmins(ArrayList<Admin> a) {
		for (int i = 0; i < a.size(); i++) {
			String adminUserName = a.get(i).username;
			String adminUserPassword = a.get(i).password;
			if ((adminUserName.compareTo(u_username) == 0) & adminUserPassword.compareTo(u_password) == 0) {
				System.out.println("Username and Password Match in Text File.");
				return true;
			}
		}
		System.out.println("Match Not Found!");
		return false;
	}

	static boolean checkUserLoginCredentialsForCustomers(ArrayList<Customers> c) {
		for (int i = 0; i < c.size(); i++) {
			String customerUserName = c.get(i).username;
			String customerUserPassword = c.get(i).password;
			if ((customerUserName.compareTo(u_username) == 0) & customerUserPassword.compareTo(u_password) == 0) {
				// System.out.println("Username and Password Match in Text
				// File.");
				String customerFirstName = c.get(i).firstname;
				String customerLastName = c.get(i).lastname;
				String customerBalance = c.get(i).balance;
				/* WELCOME SCREEN */
				customerWelcomeScreen(customerFirstName, customerLastName, customerBalance);
				return true;
			}
		}
		System.out.println("Match Not Found!");
		return false;
	}

	static void readInBankingInformation(String customers, String employees, String admins) throws Exception {
		/*
		 * Read in the list of files for the customers, employees, and admins
		 */

		File customerList = new File(customers);
		File employeeList = new File(employees);
		File adminList = new File(admins);
		BufferedReader cL = null;
		BufferedReader eL = null;
		BufferedReader aL = null;

		cL = new BufferedReader(new FileReader(customerList));
		eL = new BufferedReader(new FileReader(employeeList));
		aL = new BufferedReader(new FileReader(adminList));
		/* next line in the customer list */
		String customerLine = cL.readLine();
		/* next line in the employee list */
		String employeeLine = eL.readLine();
		/* next line in the admin list */
		String adminLine = aL.readLine();

		/* read through the lines in customer list */
		Customers c;
		String customer[];
		while (customerLine != null) {
			customer = customerLine.split(":");
			c = new Customers(customer[0], customer[1], customer[2], customer[3], customer[4]);
			eachCustomer.add(c);
			customerLine = cL.readLine();
		}
		/*
		 * System.out.println("Customers:"); for(int i=0;
		 * i<eachCustomer.size();i++){
		 * System.out.println(eachCustomer.get(i).id+" "+eachCustomer.get(i).
		 * firstname+" "+eachCustomer.get(i).lastname+" "+eachCustomer.get(i).
		 * username+" "+eachCustomer.get(i).password+" "+eachCustomer.get(i).
		 * balance); }
		 */
		Employees e;
		String employee[];
		/* read through the lines in employee list */
		while (employeeLine != null) {
			employee = employeeLine.split(":");
			e = new Employees(employee[0], employee[1], employee[2], employee[3]);
			eachEmployee.add(e);
			employeeLine = eL.readLine();
		}
		/*
		 * System.out.println("Employees:"); for(int
		 * i=0;i<eachEmployee.size();i++){
		 * System.out.println(eachEmployee.get(i).firstname+" "+eachEmployee.get
		 * (i).lastname+" "+eachEmployee.get(i).username+" "+eachEmployee.get(i)
		 * .password); }
		 */
		Admin a;
		String admin[];
		/* read through the lines in the admin list */
		while (adminLine != null) {
			admin = adminLine.split(":");
			a = new Admin(admin[0], admin[1], admin[2], admin[3]);
			eachAdmin.add(a);
			adminLine = aL.readLine();
		}
		/*
		 * System.out.println("Admins:"); for(int i=0; i<eachAdmin.size();i++){
		 * System.out.println(eachAdmin.get(i).id+" "+eachAdmin.get(i).
		 * firstname+" "+eachAdmin.get(i).lastname+" "+eachAdmin.get(i).
		 * username+" "+eachAdmin.get(i).password); }
		 */
	}// readInBankingInformation

	static void bankAccountSetUp() {
		System.out.println("**********************************************");
		System.out.println("************CREATE BANK ACCOUNT TYPE**********");
		System.out.println("1 - Checking Account");
		System.out.println("2 - Savings Account");
		System.out.println("0 - Exit");
		int u_input = 0;
		try {
			accountChoice = new Scanner(System.in);
			u_input = accountChoice.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (u_input) {
		case 0:
			welcomeUser();
		case 1:
			System.out.println("Checking Account created.");
			System.out.println("Your initial balance is $0 dollars.");
			break;
		case 2:
			System.out.println("Savings Account created.");
			System.out.println("Your initial balance is $0 dollars.");
			break;
		default:
			System.out.println("Account Type Input Error!");
		}
	}

	static void writeCredentialsToCustomerTextFile(String customerFilePath, String fn, String ln, String un,
			String pw) {
		File file = new File(customerFilePath);
		BufferedWriter writeToCustomers = null; // get the contents of the file
		try {
			writeToCustomers = new BufferedWriter(new FileWriter(file, true));
			writeToCustomers.write(fn + ":" + ln + ":" + un + ":" + pw + ":" + "0" + "\n");
			writeToCustomers.close(); // close the file writer stream
		} catch (Exception e) {
			e.getStackTrace();
		}
		System.out.println("******************************************");
		System.out.println("Wrote to file successfully!");
	}

	static void customerWelcomeScreen(String fn, String ln, String b) {
		System.out.println("**********************************************");
		System.out.println("Welcome: " + ln + ", " + fn);
		System.out.println("Your balance is currently $" + b);
		System.out.println("**********************************************");
		double balance = Double.parseDouble(b);
		int input;
		System.out.println("What would you like to do?");
		System.out.println("1 - Deposit Funds");
		System.out.println("2 - Withdraw Funds");
		System.out.println("0 - Exit");
		accountInput = new Scanner(System.in);
		input = accountInput.nextInt();
		switch(input){
		case 0:
			welcomeUser();
			break;
		case 1:
			System.out.println("Deposit Funds");
			depositFunds(balance);
			break;
		case 2:
			System.out.println("Withdraw Funds");
			withdrawFunds(balance);
			break;
		default:
			System.out.println("Invalid Input!");
		}
	}// customerWelcomeScreen
	
	static void depositFunds(double userCurrentBalance){
		int input;
		double total;
		System.out.println("How much will you like to deposit?");
		depositInput = new Scanner(System.in);
		double deposit = depositInput.nextDouble();
		System.out.println("You have deposited "+"$"+deposit);
		System.out.println("Is this correct?");
		System.out.println("1 - YES");
		System.out.println("2 - NO");
		accountInput = new Scanner(System.in);
		input = accountInput.nextInt();
		switch(input){
		case 1:
			total = userCurrentBalance + deposit;
			if(total < 1){
				System.out.println("Minimum balance of at least $1.");
				depositFunds(userCurrentBalance);
			}
			System.out.println("Your new account balance is now "+"$"+total);
			break;
		case 2:
			depositFunds(userCurrentBalance);
			break;
			default:
				System.out.println("Invalid input was given for validating correct deposit amount.");
		}
		writeNewBalanceToFile();
	}//depositFunds
	
	static void withdrawFunds(double userCurrentBalance){
		int input;
		double total;
		System.out.println("How much will you like to withdraw?");
		withdrawInput = new Scanner(System.in);
		double withdraw = withdrawInput.nextDouble();
		System.out.println("You have withdrawed "+"$"+withdraw);
		System.out.println("Is this correct?");
		System.out.println("1 - YES");
		System.out.println("2 - NO");
		accountInput = new Scanner(System.in);
		input = accountInput.nextInt();
		switch(input){
		case 1:
			total = userCurrentBalance - withdraw;
			if(total < 0){
				System.out.println("Cannot withdraw "+withdraw+" amount.");
				System.out.println("Account balance will have negative balance.");
				withdrawFunds(userCurrentBalance);
			}else {
			System.out.println("Your new account balance is now "+"$"+total);
			}
			break;
		case 2:
			withdrawFunds(userCurrentBalance);
			break;
			default:
				System.out.println("Invalid input was given for validating correct withdraw amount.");
		}
		writeNewBalanceToFile();
	}//withdrawFunds
	
	static void writeNewBalanceToFile(){
		
	}//writeNewBalanceToFile
	
	static void employeeWelcomeScreen(String fn, String ln) {
		System.out.println("**********************************************");
		System.out.println("Welcome: " + ln + ", " + fn);
		System.out.println("**********************************************");
		System.out.println("What would you like to do?");
		System.out.println("1 - View Customer Accounts");
		System.out.println("2 - Approve Customer Accounts");
		System.out.println("0 - Exit");
		int input;
		accountInput = new Scanner(System.in);
		input = accountInput.nextInt();
		switch(input){
		case 0:
			welcomeUser();
			break;
		case 1:
			viewCustomerAccounts(eachCustomer);
			break;
		case 2:
			System.out.println("Approve Customer Accounts");
			break;
		default:
			System.out.println("Invalid Input!");
		}
	}// employeeWelcomeScreen

	static void adminWelcomeScreen(String fn, String ln) {
		System.out.println("**********************************************");
		System.out.println("Welcome: " + ln + ", " + fn);
		System.out.println("**********************************************");
		System.out.println("What would you like to do?");
		System.out.println("1 - View Customers Accounts");
		System.out.println("2 - Edit Customers Accounts");
		System.out.println("0 - Exit");
		int input;
		accountInput = new Scanner(System.in);
		input = accountInput.nextInt();
		switch(input){
		case 0:
			welcomeUser();
			break;
		case 1:
			viewCustomerAccounts(eachCustomer);
			break;
		case 2:
			System.out.println("");
			break;
		default:
			System.out.println("Invalid Input!");
		}
	}// adminWelcomeScreen
	
	static void viewCustomerAccounts(ArrayList<Customers> c){
		System.out.println("__________________________________________________________________________");
		System.out.println("First Name"+"\t"+"Last Name"+"\t"+"Username"+"\t"+"Password"+"\t"+"Balance");
		System.out.println("__________________________________________________________________________");
		for(int i=0; i<c.size(); i++){
			System.out.println(c.get(i).firstname+"\t\t"+c.get(i).lastname+"\t\t"+c.get(i).username+"\t\t"+"******"+"\t\t"+"$"+c.get(i).balance);
		}
	}

}// BankingApp
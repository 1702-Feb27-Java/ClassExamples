/**
*********************************************************************************************************
x
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: Customer.java
* PROGRAMMER: KEITH MINNER
* 
* PURPOSE: ALLOW A USER TO SIGN UP FOR A BANKING SERVICE TO INCLUDE A CHECKING AND / OR SAVINGS ACCOUNT
* WITH THE CAPABILITIES TO DEPOSIT, WITHDRAW, VIEW AND EDIT PERSONAL INFORMATION.  AN EMPLOYEE CAN
* VIEW CUSTOMER INFORMATION AND APPROVE ACCOUNTS.
*========================================================================================================
*										PROJECT FILES
*
* Customer.java					Menus.java
* CustomerClassTest.java			MenusClassTest.java
* CustomerFile.java				Person.java
* CustomerFileTest.java			PersonClassTest.java	
* Employee.java					UserScreen.java
* EmployeeClassTest.java			UserScreenTest.java
*========================================================================================================
*										PACKAGE & IMPORT FILES
*********************************************************************************************************
*/
package com.revature.bankingapp;

//import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.revature.dao.DAOImpl;
/**
********************************************************************************************************
*										CLASS CUSTOMER EXTENDS PERSON
*********************************************************************************************************
 */
public class Customer extends Person {
	
	static final Logger l =  Logger.getRootLogger();
	
	//Declaring variables specific to Customer
	private int checkingAccountNumber = 0;
	private int savingsAccountNumber = 0;
	private double checkingBalance = 0.0;
	private double savingsBalance = 0.0;
	static Scanner in = new Scanner(System.in);

/**
*********************************************************************************************************
*	@ DEFAULT CONSTRUCTOR NO ARGUMENTS
*********************************************************************************************************
*/	
	public Customer() {

	}

/**
*********************************************************************************************************
*	@ CONSTRUCTOR WITH ARGUMENTS FOR PERSON
*********************************************************************************************************
*/	
	public Customer(String firstName, String lastName, String username, String password, 
			int checkingAccountNumber, int savingsAccountNumber) {
		super(firstName, lastName, username, password);
	}
	
/**
*********************************************************************************************************
*	@ CONSTRUCTOR WITH ARGUMENTS FOR PERSON & CUSTOMER
*********************************************************************************************************
*/	
	public Customer(String firstName, String lastName, String username, String password, 
			int checkingAccountNumber, int savingsAccountNumber, 
			double checkingBalance, double savingsBalance) {
		super(firstName, lastName, username, password);
	}

/**
*********************************************************************************************************
*	@ SETTERS AND GETTERS
*********************************************************************************************************
*/	
	public int getCheckingAccountNumber() {
		return checkingAccountNumber;
	}

	public void setCheckingAccountNumber(int checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
	}

	public int getSavingsAccountNumber() {
		return savingsAccountNumber;
	}

	public void setSavingsAccountNumber(int savingsAccountNumber) {
		this.savingsAccountNumber = savingsAccountNumber;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(double balance) {
		this.checkingBalance = balance;
	}
	
	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double balance) {
		this.savingsBalance = balance;
	}

/**
*********************************************************************************************************
*	@ OVER WRITING THE TO STRING METHOD
*********************************************************************************************************
*/	
	public String toString() {
		return getUserID() + ":" + getFirstName() + ":" + getLastName() + ":" + getUserName() + ":" 
				+ getPassword() + ":" + getRole() + ":" + getApproved() + ":" + checkingAccountNumber + ":"
				+ savingsAccountNumber + ":" + checkingBalance + ":" + savingsBalance;
	}
/**
*********************************************************************************************************
*	@METHOD TO ADD A NEW PERSON TO THE BANKING APPLICATION
*********************************************************************************************************
*/	
	public static void addNewPerson(int i) {
		try {
			in = new Scanner(System.in); //Creating input stream
			int checkingAccountNumber = 0;
			int savingsAccountNumber = 0;
			//User prompts and getting values
			if (i == 3){
				System.out.println("What kind of account would you like to open?\n1. Checking or 2. Savings");
				String accountType = in.nextLine();
				if (accountType.equals("1")){
					checkingAccountNumber = 1;
					savingsAccountNumber = 0;
				} else {
					checkingAccountNumber = 0;
					savingsAccountNumber = 2;
				}
			}
			System.out.println("\nEnter in your first name: ");
			String firstName = in.nextLine();
			System.out.println("Enter in your last name: ");
			String lastName = in.nextLine();
			System.out.println("Enter in a username: ");
			String username = in.nextLine();
			boolean valid = DAOImpl.verifyInfo(username);
			while(valid){
				System.out.println("That user name is already taken, please try another one: ");
				username = in.nextLine();
				valid = DAOImpl.verifyInfo(username);
			}
			System.out.println("\nUsername Approved:)");
			System.out.println("\nEnter in a password: ");
			String password = in.nextLine();
			
			//Assigning respective values entered into a Customer object
			Customer c = new Customer(firstName, lastName, username, password, checkingAccountNumber, savingsAccountNumber);
			if(i == 1)
				c.setRole(1); //default is 3 for customer, so if it is an admin 10
			if (i == 2)
				c.setRole(2); //employee is a 2		
			DAOImpl.insertData(c);  //Calling method to send data to text file
				
			System.out.println();
			l.trace("NEW SERVICE CREATED FOR " + (c.getFirstName().toUpperCase()
					+ " " + c.getLastName().toUpperCase()));
			System.out.println("\nAccount in pending status for approval, please allow 1 business\n"
					+ "day for processing.  If it has been more than one day without contact,\n"
					+ "please contact a bank representative");
		} catch (Exception e) {
			e.printStackTrace();				
		} finally {
			try{
				in.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
/**
*********************************************************************************************************
* @METHOD TO OPEN A CHECKING OR SAVINGS ACCOUNT.  IF THE CUSTOMER HAS ONE IT WILL DISPLAY IT
*********************************************************************************************************
 */ 
	public static void openAccount(Customer c) {
		
		//User prompt for Checking or Savings account
		System.out.print("\nSelect the type of account you wish to open" + "\n1. Checking or 2. Savings: ");
		int accountChoice = Integer.parseInt(in.nextLine());  //Getting input
		System.out.println();		
		
		//Getting the existing checking/saving account number
		if (accountChoice == 1 && c.getCheckingAccountNumber()==1){
			System.out.println("You already have a checking account\n");
		}
		else if (accountChoice == 2 && c.getSavingsAccountNumber() == 2){
			System.out.println("\nYou already have a savings account\n");
		}
		else {
			DAOImpl.makeAccount(c, accountChoice); //sent to database
		}
		
		l.trace("NEW ACCOUNT CREATED FOR " + (c.getFirstName().toUpperCase() + " "
				+ c.getLastName().toUpperCase() + " ACCOUNT#: " + (accountChoice == 1? 
						c.getCheckingAccountNumber() : c.getSavingsAccountNumber())));
	}
		//Checking to see if customer already has the type of account, if so, they cannot open another one, if they 
		//don't have one a random number of 8 digits is created and entered into the text file
		
		//This code is used for an auto-generated number for checking account or savings account
		/*int number = 0;
		if (accountNum != 0) {
			System.out.println("You already have a " + (accountChoice == 1 ? "checking account" : "savings account"));
			Menus.accountMenu(c);  //Back to account menu
			System.out.println();
		} else {
			Random rand = new Random(System.currentTimeMillis());  //Random seed with current time
			number = rand.nextInt(89999999) + 10000000; //random number created and assigned to number
			String s = Integer.toString(number);
			int arrayPosition = (accountChoice == 1? 7:8);
			boolean valid = CustomerFile.verifyInfo(arrayPosition, s);
			while(valid){
				number = rand.nextInt(89999999) + 10000000; //random number created and assigned to number
				s = Integer.toString(number);
				valid = CustomerFile.verifyInfo(arrayPosition, s);
			}*/
			//CustomerFile.updateRecord(c); //deleting current record from text file

/**
*********************************************************************************************************
* @METHOD TO DEPOSIT MONEY THE RESPECTIVE ACCOUNT SELECTED
*********************************************************************************************************
*/
	public static void accountDeposit(Customer c) {
		System.out.println("\nSelect the type of account you wish to deposit money into:"
				+ "\n1. Checking or 2. Savings: ");
		int accountType = Integer.parseInt(in.nextLine()); //User entry for what kind of account
		double amount = 0.0;
		
		//Assigning what is currently in the account balance
		System.out.println(accountType + " " + c.getCheckingAccountNumber() + " " + c.getSavingsAccountNumber());
		amount += (accountType == 1 ? c.getCheckingBalance() : c.getSavingsBalance());
		if (accountType == 1 && c.getCheckingAccountNumber() != 0 ||accountType == 2 && c.getSavingsAccountNumber() != 0){
			System.out.println("\nEnter in the amount to deposit into the account: ");
			double depositAmount = Double.parseDouble(in.nextLine());
			amount += depositAmount; //summing the balance with the amount deposited
			if (accountType == 1) {
				c.setCheckingBalance(amount);
			} else {
				c.setSavingsBalance(amount);
			}
			DAOImpl.accountTransaction(c, accountType, amount);
		
			System.out.println();
			l.trace("DEPOSIT FOR " + (c.getFirstName().toUpperCase() + " "
				+ c.getLastName().toUpperCase() + " ACCOUNT#: " + (accountType == 1 ? 
				c.getCheckingAccountNumber() : c.getSavingsAccountNumber()) + " IN THE AMOUNT OF: "
				+ depositAmount + " CURRENT BALANCE: " + (accountType == 1 ? 
				c.getCheckingBalance() : c.getSavingsBalance())));
		} 
		else {
			System.out.println(accountType == 1 ? "\nYou do not currently have a checking account" : "\nYou do "
					+ "not currently have a savings account");
		}
	}
		
/**
*********************************************************************************************************
* @METHOD TO WITHDRAW MONEY THE RESPECTIVE ACCOUNT SELECTED
*********************************************************************************************************
 */
	public static void accountWithdraw(Customer c) {
		System.out.println("\nSelect the type of account you wish to withdraw money from:"
				+ "\n1. Checking or 2. Savings: ");
		int accountType = Integer.parseInt(in.nextLine()); //User entry for what kind of account
		double amount = 0.0;
		
		//Assigning what is currently in the account balance
		System.out.println(accountType + " " + c.getCheckingAccountNumber() + " " + c.getSavingsAccountNumber());
		amount += (accountType == 1 ? c.getCheckingBalance() : c.getSavingsBalance());
		System.out.println(amount);
		if (accountType == 1 && c.getCheckingAccountNumber() != 0 ||accountType == 2 && c.getSavingsAccountNumber() != 0){
			System.out.println("\nEnter in the amount to withdraw from the account: ");
			double withdrawAmount = Double.parseDouble(in.nextLine());
			amount -= withdrawAmount; //summing the balance with the amount deposited
			while (amount < 0){
				System.out.println("Insuffiect funds! Try again or enter 0: ");
				withdrawAmount = Double.parseDouble(in.nextLine());
				amount -= withdrawAmount; //summing the balance with the amount deposited
			} 
			
			if (accountType == 1) {
				c.setCheckingBalance(amount);
			} else {
				c.setSavingsBalance(amount);
			}
			DAOImpl.accountTransaction(c, accountType, amount);
		
			System.out.println();
			l.trace("WITHDRAW FROM " + (c.getFirstName().toUpperCase() + " "
				+ c.getLastName().toUpperCase() + " ACCOUNT#: " + (accountType == 1 ? 
				c.getCheckingAccountNumber() : c.getSavingsAccountNumber()) + " IN THE AMOUNT OF: "
				+ withdrawAmount + " CURRENT BALANCE: " + (accountType == 1 ? 
				c.getCheckingBalance() : c.getSavingsBalance())));
		} 
		else {
			System.out.println(accountType == 1 ? "\nYou do not currently have a checking account" : "\nYou do "
					+ "not currently have a savings account");
		}
	}
/**
********************************************************************************************************
* @METHOD TO VIEW ALL OF A CUSTOMERS INFORMATION
*********************************************************************************************************
 */
	public static void viewAccountInfo(Customer c) {
		System.out.println(c.getApproved());	
		//Printing out the customers information
		System.out.println("\nUserID: " + c.getUserID() + "\nFirst Name: " + c.getFirstName() 
			+ "\nLast Name: " + c.getLastName() + "\nUsername: " + c.getUserName()
			+ "\nPassword: " + c.getPassword()
			+ "\nRole: " + c.getRole() 
			+ "\nStatus: " + (c.getApproved()==1?"Pending":"Approved")
			+ "\nChecking Account: " + (c.getCheckingAccountNumber()==1?"Yes":"No")
			+ "\nSavings Account: " + (c.getSavingsAccountNumber()==2?"Yes":"No")
			+ "\nChecking Balance: " + (c.getCheckingAccountNumber()==1?c.getCheckingBalance():"No") 
			+ "\nSavings Balance: " + (c.getSavingsAccountNumber()==2?c.getSavingsBalance():"No"));
	}
/**	
*********************************************************************************************************
* @METHOD TO VIEW JUST THE BALANCE OF THE RESPECTIVE ACCOUNT
*********************************************************************************************************
 */
	public static void viewBalance(Customer c) {
		System.out.println("\nSelect the type of account you wish to" + " see a balance for:\n1. Checking or 2. Savings: ");
		int balanceChoice = Integer.parseInt(in.nextLine());
		System.out.println("\n$" + (balanceChoice == 1 ? c.getCheckingBalance() : c.getSavingsBalance() + "0" ));
	}
/**
*********************************************************************************************************
* @METHOD TO CHANGE THE CUSTOMERS PERSONAL INFORMATION (FIRST NAME, LAST NAME, EMAIL, PASSWORD
*********************************************************************************************************
 */
	public static void editPersonalInfo(Customer c) {
		
		String again = null;
		do {              //Loop to run until customer is finished updating things
			System.out.println("\nEdit Personal Information Menu\n1. First Name\n2. Last Name"
					+ "\n3. Password\n4. Exit\nSelect Option: ");
			int myEditsChoice = Integer.parseInt(in.nextLine());
			
			switch (myEditsChoice) { //Base on selection will determine which case gets ran
			case 1:
				System.out.println("\nEnter in your new first name: ");
				String firstName = in.nextLine();
				String fName = c.getFirstName();
				String lName = c.getLastName();
				c.setFirstName(firstName);
				DAOImpl.changePersonalInfo(myEditsChoice, c);; //newline entered
				System.out.println();
				l.trace("FIRST NAME CHANGE FOR " + fName.toUpperCase() + " " + lName.toUpperCase()
				+ " to " + c.getFirstName().toUpperCase());
				break;
			case 2:
				System.out.println("\nEnter in your new last name: ");
				String lastName = in.nextLine();
				String fName1 = c.getFirstName();
				String lName1 = c.getLastName();
				c.setLastName(lastName);
				DAOImpl.changePersonalInfo(myEditsChoice, c);; //newline entered
				System.out.println();
				l.trace("LAST NAME CHANGE FOR " + fName1.toUpperCase() + " " + lName1.toUpperCase()
					+ " to " + c.getLastName().toUpperCase());
				break;
			case 3:
				System.out.println("\nEnter in your new password name: ");
				String password = in.nextLine();
				String fName3 = c.getFirstName();
				String lName3 = c.getLastName();
				c.setPassword(password);
				DAOImpl.changePersonalInfo(myEditsChoice, c);; //newline entered
				System.out.println();
				l.trace("PASSWORD CHANGE FOR " + fName3.toUpperCase() + " " + lName3.toUpperCase()
				+ " to " + c.getPassword().toUpperCase());				
				break;
			case 4:
				break;
			default:
				System.out.println("You did not enter in a valid selection " + "\nPlease try again");
				editPersonalInfo(c);
				break;
			}
			System.out.println("\nReturn to Edit Menu, \n'y' or 'n': ");
			again = in.nextLine(); //user input for running it again
			again.toLowerCase();
		}while(again.equals("y"));
		Menus.accountMenu(c);
	}
}
/**
	*********************************************************************************************************
*										END CLASS CUSTOMER
*********************************************************************************************************
*/

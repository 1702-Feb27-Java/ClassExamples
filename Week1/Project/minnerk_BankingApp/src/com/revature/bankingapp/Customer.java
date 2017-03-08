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

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;
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
	public Customer(String firstName, String lastName, String userID, String password, String email) {
		super(firstName, lastName, userID, password, email);
	}
	
/**
*********************************************************************************************************
*	@ CONSTRUCTOR WITH ARGUMENTS FOR PERSON & CUSTOMER
*********************************************************************************************************
*/	
	public Customer(String firstName, String lastName, String userID, String password, String email, 
			int accessLevel, boolean approved, int checkingAccountNumber, int savingsAccountNumber, 
			double checkingBalance, double savingsBalance) {
		super(firstName, lastName, userID, password, email, accessLevel, approved);
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
/**
*********************************************************************************************************
*  										END CLASS CUSTOMER
*********************************************************************************************************
*/


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
		return getUserID() + ":" + getPassword() + ":" + getFirstName() + ":" + getLastName() + ":" 
	+ getEmail() + ":" 	+ getAccessLevel() + ":" + isApproved() + ":" + checkingAccountNumber + ":"
				+ savingsAccountNumber + ":" + checkingBalance + ":" + savingsBalance;
	}

/**
*********************************************************************************************************
*	@METHOD TO ADD A NEW PERSON TO THE BANKING APPLICATION
*********************************************************************************************************
*/	
	public static void addNewPerson() {
		try {
			in = new Scanner(System.in); //Creating input stream
			System.out.print("\nWhat type of account, 1. Customer or 2. Employee: ");
			String type = in.nextLine();
			//User prompts and getting values
			System.out.print("\nEnter in your first name: ");
			String firstName = in.nextLine();
			System.out.print("Enter in your last name: ");
			String lastName = in.nextLine();
			System.out.print("Enter in your email address: ");
			String email = in.nextLine();
			System.out.print("Enter in a user name: ");
			String userID = in.nextLine();
			boolean valid = CustomerFile.verifyInfo(0, userID);
			while(valid){
				System.out.print("That user name is taken, please try another one: ");
				userID = in.nextLine();
				valid = CustomerFile.verifyInfo(0, userID);
			}
			System.out.print("Enter in a password: ");
			String password = in.nextLine();
			
			//Assigning respective values entered into a Customer object
			Customer c = new Customer(firstName, lastName, userID, password, email);
			if(type.equals("2"))
				c.setAccessLevel(0);
			CustomerFile.newPersonToFile(c);  //Calling method to send data to text file
			System.out.println();
			l.trace("NEW SERVICE CREATED FOR " + (c.getFirstName().toUpperCase()
					+ " " + c.getLastName().toUpperCase()));
			Menus.loginMenu(Integer.parseInt(type));
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
		System.out.print("\nSelect the type of account you wish to open" + " 1. Checking or 2. Savings: ");
		int accountChoice = Integer.parseInt(in.nextLine());  //Getting input
		System.out.println();		
		
		//Getting the existing checking/saving account number
		double accountNum = (accountChoice == 1 ? c.getCheckingAccountNumber() : c.getSavingsAccountNumber());
		//Checking to see if customer already has the type of account, if so, they cannot open another one, if they 
		//don't have one a random number of 8 digits is created and entered into the text file
		int number = 0;
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
			}
			CustomerFile.updateRecord(c); //deleting current record from text file
		}
		//Selecting which account to update
		if (accountChoice == 1) {
			c.setCheckingAccountNumber(number);
		} else {
			c.setSavingsAccountNumber(number);
		}
		CustomerFile.newPersonToFile(c); //appending to current text file
		l.trace("NEW ACCOUNT CREATED FOR " + (c.getFirstName().toUpperCase() + " "
				+ c.getLastName().toUpperCase() + " ACCOUNT#: " + (accountChoice == 1 ? 
						c.getCheckingAccountNumber() : c.getSavingsAccountNumber())));
	}
/**
*********************************************************************************************************
* @METHOD TO DEPOSIT MONEY THE RESPECTIVE ACCOUNT SELECTED
*********************************************************************************************************
*/
	public static void accountDeposit(Customer c) {
		System.out.print(
				"\nSelect the type of account you wish to" + " deposit money into:\n1. Checking or 2. Savings: ");
		int depositChoice = Integer.parseInt(in.nextLine()); //User entry for what kind of account
		double depositBal = 0.0;
		
		//Assigning what is currently in the account balance
		depositBal += (depositChoice == 1 ? c.getCheckingBalance() : c.getSavingsBalance()); 
		CustomerFile.updateRecord(c); //deleting line in text file
		System.out.print("\nEnter in the amount to deposit into the account: ");
		double depositAmount = Double.parseDouble(in.nextLine());
		depositBal += depositAmount; //summing the balance with the amount deposited
		if (depositChoice == 1) {
			c.setCheckingBalance(depositBal);
		} else {
			c.setSavingsBalance(depositBal);
		}
		CustomerFile.newPersonToFile(c); //sent to file
		CustomerFile.newPersonToFile(c); //sent to file
		System.out.println();
		l.trace("DEPOSIT FOR " + (c.getFirstName().toUpperCase() + " "
			+ c.getLastName().toUpperCase() + " ACCOUNT#: " + (depositChoice == 1 ? 
			c.getCheckingAccountNumber() : c.getSavingsAccountNumber()) + " IN THE AMOUNT OF: "
			+ depositAmount + " CURRENT BALANCE: " + (depositChoice == 1 ? 
			c.getCheckingBalance() : c.getSavingsBalance())));
		}
		
/**
*********************************************************************************************************
* @METHOD TO WITHDRAW MONEY THE RESPECTIVE ACCOUNT SELECTED
*********************************************************************************************************
 */
	public static void accountWithdraw(Customer c) {
		System.out.print(
				"\nSelect the type of account you wish to withdraw money from:\n1. Checking or 2. Savings: ");
		int withdrawChoice = Integer.parseInt(in.nextLine()); //User entry for what kind of account
		System.out.println("\nYou have $" + (withdrawChoice == 1 ? c.getCheckingBalance(): c.getSavingsBalance())
				+ " in your account");
		double withdrawBal = 0.0;
		
		//Assigning what is currently in the account balance
		withdrawBal += (withdrawChoice == 1 ? c.getCheckingBalance() : c.getSavingsBalance()); 
		CustomerFile.updateRecord(c); //deleting line in text file
		double withdrawAmount = 0.0;
		do {
			System.out.print("\nEnter in the amount to withdraw from the account,\n"
					+ "if you have no money or want to exit withdraw enter 0: "); //user prompt
			withdrawAmount = Double.parseDouble(in.nextLine()); 
			withdrawBal -= withdrawAmount; //summing the balance with the amount deposited
		}while (withdrawBal < 0);
		
		if (withdrawChoice == 1) {
			c.setCheckingBalance(withdrawBal);
		} else {
			c.setSavingsBalance(withdrawBal);
		}
		CustomerFile.newPersonToFile(c); //sent to file
		System.out.println();
		l.trace("WITHDRAW FOR " + (c.getFirstName().toUpperCase() + " "
				+ c.getLastName().toUpperCase() + " ACCOUNT#: " + (withdrawChoice == 1 ? 
						c.getCheckingAccountNumber() : c.getSavingsAccountNumber()) + " IN THE AMOUNT OF: "
						+ withdrawAmount + " CURRENT BALANCE: " + (withdrawChoice == 1 ? 
								c.getCheckingBalance() : c.getSavingsBalance())));

	}
/**
********************************************************************************************************
* @METHOD TO VIEW ALL OF A CUSTOMERS INFORMATION
*********************************************************************************************************
 */
	public static void viewAccountInfo(Customer c) {
		String s = c.toString();  //Creating string object of customer
		String[] arr = s.split(":"); //splitting it into an array
		
		//Printing out the customers information
		System.out.println("\nFirst Name: " + arr[2] + "\nLast Name: " + arr[3] + "\nUsername: " + arr[0]
				+ "\nPassword: " + arr[1] + "\nEmail: " + arr[4] + "\nAccountLevel: " + arr[5] + "\nApproved Account: "
				+ (arr[6] == "true" ? "Yes" : "No") + "\nChecking Account Number:: " + arr[7]
				+ "\nSavings Account Number: " + arr[8] + "\nChecking Account Balance: " + arr[9]
				+ "\nSavings Account Balance: " + arr[10]);
	}
/**	
*********************************************************************************************************
* @METHOD TO VIEW JUST THE BALANCE OF THE RESPECTIVE ACCOUNT
*********************************************************************************************************
 */
	public static void viewBalance(Customer c) {
		System.out.print("\nSelect the type of account you wish to" + " see a balance for:\n1. Checking or 2. Savings: ");
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
			System.out.print("\nEdit Personal Information Menu\n1. First Name\n2. Last Name"
					+ "\n3. Email Address\n4. Password\n5. Exit\nSelect Option: ");
			int myEditsChoice = Integer.parseInt(in.nextLine());
			
			switch (myEditsChoice) { //Base on selection will determine which case gets ran
			case 1:
				CustomerFile.updateRecord(c);  //line deleted from text file
				System.out.print("\nEnter in your new first name: ");
				String firstName = in.nextLine();
				String fName = c.getFirstName();
				String lName = c.getLastName();
				c.setFirstName(firstName);
				CustomerFile.newPersonToFile(c); //newline entered
				System.out.println();
				l.trace("FIRST NAME CHANGE FOR " + fName.toUpperCase() + " " + lName.toUpperCase()
				+ " to " + c.getFirstName().toUpperCase());
				break;
			case 2:
				CustomerFile.updateRecord(c);
				System.out.print("\nEnter in your new last name: ");
				String lastName = in.nextLine();
				String fName1 = c.getFirstName();
				String lName1 = c.getLastName();
				c.setLastName(lastName);
				CustomerFile.newPersonToFile(c);
				System.out.println();
				l.trace("LAST NAME CHANGE FOR " + fName1.toUpperCase() + " " + lName1.toUpperCase()
					+ " to " + c.getLastName().toUpperCase());
				break;
			case 3:
				CustomerFile.updateRecord(c);
				System.out.print("\nEnter in your new email address name: ");
				String email = in.nextLine();
				String fName2 = c.getFirstName();
				String lName2 = c.getLastName();
				c.setEmail(email);
				CustomerFile.newPersonToFile(c);
				System.out.println();
				l.trace("EMAIL ADDRESS CHANGE FOR " + fName2.toUpperCase() + " " + lName2.toUpperCase()
				+ " to " + c.getEmail().toUpperCase());				
				break;
			case 4:
				CustomerFile.updateRecord(c);
				System.out.print("\nEnter in your new password name: ");
				String password = in.nextLine();
				String fName3 = c.getFirstName();
				String lName3 = c.getLastName();
				c.setPassword(password);
				CustomerFile.newPersonToFile(c);
				System.out.println();
				l.trace("PASSWORD CHANGE FOR " + fName3.toUpperCase() + " " + lName3.toUpperCase()
				+ " to " + c.getPassword().toUpperCase());				
				break;
			case 5:
				break;
			default:
				System.out.println("You did not enter in a valid selection " + "\nPlease try again");
				editPersonalInfo(c);
				break;
			}
			System.out.print("\n'y' - Return to Edit Menu, 'n' - go back to Accounts Menu: ");
			again = in.nextLine(); //user input for running it again
			again.toLowerCase();
		}while(again.equals("y"));
		Menus.accountMenu(c);
	}
/**	
*********************************************************************************************************
* @METHOD USED TO TAKE THE ONE LINE OF TEXT AND PUT IT INTO A CUSTOMER OBJECT
*********************************************************************************************************
 */
	public static Customer setCustomerInfo(String[] sArr){
		Customer c = new Customer();
		
		//Sets all of the fields for a Customer object from a line in the text file
		c.setFirstName(sArr[2]);
		c.setLastName(sArr[3]);
		c.setUserID(sArr[0]);
		c.setPassword(sArr[1]);
		c.setEmail(sArr[4]);
		c.setAccessLevel(Integer.parseInt(sArr[5]));
		c.setApproved(Boolean.parseBoolean(sArr[6]));
		c.setCheckingAccountNumber(Integer.parseInt(sArr[7]));
		c.setSavingsAccountNumber(Integer.parseInt(sArr[8]));
		c.setCheckingBalance(Double.parseDouble(sArr[9]));
		c.setSavingsBalance(Double.parseDouble(sArr[10]));
		return c;
	}
/**	
*********************************************************************************************************
* @METHOD TO GET ONE LINE FROM THE TEXT DOCUMENT
*********************************************************************************************************
 */
	public static Customer getCustomerLine(){
		BufferedReader br = null;
		Customer c = new Customer();
		
		try {
			br = new BufferedReader(new FileReader("person.txt"));
			String readIn = br.readLine();  //reads the line in
			String[] sArr = readIn.split(":"); //splits the line into an array
			c = Customer.setCustomerInfo(sArr); //calls method to put into a Customer object
		}catch (Exception e){
			e.getStackTrace();
		} 
		return c;
	}
}
/**
*********************************************************************************************************
*										END CLASS CUSTOMER
*********************************************************************************************************
*/

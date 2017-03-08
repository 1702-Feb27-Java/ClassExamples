/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: Employee.java
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
import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.Logger;
/**
*********************************************************************************************************
*										@CLASS EMPLOYEE EXTENDS PERSON
*********************************************************************************************************
 */
public class Employee extends Person {

	static Scanner in = new Scanner(System.in);
	static final Logger l =  Logger.getRootLogger();
/**
*********************************************************************************************************
* @DEFAULT CONSTRUCTOR
*********************************************************************************************************
*/
	public Employee() {

	}
	/**
*********************************************************************************************************
* @CONSTRUCTOR WITH ARGUMENTS FOR PERSON
*********************************************************************************************************
* 
*/
	public Employee(String firstName, String lastName, String userID, String password, String email) {
		super(firstName, lastName, userID, password, email);
	}
	/**
*********************************************************************************************************
* @METHOD TO APPROVE ACCOUNTS FOR CUSTOMERS WITH A FALSE 
*********************************************************************************************************
*/
	public static void approveAccounts() {
		
		System.out.println("\nThis capability is currently under construction");

/*		BufferedReader br = null;
		Customer c = new Customer();
		try {
			br = new BufferedReader(new FileReader("person.txt"));
			String s = br.readLine();

			// Finds the specific info and sets all of the variables in
			// a customer based on what is in the string array
			while (s != null) {
				String[] sArr = s.split(":");

				if (sArr[6].equals("false")) { // approved member in person if
												// false
												// the below executes
					c = Customer.setCustomerInfo(sArr);

					// Prints out the current customer that has not been
					// approved yet
					// and provides the option to approve their account
					System.out.println(c);
					System.out.print(
							"Would you like to approve the account for " + sArr[2] + " " + sArr[3] + " yes or n: ");
					String answer = new String();
					answer = in.nextLine();
					if (answer.equals("y")) {
						CustomerFile.updateRecord(c); // deleting line in text file
						c.setApproved(true); // sets approved for the specific customer
						CustomerFile.newPersonToFile(c); // Calling method to send to file
						l.trace("ACCOUNT APPROVED FOR " + (c.getFirstName().toUpperCase() + " "
								+ c.getLastName().toUpperCase()));
						}

					}
				}
			
				s = br.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
/**
*********************************************************************************************************
* @METHOD TO PULL ALL CUSTOMER ACCOUNTS
*********************************************************************************************************
*/
	public static void pullAccounts() {

		BufferedReader br = null;
		Customer c = new Customer();
		try {
			br = new BufferedReader(new FileReader("person.txt"));
			String s = br.readLine();
			System.out.println("\nCustomer List:\n" + 
					String.format("%10s", "First") + " | " + String.format("%10s", "Last") + " | " + String.format("%10s", "UserID") +  
					" | " + String.format("%10s", "Password") + " | " + String.format("%20s", "Email") + " | " + String.format("%12s", "Access Level") +
					" | " + String.format("%10s", "Approved") + " | " + String.format("%12s", "Checking #") + " | " + String.format("%10s", "Savings #") +
					" | " + String.format("%10s", "Chk Balance") + " | " + String.format("%10s", "Svg Balance"));
			// Gets every customer in the file and puts it into the array list
			
			while (s != null) {
				String[] sArr = s.split(":");
				c = Customer.setCustomerInfo(sArr);
								
				System.out.println(String.format("%10s",c.getFirstName())
					+ " | " + String.format("%10s",c.getLastName()) 
					+ " | " + String.format("%10s",c.getUserID()) 
					+ " | " + String.format("%10s",c.getPassword())
					+ " | " + String.format("%20s",c.getEmail()) 
					+ " | " + String.format("%12s",c.getAccessLevel())
					+ " | " + String.format("%10s",c.isApproved())
					+ " | " + String.format("%10s",c.getCheckingAccountNumber())
					+ " | " + String.format("%10s",c.getSavingsAccountNumber()) 
					+ " | " + String.format("%12s",c.getCheckingBalance())
					+ " | " + String.format("%10s",c.getSavingsBalance())); 

				s = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
/**
*********************************************************************************************************
 *  @METHOD TO GET A SIGNLE CUSTOMR IN FOR MANIPULATION
*********************************************************************************************************
*/
	public static void accessCustomerInfo() {
		
		System.out.println("\nThis capability is currently under construction");
	
	}
		/*BufferedReader br = null;
		Customer c = new Customer();
		
		try{
			br = new BufferedReader(new FileReader("person.txt"));
			c = Customer.getCustomerLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} return c;
	} 
*/
}

/**
*********************************************************************************************************
*  										END CLASS EMPLOYEE
*********************************************************************************************************
*/


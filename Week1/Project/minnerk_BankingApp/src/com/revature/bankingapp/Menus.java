/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: Menus.java
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

import java.util.Scanner;
import org.apache.log4j.Logger;
/**
*********************************************************************************************************
** @CLASS MENUS
*********************************************************************************************************
*/
public class Menus {
	
	static final Logger l =  Logger.getRootLogger();
	static Scanner in = new Scanner(System.in);
/**
********************************************************************************************************
*  @METHOD TO DISPLAY OPENING MENU
*********************************************************************************************************
*/
	public static void displayMainMenu() {
		
		System.out.print("Welcome to the the MinnComm Banking Application: \n"
				+ "Please make a selection from the menu below: \n\n1. Create an Account\n"
				+ "2. Login\n3. Exit\n");

		String selection = null;
			do{ //Loop that ensures that either a 1 or 2 is entered in, if not repeats until done
				System.out.println("\nSelect a valid option from the above Menu: ");
				selection = in.nextLine();
			}while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3"));
			
			switch(selection){
			case "1":
				personTypeSelection();	
				break;
			case "2":
				loginMenu(0);
				break;
			case "3":
				break;
			}
			System.out.println("Have a nice day, Goodbye!");			
	}
/**
********************************************************************************************************
*  @METHOD TO DISPLAY TYPE OF ACCOUNT TO CREATE MENU
*********************************************************************************************************
*/
	public static void personTypeSelection() {
		
		System.out.println("\nType of Account Menu:\n\n1. Customer\n2. Employee\n3. Admin\n4. Exit");

		String selection = null;
			do{ //Loop that ensures that either a 1 or 2 is entered in, if not repeats until done
				System.out.println("\nSelect an option from the above Menu: ");
				selection = in.nextLine();
			}while (!selection.equals("1") && !selection.equals("2")
					&& !selection.equals("3") && !selection.equals("4"));
			
			switch (selection) { //determines which statement to execute
			case "1":
				Customer.addNewPerson(2); //calls to create a new customer line in the text file
				break;
			case "2":
				loginMenu(0); //calls the login menu to add an Admin, *Must have admin creation user name and password
				break;
			case "3":
				loginMenu(1); //calls the login menu to add an Employee, *Must have employee creation user name and password
				break;
			case "4":
				break;
			}
							
	}
/**
*********************************************************************************************************
*  @METHOD THAT GETS USER LOGIN CREDENTIALS
*********************************************************************************************************
*/
	public static void loginMenu(int i) {
		try {
			System.out.println("\nLog-in Menu: ");
			System.out.println("Enter in your username: "); //prompt
			String username = in.nextLine(); //get value
			System.out.println("Enter in your password: "); //prompt
			String password = in.nextLine(); //get value
			Customer c = CustomerFile.verifyLogin(username, password, i);  //call to method to verify values
			if (i == 0)
				employeeMenu(); //calls employee menu if verification is valid
			else
				accountMenu(c); //calls next menu if verification of is valid
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			in.close();
		}
	}
/**
*********************************************************************************************************
** @METHOD THAT DISPLAYS A MENU FOR AN EMPLOYEE
*********************************************************************************************************
*/	
	//Method that displays a menu for an Employee
	public static void employeeMenu(){
		System.out.print("\nEmployee Menu\n1. Approve Accounts"
			+ "\n2. View All Customers\n3. View Customer Info\nSelect an option from the above Menu: ");
		String selection = in.nextLine();
		while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3")){
			System.out.print("Invalid Entry! Enter an option from 1 - 3: ");
			selection = in.nextLine();
		}
		switch (selection){
			case "1":
				Employee.approveAccounts();
				break;
			case "2":
				Employee.pullAccounts();
				break;
			case "3":
				Employee.accessCustomerInfo();
				break;
			}
				
		}
			
/**
*********************************************************************************************************
** @METHOD TO DISPLAY THE ACCOUNT MENU 
*********************************************************************************************************
*/
	public static void accountMenu(Customer c) {

		String again = null;
		String selection = null;

		do {
			System.out.print("\nAccount Menu\n1. Open an Account\n2. View Account Info "
					+ "\n3. Make a Deposit\n4. Make a Withdraw\n5. Check Balance "
					+ "\n6. Change Personal Info\n7. Exit Program\nSelect an option"
					+ " from the above Menu: ");

			selection = in.nextLine();

			switch (selection) {
			case "1":
				Customer.openAccount(c); //call to open a checking or savings account
				break;
			case "2":
				Customer.viewAccountInfo(c); //call to view the customers information
				break;
			case "3":
				Customer.accountDeposit(c); //call to deposit money into an account
				break;
			case "4":
				Customer.accountWithdraw(c); //call to withdraw money from an account
				break;
			case "5":
				Customer.viewBalance(c); //call to view the balance of an account
				break;
			case "6":
				Customer.editPersonalInfo(c); //call to edit personal info menu
				break;
			case "7": 
				break;
			default:
				System.out.println("You did not enter in a valid selection " + "\nPlease try again");
				accountMenu(c); //call to account menu if user selected invalid operation
				break;
			}
			System.out.print("\n'y' - return to Account Menu, 'n' - to exit: "); //prompt to run again
			again = in.nextLine();
		} while (again.equals("y")); //if y run again, otherwise exit
		System.out.println("Have a nice Day!");
	}
}
/**
*********************************************************************************************************
*  										END CLASS MENUS
*********************************************************************************************************
*/


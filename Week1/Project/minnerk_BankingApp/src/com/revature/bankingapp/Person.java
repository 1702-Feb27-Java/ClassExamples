/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: Person.java
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
/**
*********************************************************************************************************
 *  @CLASS PERSON - PARENT CLASS TO EMPLOYEE AND CUSTOMER
*********************************************************************************************************
*/
public class Person {

	// Declaration of variables for any individual within the banking
	// application system
	private int userID = 0;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int role = 2;
	private int approved = 0;
	
/**
*********************************************************************************************************
*  @DEFAULT CONSTRUCTOR WITH NO ARGUMENTS
*********************************************************************************************************
*/
	public Person() {

	}
/**
*********************************************************************************************************
*  @CONSTRUCTOR WITH ARGUMENTS
*********************************************************************************************************
*/
	public Person(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
/**
*********************************************************************************************************
*  @CONSTRUCTOR WITH ARGUMENTS
*********************************************************************************************************
*/
	public Person(String firstName, String lastName, String username, String password, 
			int role, int approved) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}

/**
*********************************************************************************************************
*  @ GETTERS AND SETTERS
*********************************************************************************************************
	*/	// Getters and Setters for all private variables
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}
/**
*********************************************************************************************************
*  @METHOD OVER WRITING TO STRING
*********************************************************************************************************
*/
	public String toString() {
		return "Name: " + firstName + " " + lastName + "\n\t username: " + username + "\n\t: " + userID + "\n\t Password: "
				+ password;
	}
}
/**
*********************************************************************************************************
*  										END CLASS PERSON
*********************************************************************************************************
*/

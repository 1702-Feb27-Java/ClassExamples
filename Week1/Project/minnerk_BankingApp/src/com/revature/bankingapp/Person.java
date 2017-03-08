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
	private String firstName;
	private String lastName;
	private String userID;
	private String password;
	private String email;
	private int accessLevel = 1;
	private boolean approved = false;
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
	public Person(String firstName, String lastName, String userID, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.password = password;
		this.email = email;
	}

/**
*********************************************************************************************************
*  @CONSTRUCTOR WITH ADDITIONAL ARGUMENTS THAT INITIALLY HAVE DEFAULT VALUES
*********************************************************************************************************
*/	//Arguments Constructor with additional fields
	public Person(String firstName, String lastName, String userID, String password, String email, int accessLevel,
			boolean approved) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.password = password;
		this.email = email;
	}

/**
*********************************************************************************************************
*  @ GETTERS AND SETTERS
*********************************************************************************************************
	*/	// Getters and Setters for all private variables
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
/**
*********************************************************************************************************
*  @METHOD OVER WRITING TO STRING
*********************************************************************************************************
*/
	public String toString() {
		return "Name: " + firstName + " " + lastName + "\n\t Email: " + email + "\n\t: " + userID + "\n\t Password: "
				+ password;
	}
}
/**
*********************************************************************************************************
*  										END CLASS PERSON
*********************************************************************************************************
*/

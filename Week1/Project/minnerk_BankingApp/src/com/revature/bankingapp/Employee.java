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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.DAOImpl;
import com.revature.util.FactoryConnection;
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

	public static void approveAccounts() {
		
		System.out.println("\nAccount Approval: \nWhat type of accounts would you like to approve?\n1. Checking or 2. Savings ");
		int type = Integer.parseInt(in.nextLine());
	
		Customer c = new Customer();
	
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT u.USERID, u.FIRSTNAME, u.LASTNAME, u.USERNAME, u.PW, u.ROLEID, a.STATUSID, a.TYPEID, a.BALANCE FROM USERS u INNER JOIN CUSTOMERACCOUNTS ca ON u.USERID=ca.USERID INNER JOIN ACCOUNTS a ON ca.ACCTID=a.ACCTID WHERE TYPEID=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, type);
			ResultSet rs = ps.executeQuery();
			System.out.println("test2");
			while (rs.next()){
				c = DAOImpl.formatSet(rs, c);
				System.out.println(c.toString());
			}
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}			
/**
*********************************************************************************************************
* @METHOD TO PULL ALL CUSTOMER ACCOUNTS
*********************************************************************************************************
*/
	public static void pullAccounts() {

		Customer c = new Customer();
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT u.USERID, u.FIRSTNAME, u.LASTNAME, u.USERNAME, u.PW, u.ROLEID, a.STATUSID, a.TYPEID, a.BALANCE FROM USERS u INNER JOIN CUSTOMERACCOUNTS ca ON u.USERID=ca.USERID INNER JOIN ACCOUNTS a ON ca.ACCTID=a.ACCTID";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("\nCustomer List:\n" + 
					String.format("%8s", "UserID") + " | " +  String.format("%15s", "First") + " | " + String.format("%15s", "Last") +  
					" | " + String.format("%15s", "Username")+ " | " + String.format("%15s", "Password")  + " | " + String.format("%15s", "Role") +
					" | " + String.format("%15s", "Status") + " | " + String.format("%12s", "Checking #") + " | " + String.format("%12s", "Savings #") +
					" | " + String.format("%12s", "Chk Balance") + " | " + String.format("%12s", "Svg Balance"));
			System.out.println("------------------------------------------------------------------------------"
					+ "--------------------------------------------------------------------------------------------------------------------");
			int role;
			int approved;
			String s1 = null;
			String s2 = null;
			
			while (rs.next()){
				c = DAOImpl.formatSet(rs, c);
				role = c.getRole();
				approved = c.getApproved();
				
				
				switch(role){
				case 1:
					s1 = "Admin";
					break;
				case 2:
					s1 = "Employee";
					break;
				case 3:
					s1 = "Customer";
					break;
				}
				
				switch(approved){
				case 1:
					s1 = "Pending";
					break;
				case 2:
					s1 = "Approved";
					break;
				case 3:
					s1 = "Denied";
					break;
				}
				
				System.out.println(String.format("%8s",c.getUserID())
						+ " | " + String.format("%15s",c.getFirstName())				
						+ " | " + String.format("%15s",c.getLastName()) 
						+ " | " + String.format("%15s",c.getUserName())
						+ " | " + String.format("%15s",c.getPassword())
						+ " | " + String.format("%15s", s1)
						+ " | " + String.format("%15s", s2)
						+ " | " + String.format("%12s",c.getCheckingAccountNumber())
						+ " | " + String.format("%12s",c.getSavingsAccountNumber()) 
						+ " | " + String.format("%12s",c.getCheckingBalance())
						+ " | " + String.format("%12s",c.getSavingsBalance())); 
			}
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
}

/**
*********************************************************************************************************
*  										END CLASS EMPLOYEE
*********************************************************************************************************
*/


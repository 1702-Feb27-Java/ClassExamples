/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: Employee.java
* PROGRAMMER: KEITH MINNER
* 
* PURPOSE: ALLOW A USER TO SIGN UP FOR A BANKING SERVICE TO INCLUDE A CHECKING AND / OR SAVINGS ACCOUNT
* WITH THE CAPABILITIES TO DEPOSIT, WITHDRAW, VIEW AND EDIT PERSONAL INFORMATION.  AN EMPLOYEE CAN
* VIEW CUSTOMER INFORMATION, APPROVE ACCOUNTS, AND EDIT CUSTOMER INFO.  ADDITIONALLY AN ADMIN CAN 
* APPROVE CUSTOMER ACCOUNTS.
*========================================================================================================
*										PROJECT FILES
*
* Customer.java				MenusClassTest.java	
* DAOImpl.java				Person.java			
* Employee.java				PersonClassTest.java			
* EmployeeClassTest.java	UserScreen.java	
* Menus.java				UserScreenTest.java	

*========================================================================================================
*										PACKAGE & IMPORT FILES
*********************************************************************************************************
*/
package com.revature.bankingapp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
/**
*********************************************************************************************************
* @METHOD TO ALLOW AN EMPLOYEE TO APPROVE CUSTOMER ACCOUNTS AND ADMIN THE EMPLOYEE ACCOUNTS
*********************************************************************************************************
*/
	public static void approveAccounts(Customer c, int i) {
		
		 int resolver = c.getUserID();
		
		try (Connection connect = FactoryConnection.getConnection();){ //getting a connection
			connect.setAutoCommit(false); //turning off auto commit until end of function
			String sql = "SELECT u.USERID, u.FIRSTNAME, u.LASTNAME, u.USERNAME, u.PW, u.ROLEID, a.STATUSID, a.TYPEID, a.BALANCE FROM USERS u INNER JOIN CUSTOMERACCOUNTS ca ON u.USERID=ca.USERID INNER JOIN ACCOUNTS a ON ca.ACCTID=a.ACCTID WHERE STATUSID=? AND ROLEID=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, i+1);
			ResultSet rs = ps.executeQuery();

			String sql1 = "Call update_status (?, ?, ?)";  //call to procedure to update the status of people
			CallableStatement cs = connect.prepareCall(sql1);
			while (rs.next()){
				c = DAOImpl.formatSet(rs, c);
				System.out.println("Name: " + c.getFirstName() + " " + c.getLastName());
				System.out.println("Would you like to approve this account?\n'y' or 'n': "); //user option to approve or deny account
				String approve = in.nextLine();
				if (approve.equals("y")){
					cs.setInt(1, c.getUserID()); //setting variables for the called procedure
					cs.setInt(2, 2);			//for userid, status to approved, and resolved
					cs.setInt(3, resolver);
					cs.execute();				
				} else {
					cs.setInt(1, c.getUserID());
					cs.setInt(2, 3);
					cs.setInt(3, resolver);
					cs.execute();				
				}
			}
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}			
/**
*********************************************************************************************************
* @METHOD TO PULL ALL CUSTOMER ACCOUNTS AND DISPLAY THEM TO THE SCREEN
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
		Scanner in = new Scanner(System.in);
		System.out.println("\nEnter in the Customer's id number to edit there information: ");
		int customerId = Integer.parseInt(in.nextLine());
		
		Customer c = new Customer();
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT u.USERID, u.FIRSTNAME, u.LASTNAME, u.USERNAME, u.PW, u.ROLEID, a.STATUSID, a.TYPEID, a.BALANCE FROM USERS u INNER JOIN CUSTOMERACCOUNTS ca ON u.USERID=ca.USERID INNER JOIN ACCOUNTS a ON ca.ACCTID=a.ACCTID WHERE u.USERID=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				c = DAOImpl.formatSet(rs, c);
			}
			Menus.accountMenu(c);
			in.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}

/**
*********************************************************************************************************
*  										END CLASS EMPLOYEE
*********************************************************************************************************
*/


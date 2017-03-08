/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: CustomeClassTest.java
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Keith
 *
 */
public class CustomerClassTest {
	Customer c = Customer.getCustomerLine();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckingAccountNumber() {
		c.setCheckingAccountNumber(1);
		c.getCheckingAccountNumber();
	}
	
	@Test
	public void testSavingsAccountNumber() {
		c.setSavingsAccountNumber(1);
		c.getSavingsAccountNumber();
	}
	
	@Test
	public void testCheckingBalance() {
		c.setCheckingBalance(1);
		c.getCheckingBalance();
	}
	
	@Test
	public void testSavingsBalance() {
		c.setSavingsBalance(1);
		c.getSavingsBalance();
	}
	
	@Test
	public void testToString() {
		Customer.addNewPerson();
	}
	
	@Test
	public void newAddLine() {
		Customer.addNewPerson();
	}
	
	@Test
	public void openAccount() {
		Customer.openAccount(c);
	}

	@Test
	public void accountWithdraw() {
		Customer.accountWithdraw(c);
	}

	@Test
	public void accountDeposit() {
		Customer.accountDeposit(c);
	}

	@Test
	public void viewAccountInfo() {
		Customer.viewAccountInfo(c);
	}
	
	@Test
	public void viewBalance() {
		Customer.viewBalance(c);
	}

	@Test
	public void editPersonalInfo() {
		Customer.editPersonalInfo(c);
	}

	@Test
	public void setCustomerInfo() {
		BufferedReader br = null;
				
		try {
			br = new BufferedReader(new FileReader("person.txt"));
			String readIn = br.readLine();
			String[] sArr = readIn.split(":");
			Customer.setCustomerInfo(sArr);
		}catch (IOException ioe){
			ioe.getStackTrace();
		} finally {
			try{
				br.close();
			} catch (IOException ioe){
				ioe.getStackTrace();
			}
		}
	}		

	@Test
	public void getCustomerLine() {
		Customer.getCustomerLine();
	}

}

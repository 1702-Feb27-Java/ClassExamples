/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: CustomerFileTest.java
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Keith
 *
 */
//*******************************************************************************************************

public class CustomerFileTest {

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
	public void testNewPersonToFile() {
		CustomerFile.newPersonToFile(c);
	}

	@Test
	public void testUpdateRecord() {
		CustomerFile.updateRecord(c);
	}
	
	@Test
	public void testVerifyLogin() {
		String s1 = "k";
		String s2 = "m";
		int i = 0;
		CustomerFile.verifyLogin(s1, s2, i);
	}
	
	@Test
	public void testVerifyInfo1() {
		String s = "test";
		int i = 1;
		CustomerFile.verifyInfo(i, s);
	}
	
	@Test
	public void testVerifyInfo2() {
		String s = "test";
		int i = 0;
		CustomerFile.verifyInfo(i, s);
	}


}

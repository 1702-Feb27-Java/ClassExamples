/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: MenusClassTest.java
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
package com.revature.sourcetestfiles;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bankingapp.Customer;
import com.revature.bankingapp.Menus;

/**
 * @author Keith
 *
 */
public class MenusClassTest {

	
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
	public void testDisplayMainMenu() {
		Menus.displayMainMenu();
	}
	
	@Test
	public void testGotoSelection1() {
		Menus.gotoSelection(1);
	}
	
	@Test
	public void testGotoSelection2() {
		Menus.gotoSelection(2);
	}
	
	@Test
	public void testGotoSelection3() {
		Menus.gotoSelection(3);
	}
	
	@Test
	public void loginMenu1() {
		Menus.loginMenu(0);
	}
	
	@Test
	public void loginMenu2() {
		Menus.loginMenu(1);
	}
	
	@Test
	public void employeeMenu() {
		Menus.employeeMenu();
	}
	
	@Test
	public void accountMenu() {
		Customer c = Customer.getCustomerLine();
		Menus.accountMenu(c);
	}
}

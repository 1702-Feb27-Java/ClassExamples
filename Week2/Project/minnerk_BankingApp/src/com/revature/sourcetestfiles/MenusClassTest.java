/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: MenusClassTest.java
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
		Menus.personTypeSelection();
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
		Menus.employeeMenu(null, 0);
	}
	
	@Test
	public void accountMenu() {
		
	}
}

/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: PersonClassTest.java
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

import com.revature.bankingapp.Person;

/**
 * @author Keith
 *
 */
public class PersonClassTest {
	
	Person p = new Person("Keith", "Minner", "minncomm", "password");
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
	public void testFirstName() {
		p.setFirstName("Michael");
		p.getFirstName();
	}

	@Test
	public void testLastName() {
		p.setLastName("Scott");
		p.getLastName();
	}
	
	@Test
	public void testUserID() {
		p.setUserName("mjscott");
		p.getUserID();
	}
	
	@Test
	public void testPassword() {
		p.setPassword("scottstots");
		p.getPassword();
	}
	
	@Test
	public void testAccessLevel() {
		p.setFirstName("Ted");
		p.getFirstName();
	}
	
	@Test
	public void testApproved() {
		p.setApproved(2);
		p.getApproved();
	}
	
	@Test
	public void testToString() {
		p.toString();
	}
}

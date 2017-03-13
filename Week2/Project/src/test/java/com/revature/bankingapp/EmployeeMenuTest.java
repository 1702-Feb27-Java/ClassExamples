package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;
import com.revature.bankingapp.menu.AccountMenu;
import com.revature.bankingapp.menu.EmployeeMenu;
import com.revature.bankingapp.menu.IMenu;
import com.revature.bankingapp.menu.LoginMenu;

public class EmployeeMenuTest {

	private static final String CUSTOMERS_FILENAME = "customers.txt";
	private static final String EMPLOYEES_FILENAME = "employees.txt";
	private static final String ACCOUNTS_FILENAME = "accounts.txt";
	private User customer;
	private Account account;
	private User ocustomer;
	private Account account2;
	private Account account3;
	private User admin;
	private User employee;
	
	
	@Before
	public void setUp() throws Exception {

		customer = new User("newuser", "lastname", "customer", "password", Service.getInstance().getCustomerRole());
		ocustomer = new User("newuser", "lastname", "ocustomer", "password", Service.getInstance().getCustomerRole());
		employee = new User("e", "e", "employee", "password", Service.getInstance().getEmployeeRole());
		admin = new User("admin", "admin", "admin", "admin", Service.getInstance().getAdminRole());
		
		account = new Account("account", Service.getInstance().getCheckingAccountType());
		account2 = new Account("account2", Service.getInstance().getSavingAccountType());
		account3 = new Account("saving", Service.getInstance().getSavingAccountType());
		
		customer = Service.getInstance().saveAndReturnNewUser(customer);
		ocustomer = Service.getInstance().saveAndReturnNewUser(ocustomer);
		Service.getInstance().createNewAccount(customer, account);
		Service.getInstance().createNewAccount(customer, account2);
		Service.getInstance().createNewAccount(ocustomer, account3);
		
		List<Account> accounts = Service.getInstance().getAccountFromUser(customer);
		List<Account> accounts2 = Service.getInstance().getAccountFromUser(ocustomer);
		account = accounts.get(0);
		account2 = accounts.get(1);
		account3 = accounts2.get(0);
	}
	
	@After
	public void tearDown() throws Exception{
		Service.getInstance().deleteAccount(account);
		Service.getInstance().deleteAccount(account2);
		Service.getInstance().deleteAccount(account3);
		Service.getInstance().deleteUser(customer);
		Service.getInstance().deleteUser(ocustomer);
		Service.getInstance().deleteUser(admin);
		Service.getInstance().deleteUser(employee);
	}

	@Test
	/**
	 * test trying to access an existing account
	 * 
	 * attempts to access an account by giving it the id of an existing account. 
	 * User would be redirected to the Account Menu class
	 */
	public void testAccessingAccount() {
		IMenu menu = new EmployeeMenu(employee);
		int n = account.getAccountId();
		
		String s = "l\n"
				+  n+"\n" 	// account number
				+ "q\n"; 	// quit
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertTrue(menu instanceof AccountMenu);
		
	}
	/**
	 * test attempting to access a non existing account
	 * 
	 * attempts to access an account but has the incorrect id, resulting in user being
	 * redirected to Employee Menu as all test string end with q\n for quit
	 */
	@Test
	public void testAccessingNonExistingAccount(){
		IMenu menu = new EmployeeMenu(employee);
		int n = 203428; 
		
		String s = "l\n"
				+ n +"\n" 	// account number
				+ "q\n"; 	// quit
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertEquals(EmployeeMenu.class, menu.getClass());
		
	}
	
	@Test
	/**
	 * test admin creating a new account for an employee.
	 */
	public void adminCreatingEmployee(){
		
		
		IMenu menu = new EmployeeMenu(admin);
		 
		
		String s = "a\n" 	// add user
				+ "newadmin\n" //username
				+ "password123\n" //password
				+ "password123\n" //confirm password
				+ "q\n";
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		

		assertNotNull(Service.getInstance().getUser("newadmin"));
		assertEquals(Service.getInstance().getUser("newadmin").getRole(), Service.getInstance().getEmployeeRole());
		assertEquals(menu.getClass(), LoginMenu.class);
		Service.getInstance().deleteUser(Service.getInstance().getUser("newadmin"));
	}
	
	@Test
	/**
	 * test employee attempting to create another employee. 
	 * This will resuilt in no change to database, as employee's can't do this.
	 */
	public void employeeCreatingEmployee(){
		IMenu menu = new EmployeeMenu(employee);
		 
		
		String s = "a\n" 	// add user
				+ "newadmintwo\n" //username
				+ "password123\n" //password
				+ "password123\n" //confirm password
				+ "q\n";
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertNull(Service.getInstance().getUser("newadmintwo"));
		assertEquals(menu.getClass(), LoginMenu.class);
		
	}
	
	@Test(timeout=5000)
	/**
	 * test admin trying to create an employee with a duplicate name.
	 */
	public void duplicateCustomer(){

		String s = "a\n"  //register new user
				+ "customer\n" //'attempt' to create duplicate username
				+ "lemployee\n" //create new account
				+ "password\n" //password
				+ "password\n" //confirm password
				+ "q\n"; // exits out of menu
				
		Scanner scan = new Scanner(s);
		IMenu menu = new EmployeeMenu(admin);
		
		//user did not exist
		assertNotNull(Service.getInstance().getUser("customer"));
		assertNull(Service.getInstance().getUser("lemployee"));
		
		
		menu = menu.openMenu(scan);
		
		//user first attempt to add a duplicate user failed, then tried second one
		assertNotNull(Service.getInstance().getUser("customer"));
		assertNotNull(Service.getInstance().getUser("lemployee"));
		
		Service.getInstance().deleteUser(Service.getInstance().getUser("lemployee"));
	}

}

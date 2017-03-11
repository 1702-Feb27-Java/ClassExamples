package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.menu.AccountMenu;
import com.revature.bankingapp.menu.EmployeeMenu;
import com.revature.bankingapp.menu.IMenu;
import com.revature.bankingapp.menu.LoginMenu;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;
import com.revature.bankingapp.model.Employee;

public class EmployeeMenuTest {

	private static final String CUSTOMERS_FILENAME = "customers.txt";
	private static final String EMPLOYEES_FILENAME = "employees.txt";
	private static final String ACCOUNTS_FILENAME = "accounts.txt";
	
	
	@Before
	public void setUp() throws Exception {
		
		// resets the database  
		File customersFile = new File(CUSTOMERS_FILENAME);
		File employeesFile = new File(EMPLOYEES_FILENAME);
		File accountsFile = new File(ACCOUNTS_FILENAME);
		
		customersFile.delete();
		employeesFile.delete();
		accountsFile.delete();
		Database.getDatabase().reload();
	}

	@Test
	/**
	 * test trying to access an existing account
	 * 
	 * attempts to access an account by giving it the id of an existing account. 
	 * User would be redirected to the Account Menu class
	 */
	public void testAccessingAccount() {
		Employee employee = new Employee("username1", "Password", false);
		Account account = new Account("Test", "checking", 0, false);
		
		Database.getDatabase().addEmployee(employee);
		Database.getDatabase().addAccount(account);
		IMenu menu = new EmployeeMenu(employee);
		int n = account.getId();
		
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
		Employee employee = new Employee("username2", "Password", false);
		Account account = new Account("Test", "checking", 0, false);
		
		Database.getDatabase().addEmployee(employee);
		Database.getDatabase().addAccount(account);
		IMenu menu = new EmployeeMenu(employee);
		int n = 2034287; 
		
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
		Employee employee = new Employee("admin", "Password", true);
		
		Database.getDatabase().addEmployee(employee);
		IMenu menu = new EmployeeMenu(employee);
		 
		
		String s = "a\n" 	// add user
				+ "newadmin\n" //username
				+ "password123\n" //password
				+ "password123\n" //confirm password
				+ "q\n";
		Scanner scan = new Scanner(s);
		assertEquals(null,Database.getDatabase().getEmployeeByUsername("newadmin"));
		menu = menu.openMenu(scan);
		
		
		assertTrue(Database.getDatabase().getEmployeeByUsername("newadmin") != null);
		assertTrue(Database.getDatabase().getEmployeeByUsername("newadmin").isAdmin() == false);
		
		
		
		assertEquals(menu.getClass(), LoginMenu.class);
	}
	
	@Test
	/**
	 * test employee attempting to create another employee. 
	 * This will resuilt in no change to database, as employee's can't do this.
	 */
	public void employeeCreatingEmployee(){
		Employee employee = new Employee("nonadmin", "Password", false);
		
		Database.getDatabase().addEmployee(employee);
		IMenu menu = new EmployeeMenu(employee);
		 
		
		String s = "a\n" 	// add user
				+ "newadmintwo\n" //username
				+ "password123\n" //password
				+ "password123\n" //confirm password
				+ "q\n";
		Scanner scan = new Scanner(s);
		assertTrue(Database.getDatabase().getEmployeeByUsername("newadmintwo") == null);
		menu = menu.openMenu(scan);
		
		assertTrue(Database.getDatabase().getEmployeeByUsername("newadmintwo") == null);
		assertEquals(menu.getClass(), LoginMenu.class);
		
	}
	
	@Test(timeout=500)
	/**
	 * test admin trying to create an employee with a duplicate name.
	 */
	public void duplicateCustomer(){
		//set up user to attempted to be duplicated
		Employee admin = new Employee("admin", "password", true);
		Customer customer = new Customer("popularemployee", "password");
		Database.getDatabase().addEmployee(admin);
		Database.getDatabase().addCustomer(customer);
		
		
		
		String s = "a\n"  //register new user
				+ "popularemployee\n" //'attempt' to create duplicate username
				+ "lesspopularemployee\n" //create new account
				+ "password\n" //password
				+ "password\n" //confirm password
				+ "q\n"; // exits out of menu
				
		Scanner scan = new Scanner(s);
		IMenu menu = new EmployeeMenu(admin);
		
		//user did not exist
		assertTrue(Database.getDatabase().containsUsername("popularemployee"));
		assertFalse(Database.getDatabase().containsUsername("lesspopularemployee"));
		
		
		menu = menu.openMenu(scan);
		
		//user first attempt to add a duplicate user failed, then tried second one
		assertTrue(Database.getDatabase().containsUsername("popularemployee"));
		assertTrue(Database.getDatabase().containsUsername("lesspopularemployee"));
	}

}

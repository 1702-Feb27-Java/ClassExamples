package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.menu.AccountMenu;
import com.revature.bankingapp.menu.CreateAccountMenu;
import com.revature.bankingapp.menu.CustomerMenu;
import com.revature.bankingapp.menu.IMenu;
import com.revature.bankingapp.menu.LoginMenu;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;

public class CustomerMenuTest {

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
	 * Test that when the customer requests for a new account, it is redirected to the correct menu.
	 */
	public void testRequest () {
		Customer customer = new Customer("Username123", "Password");
		Database.getDatabase().addCustomer(customer);
		
		IMenu menu = new CustomerMenu(customer);
		
		String s = "n\n";
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertTrue(menu instanceof CreateAccountMenu);
	}
	
	@Test
	public void testNotAccessAccount() {
		Customer customer = new Customer("Username123", "Password");
		Account account = new Account("Test", "checking", 0, false);
				
		Database.getDatabase().addCustomer(customer);
		Database.getDatabase().addAccount(account);
		IMenu menu = new CustomerMenu(customer);
		int n = account.getId();
		
		//should fail to get account, and instead logout from second command
		String s = n + "\n" 	// account number
				+ "q\n"; 	// quit
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertTrue(menu instanceof LoginMenu);
	}
	
	@Test
	public void testAccessAccount() {
		Customer customer = new Customer("Username1234", "Password");
		Account account = new Account("Test", "checking", 0, false);
		
		Database.getDatabase().addCustomer(customer);
		Database.getDatabase().addAccount(account);
		IMenu menu = new CustomerMenu(customer);
		int n = account.getId();
		customer.getAccountIds().add(n);
		//should go to the account with id 51 
		String s = n+"\n" 	// account number
				+ "q\n"; 	// quit
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertTrue(menu instanceof AccountMenu);
	}
	
	

}

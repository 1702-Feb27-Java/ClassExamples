package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.menu.CreateAccountMenu;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;

public class CreateAccountMenuTest {
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
	 * Test user creating a new account
	 */
	public void testCustomerMakingAccount() {
		Customer customer = new Customer("username", "password");
		
		String s = "account Name\n" 	//account name
				+ "checking\n"; 		//account type	 
		
		Scanner scan = new Scanner(s);
		Database.getDatabase().addCustomer(customer);
		CreateAccountMenu menu = new CreateAccountMenu(customer);
		
		int nOfAccountsBefore = Database.getDatabase().getAccounts().size();
		int nOfCustomersAccountBefore = Database.getDatabase().getCustomer(customer.getId()).getAccountIds().size();
		menu.openMenu(scan);
		assertEquals( nOfAccountsBefore + 1 , Database.getDatabase().getAccounts().size());
		assertEquals( nOfCustomersAccountBefore + 1, Database.getDatabase().getCustomer(customer.getId()).getAccountIds().size());	
		

	}
	
	@Test(timeout=500)
	/**
	 * Test for when customer creates an invalid account name
	 */
	public void testInvalidAccountName() {
		Customer customer = new Customer("username", "password");
		
		String s = "account:as;djfans;gnj4Name\n" 	//account name
				+ "accountName\n"
				+ "checking\n"; 		//account type	 
		
		Scanner scan = new Scanner(s);
		Database.getDatabase().addCustomer(customer);
		CreateAccountMenu menu = new CreateAccountMenu(customer);
		
		int nOfAccountsBefore = Database.getDatabase().getAccounts().size();
		int nOfCustomersAccountBefore = Database.getDatabase().getCustomer(customer.getId()).getAccountIds().size();
		menu.openMenu(scan);
		assertEquals( nOfAccountsBefore + 1 , Database.getDatabase().getAccounts().size());
		assertEquals( nOfCustomersAccountBefore + 1, Database.getDatabase().getCustomer(customer.getId()).getAccountIds().size());	
		
	}
	
	@Test(timeout=500)
	/**
	 * Test for when customer creates an invalid account type
	 */
	public void testInvalidAccountType() {
		Customer customer = new Customer("username", "password");
		
		String s = "accountName\n"
				+ "save\n"
				+ "saving\n"; 		//account type	 
		
		Scanner scan = new Scanner(s);
		Database.getDatabase().addCustomer(customer);
		CreateAccountMenu menu = new CreateAccountMenu(customer);
		
		int nOfAccountsBefore = Database.getDatabase().getAccounts().size();
		int nOfCustomersAccountBefore = Database.getDatabase().getCustomer(customer.getId()).getAccountIds().size();
		menu.openMenu(scan);
		assertEquals( nOfAccountsBefore + 1 , Database.getDatabase().getAccounts().size());
		assertEquals( nOfCustomersAccountBefore + 1, Database.getDatabase().getCustomer(customer.getId()).getAccountIds().size());	
	}

}

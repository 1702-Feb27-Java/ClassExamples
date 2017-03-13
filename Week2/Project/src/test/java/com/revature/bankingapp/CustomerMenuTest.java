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
import com.revature.bankingapp.menu.CreateAccountMenu;
import com.revature.bankingapp.menu.CustomerMenu;
import com.revature.bankingapp.menu.IMenu;
import com.revature.bankingapp.menu.LoginMenu;

public class CustomerMenuTest {

	private static final String CUSTOMERS_FILENAME = "customers.txt";
	private static final String EMPLOYEES_FILENAME = "employees.txt";
	private static final String ACCOUNTS_FILENAME = "accounts.txt";
	
	User customer;
	User ocustomer;
	Account account;
	Account account2;
	Account account3;
	@Before
	public void setUp() throws Exception {
		
		customer = new User("newuser", "lastname", "customer", "password", Service.getInstance().getCustomerRole());
		ocustomer = new User("newuser", "lastname", "ocustomer", "password", Service.getInstance().getCustomerRole());
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
		
		
	}

	@Test
	/**
	 * Test that when the customer requests for a new account, it is redirected to the correct menu.
	 */
	public void testRequest () {
		
		IMenu menu = new CustomerMenu(customer);
		
		String s = "n\n";
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertTrue(menu instanceof CreateAccountMenu);
	}
	
	@Test
	public void testNotAccessAccount() {
		IMenu menu = new CustomerMenu(customer);
		int n = account3.getAccountId();
		
		//should fail to get account, and instead logout from second command
		String s = n + "\n" 	// account number
				+ "q\n"; 	// quit
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertTrue(menu instanceof LoginMenu);
	}
	
	@Test
	public void testAccessAccount() {
		IMenu menu = new CustomerMenu(customer);
		int n = account.getAccountId();
		//should go to the account with id 51 
		String s = n+"\n" 	// account number
				+ "q\n"; 	// quit
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertTrue(menu instanceof AccountMenu);
	}
	
	

}

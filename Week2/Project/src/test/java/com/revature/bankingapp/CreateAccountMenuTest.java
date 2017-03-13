package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;
import com.revature.bankingapp.menu.CreateAccountMenu;

public class CreateAccountMenuTest {
	private static final String CUSTOMERS_FILENAME = "customers.txt";
	private static final String EMPLOYEES_FILENAME = "employees.txt";
	private static final String ACCOUNTS_FILENAME = "accounts.txt";
	User user;
	List<Account> accountsToTearDown;
	
	@Before
	public void setUp() throws Exception {
		user = new User("firstname", "lastname", "createaccount","password", Service.getInstance().getUserRoles().get(1));
		user = Service.getInstance().saveAndReturnNewUser(user);
		accountsToTearDown = new LinkedList<>();
	}
	
	@After
	public void tearDown() throws Exception {
		Service.getInstance().deleteUser(user);
		accountsToTearDown.forEach(p -> Service.getInstance().deleteAccount(p));
	}
	

	
	@Test
	/**
	 * Test user creating a new account
	 */
	public void testCustomerMakingAccount() {
		
		String s = "account Name\n" 	//account name
				+ "checking\n"; 		//account type	 
		
		Scanner scan = new Scanner(s);

		CreateAccountMenu menu = new CreateAccountMenu(user);
		
		int nOfAccountsBefore = Dao.getInstance().getAccountFromUser(user).size();
		menu.openMenu(scan);
		assertEquals( nOfAccountsBefore + 1 , Dao.getInstance().getAccountFromUser(user).size());
		this.accountsToTearDown.addAll(Service.getInstance().getAccountFromUser(user));

	}
	
	@Test(timeout=2000)
	/**
	 * Test for when customer creates an invalid account name
	 */
	public void testInvalidAccountName() {
		String s = "account:as;djfans;gnj4Name\n" 	//account name
				+ "accountName\n"
				+ "checking\n"; 		//account type	 
		
		Scanner scan = new Scanner(s);
		CreateAccountMenu menu = new CreateAccountMenu(user);
		
		int nOfAccountsBefore = Service.getInstance().getAccountFromUser(user).size();
		menu.openMenu(scan);
		assertEquals( nOfAccountsBefore + 1 , Service.getInstance().getAccountFromUser(user).size());
		this.accountsToTearDown.addAll(Service.getInstance().getAccountFromUser(user));
	}
	
	@Test(timeout=500)
	/**
	 * Test for when customer creates an invalid account type
	 */
	public void testInvalidAccountType() {
		
		String s = "accountName\n"
				+ "save\n"
				+ "saving\n"; 		//account type	 
		
		Scanner scan = new Scanner(s);
		CreateAccountMenu menu = new CreateAccountMenu(user);
		
		int nOfAccountsBefore = Service.getInstance().getAccountFromUser(user).size();
		menu.openMenu(scan);
		assertEquals( nOfAccountsBefore + 1 , Service.getInstance().getAccountFromUser(user).size());
		
		//compares the account type
		assertTrue(Service.getInstance().getAccountFromUser(user).get(0).getAccountType() == Service.getInstance().getAccountTypes().get(2));
	
		this.accountsToTearDown.addAll(Service.getInstance().getAccountFromUser(user));
	}

}

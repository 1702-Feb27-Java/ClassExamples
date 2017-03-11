package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.menu.AccountMenu;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;
import com.revature.bankingapp.model.Employee;


public class AccountMenuTest {

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
	 * Test when an invalid option is given.
	 * Confirms to ensure nothing in the account has changed.
	 *
	 */
	public void testInvalidOption(){
		Account account = new Account("accountName", "checking", 100, true);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		String s = new String("pizza\n"
				+ "1234\n"
				+ "\n\n"
				+ "dude bro that is my car\n"
				+ "q\n");
		
		String accountName = account.getAccountName();
		String accountType = account.getAccountType();
		double balance = account.getBalance();
		boolean isApproved = account.isApproved();
		int id = account.getId();
		Scanner scan = new Scanner(s);
		
		menu.openMenu(scan);
		
		assertEquals(id, account.getId());
		assertEquals(accountName, account.getAccountName());
		assertEquals(accountType, account.getAccountType());
		assertEquals(balance, account.getBalance(), 0.0009);
		assertEquals(isApproved, account.isApproved());
	}
	
	@Test
	/**
	 * Test when an invalid number is given.
	 * Confirms to ensure nothing in the account has changed.
	 *
	 */
	public void testInvalidDeposit(){
		Account account = new Account("accountName", "checking", 100, true);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		String s = new String("d\n"
				+ "I like 50 dollars please\n"
				+ "\n"
				+ "0\n"
				+ "q\n");
		
		String accountName = account.getAccountName();
		String accountType = account.getAccountType();
		double balance = account.getBalance() + 0;
		boolean isApproved = account.isApproved();
		int id = account.getId();
		Scanner scan = new Scanner(s);
		
		menu.openMenu(scan);
		
		assertEquals(id, account.getId());
		assertEquals(accountName, account.getAccountName());
		assertEquals(accountType, account.getAccountType());
		assertEquals(balance, account.getBalance(), 0.0009);
		assertEquals(isApproved, account.isApproved());
	}
	
	

	@Test
	/**
	 * Test to confirm that a customer which does not have the approve account function
	 * can not approve an account. 
	 * 
	 */
	public void testCustomerApprovesNotApprovedAccount() {
		
		// creates account and user
		Account account = new Account("accountName", "checking", 100, false);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		// test input string
		String s = new String("a\n"
				+ "q\n");
		
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		assertEquals(false, account.isApproved());
		
	}
	
	@Test
	/**
	 * Test to confirm that a customer can not deposit from a non approved account.
	 */
	public void testCustomerDepositNotApprovedAccount() {
		
		// creates account and user
		Account account = new Account("accountName", "checking", 100, false);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		double bal = account.getBalance();
		
		// test input string
		String s = new String("d\n" //deposit 
				+ "250.50\n" //250.50 dollars
				+ "q\n"); // exit
		
		
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test to confirm that a customer can deposit from an approved account
	 */
	public void testCustomerDespostApprovedAccount() {
		
		// creates account and user
		Account account = new Account("accountName", "checking", 100, true);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		// test input string
		String s = new String("d\n"
				+ "250.50\n"
				+ "q\n");
		
		double bal = account.getBalance()+250.50;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}

	@Test
	/**
	 * Test to confirm that a customer cannot withdraw from a non approved account
	 */
	public void testCustomerWithdrawsNotApprovedAccount() {
		
		// creates account and user
		Account account = new Account("accountName", "checking", 100, false);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		// test input string
		String s = new String("w\n"
				+ "50\n"
				+ "q\n");
		
		double bal = account.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test to confirm that a customer can withdraw from an approved account
	 */
	public void testCustomerWithdrawApprovedAccount() {
		
		// creates account and user
		Account account = new Account("accountName", "checking", 100, true);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		// test input string
		String s = new String("w\n"
				+ "50\n"
				+ "q\n");
		
		double bal = account.getBalance()-50;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}

	@Test
	/**
	 * Test to confirm that a customer can withdraw all the money in an account
	 */
	public void testCustomerEmptyApprovedAccount() {
		
		// creates account and user
		Account account = new Account("accountName", "checking", 100, true);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		// test input string
		String s = new String("w\n"
				+ "100\n"
				+ "q\n");
		
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(0, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test to confirm that a customer can not Withdraw his account
	 */
	public void testCustomerOverdawApprovedAccount() {
		
		// creates account and user
		Account account = new Account("accountName", "checking", 100, true);
		Database.getDatabase().addAccount(account);
		Customer customer = new Customer("username", "password");
		customer.getAccountIds().add(account.getId());
		Database.getDatabase().addCustomer(customer);
		AccountMenu menu = new AccountMenu(customer, account, account.isApproved(), false);
		
		// test input string
		String s = new String("w\n"
				+ "101\n"
				+ "q\n");
		
		double bal = account.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test to confirm an employee can approve an account that was not apporved
	 */
	public void testEmployeeApproveAccount() {
		// creates account and user
		Account account = new Account("accountName", "checking", 0, false);
		Database.getDatabase().addAccount(account);
		
		Employee employee = new Employee("username", "password", false);
		Database.getDatabase().addEmployee(employee);
		AccountMenu menu = new AccountMenu(employee, account, employee.isAdmin(), true);
		
		// test input string
		String s = new String("a\n"
				+ "q\n");
		
		
		Scanner scan = new Scanner(s);
		
		assertEquals(false, account.isApproved());
		
		menu.openMenu(scan);
		
		assertEquals(true, account.isApproved());
	}

	@Test
	/**
	 * Test to confirm that an employee can not withdraw an account
	 */
	public void testEmployeeWithdrawAccount() {
		// creates account and user
		Account account = new Account("accountName", "checking", 0, false);
		Database.getDatabase().addAccount(account);
		Employee employee = new Employee("username", "password", false);
		Database.getDatabase().addEmployee(employee);
		AccountMenu menu = new AccountMenu(employee, account, employee.isAdmin(), true);
		
		// test input string
		String s = new String("w\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}
	@Test
	/**
	 * Test to confirm that an employee can not deposit into an account
	 */
	public void testEmployeeDepsoitAccount() {
		// creates account and user
		Account account = new Account("accountName", "checking", 0, false);
		Database.getDatabase().addAccount(account);
		Employee employee = new Employee("username", "password", false);
		Database.getDatabase().addEmployee(employee);
		AccountMenu menu = new AccountMenu(employee, account, employee.isAdmin(), true);
		
		// test input string
		String s = new String("d\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test that an admin can deposit into an account
	 */
	public void testAdminDepsoitAccount(){
		// creates account and user
		Account account = new Account("accountName", "checking", 0, false);
		Database.getDatabase().addAccount(account);
		Employee employee = new Employee("username", "password", true);
		Database.getDatabase().addEmployee(employee);
		AccountMenu menu = new AccountMenu(employee, account, employee.isAdmin(), true);
		
		// test input string
		String s = new String("d\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance()+100;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test that an admin can withdraw into an account
	 */
	public void testAdminWithdrawAccount(){
		// creates account and user
		Account account = new Account("accountName", "checking", 100, false);
		Database.getDatabase().addAccount(account);
		Employee employee = new Employee("username", "password", true);
		Database.getDatabase().addEmployee(employee);
		AccountMenu menu = new AccountMenu(employee, account, employee.isAdmin(), true);
		
		// test input string
		String s = new String("w\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance()-100;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(bal, account.getBalance(), 0.009);
	}

	@Test
	/**
	 * Test that an admin can approve an account
	 */
	public void testAdminApproveAccount(){
		// creates account and user
		Account account = new Account("accountName", "checking", 100, false);
		Database.getDatabase().addAccount(account);
		Employee employee = new Employee("username", "password", true);
		Database.getDatabase().addEmployee(employee);
		AccountMenu menu = new AccountMenu(employee, account, employee.isAdmin(), true);
		
		// test input string
		String s = new String("a\n"
				+ "q\n");
		
		
		assertEquals(false, account.isApproved());
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		assertEquals(true, account.isApproved());
	}
	
}

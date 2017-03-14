package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.AccountStatus;
import com.revature.bankingapp.database.model.AccountType;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;
import com.revature.bankingapp.menu.AccountMenu;


public class AccountMenuTest {

	private static final String CUSTOMERS_FILENAME = "customers.txt";
	User customer;
	User employee;
	User admin;
	Account account;
	Account approvedAccount;
	Account rejectedAccount;
	@Before
	public void setUp() throws Exception {
		customer = new User("firstname", "lastname", "aamt", "password", Service.getInstance().getCustomerRole());
		employee = new User("firstname", "lastname", "eamt", "password", Service.getInstance().getEmployeeRole());
		admin = new User("firstname", "lastname", "adamt", "password", Service.getInstance().getAdminRole());
		
		customer = Service.getInstance().saveAndReturnNewUser(customer);
		employee = Service.getInstance().saveAndReturnNewUser(employee);
		admin = Service.getInstance().saveAndReturnNewUser(admin);
		
		account = new Account("pending", Service.getInstance().getCheckingAccountType());
		approvedAccount = new Account("approved", Service.getInstance().getCheckingAccountType());
		rejectedAccount = new Account("rejected", Service.getInstance().getCheckingAccountType());
	
		Service.getInstance().createNewAccount(customer, account);
		Service.getInstance().createNewAccount(customer, approvedAccount);
		Service.getInstance().createNewAccount(customer, rejectedAccount);
		
		List<Account> accounts = Service.getInstance().getAccountFromUser(customer);
		for (Account account : accounts){
			if (account.getAccountName().equals("pending")){
				account.setStatus(Service.getInstance().getPendingStatus());
			} else if (account.getAccountName().equals("approved")){
				account.setStatus(Service.getInstance().getApprovedStatus());
			} else if (account.getAccountName().equals("rejected")){
				account.setStatus(Service.getInstance().getRejectedStatus());
			}
		}
		

		accounts.forEach(p->Service.getInstance().updateAccount(p));
		
		accounts = Service.getInstance().getAccountFromUser(customer);
		for (Account a : accounts){
			if (a.getAccountName().equals("pending")){
				account = a;
			} else if (a.getAccountName().equals("approved")){
				approvedAccount = a;
			} else if (a.getAccountName().equals("rejected")){
				rejectedAccount = a;
			}
		}
		
		//money in account
		account.setBalance(100);
		approvedAccount.setBalance(100);
		
		account = Service.getInstance().updateAndReturnAccount(account);
		approvedAccount = Service.getInstance().updateAndReturnAccount(approvedAccount);
		
	}
	
	@After 
	public void tearDown() throws Exception {
		Service.getInstance().deleteUser(customer);
		Service.getInstance().deleteUser(employee);
		Service.getInstance().deleteUser(admin);
		Service.getInstance().deleteAccount(account);
		Service.getInstance().deleteAccount(approvedAccount);
		Service.getInstance().deleteAccount(rejectedAccount);
	}
	
	@Test
	/**
	 * Test when an invalid option is given.
	 * Confirms to ensure nothing in the account has changed.
	 *
	 */
	public void testInvalidOption(){

		AccountMenu menu = new AccountMenu(customer, account);
		int id = account.getAccountId();
		String  accountName = account.getAccountName();
		AccountType accountType = account.getAccountType();
		double balance = account.getBalance();
		AccountStatus status = account.getStatus();
		String s = new String("pizza\n"
				+ "1234\n"
				+ "\n\n"
				+ "dude bro that is my car\n"
				+ "q\n");
		
		Scanner scan = new Scanner(s);
		
		menu.openMenu(scan);
		
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(id, account.getAccountId().intValue());
		assertEquals(accountName, account.getAccountName());
		assertEquals(accountType, account.getAccountType());
		assertEquals(balance, account.getBalance(), 0.0009);
		assertEquals(status, account.getStatus());
	}
	
	@Test
	/**
	 * Test when an invalid number is given.
	 * Confirms to ensure nothing in the account has changed.
	 *
	 */
	public void testInvalidDeposit(){
		AccountMenu menu = new AccountMenu(customer, account);
		int id = account.getAccountId();
		String  accountName = account.getAccountName();
		AccountType accountType = account.getAccountType();
		double balance = account.getBalance();
		AccountStatus status = account.getStatus();
		
		String s = new String("d\n"
				+ "I like 50 dollars please\n"
				+ "\n"
				+ "0\n"
				+ "q\n");
		
		Scanner scan = new Scanner(s);
		
		
		menu.openMenu(scan);
		
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(id, account.getAccountId().intValue());
		assertEquals(accountName, account.getAccountName());
		assertEquals(accountType, account.getAccountType());
		assertEquals(balance, account.getBalance(), 0.0009);
		assertEquals(status, account.getStatus());	}
	
	

	@Test
	/**
	 * Test to confirm that a customer which does not have the approve account function
	 * can not approve an account. 
	 * 
	 */
	public void testCustomerApprovesNotApprovedAccount() {
		
		// creates account and user
		AccountMenu menu = new AccountMenu(customer, account);
		
		// test input string
		String s = new String("a\n"
				+ "q\n");
		
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(account.getStatus(), Service.getInstance().getPendingStatus() );
		
	}
	
	@Test
	/**
	 * Test to confirm that a customer can not deposit from a non approved account.
	 */
	public void testCustomerDepositNotApprovedAccount() {
		
		// creates account and user
		AccountMenu menu = new AccountMenu(customer, account);
		
		double bal = account.getBalance();
		
		// test input string
		String s = new String("d\n" //deposit 
				+ "250.50\n" //250.50 dollars
				+ "q\n"); // exit
		
		
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(bal, account.getBalance(), 0.009);
		
	}
	
	@Test
	/**
	 * Test to confirm that a customer can deposit from an approved account
	 */
	public void testCustomerDespostApprovedAccount() {
		
		// creates account and user

		AccountMenu menu = new AccountMenu(customer, this.approvedAccount);
		
		// test input string
		String s = new String("d\n"
				+ "250.50\n"
				+ "q\n");
		
		double bal = approvedAccount.getBalance()+250.50;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		approvedAccount = Service.getInstance().getAccount(approvedAccount.getAccountId());
		assertEquals(bal, approvedAccount.getBalance(), 0.009);
	}

	@Test
	/**
	 * Test to confirm that a customer cannot withdraw from a non approved account
	 */
	public void testCustomerWithdrawsNotApprovedAccount() {
		
		// creates account and user

		AccountMenu menu = new AccountMenu(customer, account);
		
		// test input string
		String s = new String("w\n"
				+ "50\n"
				+ "q\n");
		
		double bal = account.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test to confirm that a customer can withdraw from an approved account
	 */
	public void testCustomerWithdrawApprovedAccount() {
		


		AccountMenu menu = new AccountMenu(customer, approvedAccount);
		
		// test input string
		String s = new String("w\n"
				+ "50\n"
				+ "q\n");
		
		double bal = approvedAccount.getBalance()-50;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		approvedAccount = Service.getInstance().getAccount(approvedAccount.getAccountId());
		assertEquals(bal, approvedAccount.getBalance(), 0.009);
	}

	@Test
	/**
	 * Test to confirm that a customer can withdraw all the money in an account
	 */
	public void testCustomerEmptyApprovedAccount() {
		

		AccountMenu menu = new AccountMenu(customer, approvedAccount);
		
		// test input string
		String s = new String("w\n"
				+ "100\n"
				+ "q\n");
		
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		approvedAccount = Service.getInstance().getAccount(approvedAccount.getAccountId());
		assertEquals(0, approvedAccount.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test to confirm that a customer can not OverWithdraw his account
	 */
	public void testCustomerOverdawApprovedAccount() {
		
		// creates account and user
		AccountMenu menu = new AccountMenu(customer, approvedAccount);
		
		// test input string
		String s = new String("w\n"
				+ "101\n"
				+ "q\n");
		
		double bal = approvedAccount.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		
		
		approvedAccount = Service.getInstance().getAccount(approvedAccount.getAccountId());
		assertEquals(bal, approvedAccount.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test to confirm an employee can approve an account that was not apporved
	 */
	public void testEmployeeApproveAccount() {

		AccountMenu menu = new AccountMenu(employee, account);
		
		// test input string
		String s = new String("a\n"
				+ "q\n");
		
		
		Scanner scan = new Scanner(s);
		
		assertEquals(account.getStatus(), Service.getInstance().getPendingStatus());
		
		menu.openMenu(scan);
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(account.getStatus(), Service.getInstance().getApprovedStatus());
	}

	@Test
	/**
	 * Test to confirm that an employee can not withdraw an account
	 */
	public void testEmployeeWithdrawAccount() {

		AccountMenu menu = new AccountMenu(employee, account);
		
		// test input string
		String s = new String("w\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(bal, account.getBalance(), 0.009);
	}
	@Test
	/**
	 * Test to confirm that an employee can not deposit into an account
	 */
	public void testEmployeeDepsoitAccount() {
		AccountMenu menu = new AccountMenu(employee, account);
		
		// test input string
		String s = new String("d\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance();
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test that an admin can deposit into an account
	 */
	public void testAdminDepsoitAccount(){
		AccountMenu menu = new AccountMenu(admin, account);
		
		// test input string
		String s = new String("d\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance()+100;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(bal, account.getBalance(), 0.009);
	}
	
	@Test
	/**
	 * Test that an admin can withdraw into an account
	 */
	public void testAdminWithdrawAccount(){
		AccountMenu menu = new AccountMenu(admin, account);
		
		// test input string
		String s = new String("w\n"
				+ "100.00\n"
				+ "q\n");
		
		double bal = account.getBalance()-100;
		
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(bal, account.getBalance(), 0.009);
	}

	@Test
	/**
	 * Test that an admin can approve an account
	 */
	public void testAdminApproveAccount(){
		AccountMenu menu = new AccountMenu(admin, account);
		
		// test input string
		String s = new String("a\n"
				+ "q\n");
		
		
		assertEquals(account.getStatus(), Service.getInstance().getPendingStatus());
		Scanner scan = new Scanner(s);
		menu.openMenu(scan);
		account = Service.getInstance().getAccount(account.getAccountId());
		assertEquals(account.getStatus(), Service.getInstance().getApprovedStatus());
	}
	
}

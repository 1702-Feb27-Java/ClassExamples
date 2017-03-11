package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;
import com.revature.bankingapp.model.Employee;

public class DatabaseTest {

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
	 * tests to ensure that employees can be added and removed from database
	 */
	public void testEmployees() {
		
		Employee employee = new Employee("employee", "password", false);
		
		assertEquals(null, Database.getDatabase().getEmployeeByUsername("employee"));
		
		Database.getDatabase().addEmployee(employee);
		
		assertNotNull(Database.getDatabase().getEmployeeByUsername("employee"));
		assertEquals(employee, Database.getDatabase().getEmployeeByUsername("employee"));
	}
	
	@Test
	/**
	 * tests to ensure that employees can be added and removed from database
	 */
	public void testCustomer() {
		
		Customer customer = new Customer("customer", "password");
		
		assertEquals(null, Database.getDatabase().getCustomerByUsername("customer"));
		
		Database.getDatabase().addCustomer(customer);
		
		assertNotNull(Database.getDatabase().getCustomerByUsername("customer"));
		assertEquals(customer, Database.getDatabase().getCustomerByUsername("customer"));
	}
	
	@Test
	/**
	 * tests to ensure that employees can be added and removed from database
	 */
	public void testAccount() {
		
		Account account = new Account("account", "checking", 0.0, false);
		

		Database.getDatabase().addAccount(account);
		
		assertNotNull(Database.getDatabase().getAccount(account.getId()));
		assertEquals(account, Database.getDatabase().getAccount(account.getId()));
	}

}

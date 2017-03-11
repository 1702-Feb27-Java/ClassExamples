package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.menu.CustomerMenu;
import com.revature.bankingapp.menu.EmployeeMenu;
import com.revature.bankingapp.menu.IMenu;
import com.revature.bankingapp.menu.LoginMenu;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Database;
import com.revature.bankingapp.model.Employee;

public class LoginMenuTest {

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
	public void testLoginCustomer() {
		Customer customer = new Customer("username", "password");
		Database.getDatabase().addCustomer(customer);
		
		String s = "1\n" //selects to login
				+ "username\n" //type in username
				+ "password\n"; //type in password
		IMenu menu = new LoginMenu();
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertEquals(CustomerMenu.class, menu.getClass());

		
	}
	
	@Test
	public void testLoginEmployee() {
		Employee employee = new Employee("usernameABC", "password", false);
		Database.getDatabase().addEmployee(employee);
		
		String s = "1\n" //selects to login
				+ "usernameABC\n" //type in username
				+ "password\n"; //type in password
		IMenu menu = new LoginMenu();
		Scanner scan = new Scanner(s);
		menu = menu.openMenu(scan);
		
		assertEquals(EmployeeMenu.class, menu.getClass());
	}
	
	@Test
	public void registerNewCustomer(){

	
		String s = "2\n" //register new user option
				+ "newUserName\n" // give username
				+ "password123\n" // password
				+ "password123\n"; //confirm password
		
		Customer customer = Database.getDatabase().getCustomerByUsername("newUserName");
		Scanner scan = new Scanner(s);
		assertEquals(null, customer);
		IMenu menu = new LoginMenu();
		menu = menu.openMenu(scan);
		customer = Database.getDatabase().getCustomerByUsername("newUserName");
		assertNotEquals(null, customer);
		
		assertEquals(CustomerMenu.class, menu.getClass());
		
		
		
	}
	
	@Test(timeout=500)
	/**
	 * test to see if user tries to create a new user with a name already existing
	 */
	public void duplicateCustomer(){
		//set up user to attempted to be duplicated
		Employee employeer = new Employee("popularusername", "password", false);
		Database.getDatabase().addEmployee(employeer);
		
		
		String s = "2\n"  //register new user
				+ "popularusername\n" //'attempt' to create duplicate username
				+ "lesspopularusername\n" //different account name
				+ "password\n" //password
				+ "password\n"; //confirm password
				
		Scanner scan = new Scanner(s);
		IMenu menu = new LoginMenu();
		
		//user did not exist
		assertTrue(Database.getDatabase().containsUsername("popularusername"));
		assertFalse(Database.getDatabase().containsUsername("lesspopularusername"));
		
		
		menu = menu.openMenu(scan);
		
		//user now exists as different name
		assertTrue(Database.getDatabase().containsUsername("popularusername"));
		assertTrue(Database.getDatabase().containsUsername("lesspopularusername"));
			
	}

}

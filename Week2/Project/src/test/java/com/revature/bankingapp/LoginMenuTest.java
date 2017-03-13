package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;
import com.revature.bankingapp.menu.CustomerMenu;
import com.revature.bankingapp.menu.EmployeeMenu;
import com.revature.bankingapp.menu.IMenu;
import com.revature.bankingapp.menu.LoginMenu;

public class LoginMenuTest {

	User existingUser;
	User existingEmployee;
	
	
	@Before
	public void setUp() throws Exception {
		existingUser = new User("firstname", "second", "username", "password", Service.getInstance().getCustomerRole());
		existingEmployee = new User("firstname", "last", "employee", "password", Service.getInstance().getEmployeeRole());
		
		existingUser = Service.getInstance().saveAndReturnNewUser(existingUser);
		existingEmployee = Service.getInstance().saveAndReturnNewUser(existingEmployee);
	}
	
	@After
	public void tearDown() throws Exception{
		Service.getInstance().deleteUser(existingUser);
		Service.getInstance().deleteUser(existingEmployee);
	}


	@Test
	public void testLoginCustomer() {
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
		String s = "1\n" //selects to login
				+ "employee\n" //type in username
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
		
		Scanner scan = new Scanner(s);
		IMenu menu = new LoginMenu();
		menu = menu.openMenu(scan);
		User u = Service.getInstance().getUser("newUserName");
		
		assertNotNull(u);
		assertEquals(CustomerMenu.class, menu.getClass());
		Service.getInstance().deleteUser(u);
		
		
	}
	
	@Test(timeout=5000)
	/**
	 * test to see if user tries to create a new user with a name already existing
	 */
	public void duplicateCustomer(){
		String s = "2\n"  //register new user
				+ "username\n" //'attempt' to create duplicate username
				+ "newUserName\n" //different account name
				+ "password\n" //password
				+ "password\n"; //confirm password
				
		Scanner scan = new Scanner(s);
		IMenu menu = new LoginMenu();
				
		menu = menu.openMenu(scan);
		User u = Service.getInstance().getUser("newUserName");
		//user now exists as different name
		assertNotNull(u);
		Service.getInstance().deleteUser(u);
			
	}

}

package com.revature.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuClassTest {
	
	MenuClass mc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mc = new MenuClass();
	}

	@After
	public void tearDown() throws Exception {
		mc = null;
	}

	@Test
	public void testShowMainMenu() {
		System.out.println("Testing the MenuClass.java now.");
		System.out.println("Expected to print out the main menu.");
		
		mc.showMainMenu(); 
		//but i guess this doesn't even do anything
		
	}
	
	@Test
	public void testShowLogInMenu(){
		mc.showLogInMenu();
	}

}

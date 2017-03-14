//package com.revature.testing;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.revature.banking.Account;
//import com.revature.banking.Customer;
////import com.revature.banking.Database;
//import com.revature.banking.Employee;
//
//public class Tests {
//	
//	@Before
	// retrieve database instance and then
	// reset Database fields since database is a singleton?
	// how would i do that??
	
//	@Test
//	public void accountTest() {
//		Account a1 = new Account(123, "account name 1", "account type 1");
//		Account a2 = new Account(1234, "account name 2", "account type 2", 50, true);
//		assertEquals(123, a1.getAccountId());
//		assertEquals(1234, a2.getAccountId());
//		assertEquals("account name 1", a1.getAccountName());
//		assertEquals("account type 1", a1.getAccountType());
//		assertEquals("account name 2", a2.getAccountName());
//		assertEquals("account type 2", a2.getAccountType());
//		assertEquals(false, a1.isApproved());
//		assertEquals(true, a2.isApproved());
//		System.out.println(a2);
//		a2.prettyPrintBasic();
//		a2.prettyPrint();
//	}
//	
//	@Test
//	public void customerTest() {
//		Customer c = new Customer(1, "username", "password");
//		assertEquals(1, c.getId());
//		assertEquals("username", c.getUsername());
//		assertEquals("password", c.getPassword());
//		c.addAccountById(123);
//		c.prettyPrint();
//		System.out.println(c);
//	}
//	
//	@Test
//	public void employeeTest() {
//		Employee e = new Employee(10, "bob", "bobspw");
//		assertEquals(10, e.getId());
//		assertEquals("bob", e.getUsername());
//		assertEquals("bobspw", e.getPassword());
//		System.out.println(e);
//	}
//	
//	@Test
//	public void databaseTest() {
//		// would test database
//	}
//
//}

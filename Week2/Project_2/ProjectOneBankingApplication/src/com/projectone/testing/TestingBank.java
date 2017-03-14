package com.projectone.testing;

import static org.junit.Assert.*;
import com.projectone.testing.*;
import com.projectone.*;
import org.junit.Test;

public class TestingBank {
private Bank b;
private Customer c;
private Employee e;
private Admin a;
	
public TestingBank()
	{
		c = new Customer("David","Lund",122,"DL001","Testing",false,"checking");
		e = new Employee("David","Lund","DL001","Testing");
		a = new Admin("David","Lund,DL001,Testing");
		
		}
	@Test

	public void testAdminGetFirst()
	{
		breakLine();
		System.out.println("Creating New Admin ");
		b = new Bank(a);
		try
		{

		    System.out.println("Testing \nGet Admin User Name is : "+ b.getAdmin().getFirst());
		}
		catch(Exception e)
		{
			
			fail("Fail To Get First Name");
		}
		breakLine();
		
		
	}

	@Test

	public void testAdminGetPassword()
	{
		breakLine();
		System.out.println("Creating New Admin ");
		b = new Bank(a);
		try
		{
			
		    System.out.println("Testing \nGet Admin Password Name is : "+ b.getAdmin().getsPassword());
		}
		catch(Exception e)
		{
			
			fail("Fail To Get Password");
		}
		breakLine();
		
				
	}
	@Test

	public void testAdminGetLast()
	{
		
		breakLine();
		System.out.println("New Customer Declared Get First to true method");
		b = new Bank(a);
		try
		{
			System.out.println("Testing \nGet Last Name before change  : "+ a.getLast());
			
		}
		catch(Exception e)
		{
			
			fail("To Get Name");
		}
		breakLine();
		
		
	}
	@Test

	public void testAdminSetUserName()
	{
		breakLine();
		System.out.println("Creating New Admin ");
		b = new Bank(a);
		try
		{
			   System.out.println("Testing \nBefore Set Admin Username is now : "+ b.getAdmin().getsUserName());
					
			b.getAdmin().setsUserName("Joe123");
		    System.out.println("Testing \nAfter Set Admin Username is : "+ b.getAdmin().getsUserName());
		}
		catch(Exception e)
		{
			
			fail("Fail To Get Password");
		}
		breakLine();
				
		
	}
		
	public void test() {
		breakLine();
		
	}
	
	
	
	
	@Test

	public void testAdminSetFirst()
	{
		breakLine();
		System.out.println("Creating New Admin ");
		b = new Bank(a);
		try
		{
			System.out.println("Before Setting Admin First Name " + b.getAdmin().getFirst());
			b.getAdmin().setFirst("Anne");
		    System.out.println("Testing \nGet Admin User Name is : "+ b.getAdmin().getFirst());
		}
		catch(Exception e)
		{
			
			fail("Fail To Set First Name");
		}
		breakLine();
		
		
	}

	@Test

	public void testAdminSetPassword()
	{
		breakLine();
		System.out.println("Creating New Admin ");
		b = new Bank(a);
		try
		{

		    System.out.println("Befor Set Admin Password Name is : "+ b.getAdmin().getsPassword());
		    b.getAdmin().getsPassword();
		    System.out.println("Testing \nGet Admin Password Name is : "+ b.getAdmin().getsPassword());
		}
		catch(Exception e)
		{
			
			fail("Fail To Set Password");
		}
		breakLine();
		
				
	}
	@Test

	public void testAdminSetLast()
	{
		
		breakLine();
		System.out.println("New Customer Declared Get First to true method");
		b = new Bank(a);
		try
		{
			System.out.println("Testing \nGet Last Name before change  : "+ a.getLast());
			
		}
		catch(Exception e)
		{
			
			fail("To Get Name");
		}
		breakLine();
		
		
	}
	@Test

	public void testAdminGetUserName()
	{
		breakLine();
		System.out.println("Creating New Admin ");
		b = new Bank(a);
		try
		{
			
		    System.out.println("Testing \nGet Admin UserName is : "+ b.getAdmin().getsUserName());
		}
		catch(Exception e)
		{
			
			fail("Fail To Get Password");
		}
		breakLine();
				
		
	}
	
	
	@Test
	public void testGetCustomerFirst()
	{
	breakLine();
	System.out.println("New Customer Declared Get First to true method");
	b = new Bank(c,222);
	try
	{
		System.out.println("Testing \nSet First Name before change  : "+ b.getCustomer().getsFName());
		

	System.out.println("Testing \nSet First Name is now  : "+ b.getCustomer().getsFName());
	}
	catch(Exception e)
	{
		
		fail("To Get Name");
	}
	breakLine();
		
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testWithdraw()
	{
		breakLine();
		System.out.println("New Customer Declared Testing withdraw method");
		b = new Bank(c,222);
	System.out.println("Testing : Before Withdraw \nBalance : "+ b.getMoney());
	
	try
	{
	b.withdraw(40);	System.out.println("Testing : After $40 Withdraw \nBalance : "+ b.getMoney());
	
	//assertEquals(" Testing : Before Withdraw \nBalance : ",182.0,b.getMoney());

	}
	catch(Exception e)
	{
		
		fail("Failed to Withdraw");
	}
	breakLine();
	}
	@Test
	public void testDeposit()
	{breakLine();
		System.out.println("New Customer Declared Testing deposit method");
		b = new Bank(c,222);
		System.out.println("Testing : Before Deposit \nBalance : "+ b.getMoney());
		try
		{
		b.deposit(22);
		System.out.println("Testing : After $22 Deposit \nBalance : "+ b.getMoney());
		
		}
		catch(Exception e)
		{
			
			fail("Failed to Deposit");
		}
		breakLine();
		
	}
	
	@Test
	public void testGetMoney()
	{
		breakLine();
		System.out.println("New Customer Declared get money method");
		b = new Bank(c,222);
		try
		{
		b.deposit(22);
		System.out.println("Testing \nBalance "+ b.getMoney());
		}
		catch(Exception e)
		{
			
			fail("Failed to get Money Amount");
		}
		breakLine();
	}
	@Test
	public void testGetAccountStatus()
	{
		breakLine();
		System.out.println("New Customer Declared Account Status method");
		b = new Bank(a);
		
		try
		{
		
		System.out.println("Testing \nGet Account Status : "+ b.getCustomer().issApproved());
		}
		catch(Exception e)
		{
			
			fail("Failed to Get Account Status");
		}
		breakLine();
	}
	
	
	@Test
	public void testSetAccountStatus()
	{
		breakLine();
		System.out.println("New Customer Declared Set Account Status to true method");
		b = new Bank(c,222);
		try
		{
		b.getCustomer().setsApproved(true);
		System.out.println("Testing \nSet Account Status : "+ b.getCustomer().issApproved());
		}
		catch(Exception e)
		{
			
			fail("Failed to get accountStatus ");
		}
		breakLine();
	}
	@Test
	public void testSetCustomerFirst()
	{
		breakLine();
		System.out.println("New Customer Declared Set First to true method");
		b = new Bank(a);
		try
		{
			System.out.println("Testing \nSet First Name before change  : "+ b.getCustomer().getsFName());
			
		b.getCustomer().setsFName("Tom");
		System.out.println("Testing \nSet First Name is now  : "+ b.getCustomer().getsFName());
		}
		catch(Exception e)
		{
			
			fail("To Get Name");
		}
		breakLine();
	}
	@Test
	public void testSetCustomerLast()
	{
		breakLine();
		System.out.println("New Customer Declared Set Last to true method");
		b = new Bank(a);
		try
		{
			System.out.println("Testing \nSet Last Name before change  : "+ b.getCustomer().getsLName());
			
		b.getCustomer().setsLName("Tom");
		System.out.println("Testing \nSet First Name is now  : "+ b.getCustomer().getsLName());
		}
		catch(Exception e)
		{
			
			fail("To Get Name");
		}
		breakLine();
	}
	
	
	
	
	
	public void breakLine()
	{
		
		System.out.println("----------------------------");
	}
	
	
	

}

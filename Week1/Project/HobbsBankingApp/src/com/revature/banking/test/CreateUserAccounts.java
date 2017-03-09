package com.revature.banking.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.banking.account.user.*;

public class CreateUserAccounts {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		////////////////////////////////////////////////////////////////////////
		// WRITE
		
		// customers:
		ArrayList<CustomerUserAccount> a = new ArrayList<>();
		a.add(new CustomerUserAccount("A Customer", "customer", "customer@revature.com"));
		a.get(0).openNewAccount();
		a.get(0).openCheckingAccount(0);
		a.add(new CustomerUserAccount("Sally Mally", "flowerpower", "sally@malley.com"));
		a.add(new CustomerUserAccount("Ace Show", "aceshow", "ace@show.com"));
		a.add(new CustomerUserAccount("Joe Plumber", "2008", "joe@plumbersonly.com"));
		
		ObjectOutputStream oa = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
		oa.writeObject(a);
		oa.flush();
		oa.close();
		
		// employees:
		ArrayList<EmployeeUserAccount> b = new ArrayList<>();
		b.add(new EmployeeUserAccount("An Employee", "employee", "employee@revature.com"));
		b.add(new EmployeeUserAccount("Mickey Mouse", "disney", "mickey@disney.com"));
		b.add(new EmployeeUserAccount("Bruce Wayne", "batman", "bruce@wayneindustries.com"));
		b.add(new EmployeeUserAccount("Grubber", "bigmac", "mc@grubber.com"));
		
		ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("accounts_employee.txt"));
		ob.writeObject(b);
		ob.flush();
		ob.close();
		
		// admins:
		ArrayList<AdminUserAccount> c = new ArrayList<>();
		c.add(new AdminUserAccount("An Admin", "admin", "admin@revature.com"));
		
		ObjectOutputStream oc = new ObjectOutputStream(new FileOutputStream("accounts_admin.txt"));
		oc.writeObject(c);
		oc.flush();
		oc.close();
		
		////////////////////////////////////////////////////////////////////////
		// READ
		
		// customers:
		System.out.println("CUSTOMERS:");
		ObjectInputStream ia = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
		ArrayList<CustomerUserAccount> ai = (ArrayList<CustomerUserAccount>)ia.readObject();
		System.out.println(ai);
		ia.close();
		
		// employees:
		System.out.println("EMPLOYEES:");
		ObjectInputStream ib = new ObjectInputStream(new FileInputStream("accounts_employee.txt"));
		ArrayList<EmployeeUserAccount> bi = (ArrayList<EmployeeUserAccount>)ib.readObject();
		System.out.println(bi);
		ib.close();
		
		// admins:
		System.out.println("ADMINS:");
		ObjectInputStream ic = new ObjectInputStream(new FileInputStream("accounts_admin.txt"));
		ArrayList<EmployeeUserAccount> ci = (ArrayList<EmployeeUserAccount>)ic.readObject();
		System.out.println(ci);
		ic.close();
	}

}

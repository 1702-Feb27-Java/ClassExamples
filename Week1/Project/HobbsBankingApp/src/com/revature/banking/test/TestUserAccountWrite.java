package com.revature.banking.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.banking.account.user.*;

public class TestUserAccountWrite {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<CustomerUserAccount> a = new ArrayList<>();
		a.add(new CustomerUserAccount("Aoeu htns", "gcrlgcrl", "aoeu@htns.com"));
		a.add(new CustomerUserAccount("Gcrl Aoeu", "aoeuhtns", "gcrl@aoeu.com"));
		a.add(new CustomerUserAccount("Mao Guh", "mrguh", "mao@cnn.com"));
		
		ObjectOutputStream oa = new ObjectOutputStream(new FileOutputStream("accounts_customer.txt"));
		oa.writeObject(a);
		oa.flush();
		oa.close();
		
		ArrayList<EmployeeUserAccount> b = new ArrayList<>();		
		b.add(new EmployeeUserAccount("Mickey Mouse", "disney", "aoeu@htns.com"));
		b.add(new EmployeeUserAccount("Gcrl Aoeu", "aoeuhtns", "gcrl@aoeu.com"));
		b.add(new EmployeeUserAccount("Grubber", "mrguh", "mc@grubber.com"));
		
		ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("accounts_employee.txt"));
		ob.writeObject(b);
		ob.flush();
		ob.close();
		
		/*
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("accounts_customer.txt"));
		a = (ArrayList<CustomerUserAccount>)i.readObject();
		System.out.println(a);
		i.close();
		*/
	}

}

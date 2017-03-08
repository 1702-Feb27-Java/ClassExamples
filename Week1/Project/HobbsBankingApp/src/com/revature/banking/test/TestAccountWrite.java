package com.revature.banking.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.banking.account.Account;
import com.revature.banking.account.BankAccount;
import com.revature.banking.account.user.CustomerUserAccount;

public class TestAccountWrite {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Account> a = new ArrayList<>();
		
		a.add(new Account());
		a.add(new Account());
		a.add(new Account());
		
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("bank_accounts.txt"));
		
		o.writeObject(a);
		o.flush();
		
		o.close();
		
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("bank_accounts.txt"));
		a = (ArrayList<Account>)i.readObject();
		System.out.println(a);
		i.close();
	}

}

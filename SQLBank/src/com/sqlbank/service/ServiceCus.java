package com.sqlbank.service;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Customer;

public interface ServiceCus extends Service
{
	public void viewAccount(Customer c);
	public ArrayList<Account> getValidAccounts(Customer c);
	public ArrayList<Account> accounts (Customer c);
	public boolean updateBalance (Account a);
	public boolean applyAccount(Customer c, int type);
	
	
}

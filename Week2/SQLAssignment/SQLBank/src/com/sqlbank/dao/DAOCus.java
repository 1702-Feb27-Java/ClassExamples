package com.sqlbank.dao;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Customer;

public interface DAOCus
{
	public ArrayList<Account> viewCustomer(Customer c);
	public boolean updateAccount(Account a);
}

package com.sqlbank.service;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Customer;

public interface ServiceEmp extends Service
{
	public ArrayList<Customer> viewMyCustomers(String name);
	public ArrayList<Customer> getPending();
	public boolean updateAccount (int aid, int resolver, int type);

}

package com.sqlbank.dao;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Customer;

public interface DAOEmp extends DAO
{
	public ArrayList<Customer> viewMyCustomers(String name);
	public ArrayList<Customer> getPending();
	public boolean updateAccount (int aid, int resolver, int type);


}

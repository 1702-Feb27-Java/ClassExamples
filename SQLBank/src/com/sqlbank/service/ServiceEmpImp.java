package com.sqlbank.service;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.dao.DAOEmpImp;
import com.sqlbank.peoplepack.Customer;

public class ServiceEmpImp extends ServiceCusImp implements ServiceEmp
{
	static DAOEmpImp emDao = new DAOEmpImp();

	@Override
	public ArrayList<Customer> viewMyCustomers(String name)
	{
		return emDao.viewMyCustomers(name);
	}

	@Override
	public ArrayList<Customer> getPending()
	{
		return emDao.getPending();
	}
	
	public boolean updateAccount (int aid, int resolver, int type)
	{
		return emDao.updateAccount(aid, resolver, type);
	}
}

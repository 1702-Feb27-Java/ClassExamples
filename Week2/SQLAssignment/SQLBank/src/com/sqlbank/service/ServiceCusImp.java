package com.sqlbank.service;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.dao.DAOCusImp;
import com.sqlbank.peoplepack.Customer;

public class ServiceCusImp extends ServiceImplementation implements ServiceCus
{
	static DAOCusImp cusDao = new DAOCusImp();
	@Override
	public void viewAccount(Customer c)
	{
		ArrayList<Account> accs = cusDao.viewCustomer(c);
		if(accs.isEmpty() || accs == null)
		{
			c.printAccount();
			System.out.println("NOTHING TO VIEW FOR ACCOUNTS");
		}
		else
		{
			c.printAccount();
			for (Account x: accs)
			{
				x.printAccount();
			}
		}
	}
	@Override
	public ArrayList<Account> accounts(Customer c)
	{
		return cusDao.viewCustomer(c);
	}
	@Override
	public boolean updateBalance(Account a)
	{
		return cusDao.updateAccountBalance(a);
	}
	public boolean applyAccount(Customer c, int type)
	{
		if ( type == 1)
		{
			return cusDao.applyAccount(c, 1);
		}
		else if ( type == 2)
		{
			return cusDao.applyAccount(c, 2);
		}
		return false;
	}

}

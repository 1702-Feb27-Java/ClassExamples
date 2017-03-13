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
				System.out.println("ACCOUNTS =====================");
				System.out.println("\tAccount Id: " + x.getId());
				System.out.println("\t\tType: " + x.getType());
				System.out.println("\t\t\tBalance: " + x.getAmount());
				System.out.println("\t\t\tStatus: " + x.getStatus());
				System.out.println("\t\t\tResolver: " + x.getResolver());
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
		return cusDao.updateAccount(a);
		
	}

}

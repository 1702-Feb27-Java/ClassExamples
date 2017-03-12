package com.sqlbank.service;

import java.util.ArrayList;

import com.sqlbank.dao.DAOAdminImp;
import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;

public class ServiceAdminImp extends ServiceImplementation implements ServiceAdmin
{

	static DAOAdminImp adDao = new DAOAdminImp();
	@Override
	public ArrayList<People> viewAccounts()
	{
		return adDao.viewAccounts();
	}
	@Override
	public boolean viewAaccount(String username)
	{
		int usertype = 0;
		Employee e;
		Customer cus;
		Admin ad;
		if(!dao.checkUsername(username))
		{
			usertype= dao.checkType(username);
			if(usertype == 1)
			{
				ad = adDao.returnAd(username);
				ad.viewMyAccount();
			}
			else if (usertype == 2)
			{
				e = adDao.returnEm(username);
				e.viewMyAccount();
			}
			else if (usertype == 3)
			{
				cus = adDao.returnCus(username);
			}
			return true;
		}
		return false;
		// TODO Auto-generated method stub
		
	}

}

package com.sqlbank.service;

import com.sqlbank.dao.DAOImplementation;
import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;

public class ServiceImplementation implements Service
{
	static DAOImplementation dao = new DAOImplementation();

	@Override
	public int isUserValid(String uname, String pword)
	{
		int res = dao.checkUser(uname, pword);
		return res;
	}
	
	@Override
	public boolean isUsernameValid(String uname)
	{
		return dao.checkUsername(uname);
	}

	@Override
	public Admin getAdmin(String uname)
	{
		return dao.returnAd(uname);
	}

	@Override
	public Employee getEmployee(String uname)
	{
		return dao.returnEm(uname);
	}

	@Override
	public Customer getCustomer(String uname)
	{
		return dao.returnCus(uname);
	}

	@Override
	public boolean createNewCus(String name, String uname, String pword)
	{
		return dao.addUser(name, uname, pword);
	}
	public boolean updateUsername(String old, String newOne)
	{
		return dao.updateUsername(old, newOne);
	}
}

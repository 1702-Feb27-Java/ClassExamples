package com.revature.service;

import java.util.ArrayList;
import com.revature.dao.DAOImpl;
import com.revature.pojo.Employee;

public class Service
{
	static DAOImpl dao = new DAOImpl();
	
	public Employee viewAccount(String username)
	{
		return dao.getUser(username);
	}
	
	public ArrayList<Employee> viewAllAccounts()
	{
		ArrayList<Employee> arr = new ArrayList();
		arr = dao.getAllUsers();
		return arr;
	}
}

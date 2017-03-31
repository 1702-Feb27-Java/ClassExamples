package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.DAOImpl;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimburstment;

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
	
	public Reimburstment myReimburstment(String pass)
	{
		Reimburstment reim = new Reimburstment();
		reim = dao.getReim(pass);
		return reim;
	}
	
	public ArrayList<Reimburstment> viewAllReimburstments()
	{
		ArrayList<Reimburstment> arr = new ArrayList();
		arr = dao.allReims();
		return arr;
	}
	
	public void makeReimburstment(Reimburstment reim)
	{
		dao.makeReim(reim);
	}
}

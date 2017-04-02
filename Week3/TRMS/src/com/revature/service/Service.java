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
	
	public ArrayList<Reimburstment> getPending(int dept_id)
	{
		ArrayList<Reimburstment> arr = new ArrayList();
		ArrayList<Employee> temp = new ArrayList();
		temp = dao.getEmpsInDept(dept_id);
		for(Employee e: temp)
		{
			arr.addAll(dao.getPendingReim(e.getEmp_id()));
		}
		return arr;
	}
	
	public ArrayList<Reimburstment> getAllReims(int emp_id)
	{
		ArrayList<Reimburstment> arr = new ArrayList();
		arr = dao.getAllReim(emp_id);
		return arr;
	}
	
	public void dsApprove(int reim_id)
	{
		dao.dsApproval(reim_id);
	}
	
	public void dhApproval(int reim_id)
	{
		dao.dhApproval(reim_id);
	}
	
	public void bApproval(int reim_id)
	{
		dao.bApproval(reim_id);
	}
	
	public void decline(int reim_id)
	{
		dao.decline(reim_id);
	}
}

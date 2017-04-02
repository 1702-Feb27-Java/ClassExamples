package com.revature.dao;

import java.util.ArrayList;
import com.revature.pojo.*;

public interface DAO
{
	public Employee getUser(String user);
	public ArrayList<Employee> getAllUsers();
	public void makeReim(Reimburstment reim);
	public Reimburstment getReim(String pass);
	public ArrayList<Reimburstment> getAllReim(int emp_id);
	public ArrayList<Reimburstment> allReims();
	public ArrayList<Employee> getEmpsInDept(int dept_id);
	public ArrayList<Reimburstment> getPendingReim(int emp_id);
	public void dsApproval(int reim_id);
	public void dhApproval(int reim_id);
	public void bApproval(int reim_id);
	public void decline(int reim_id);
}

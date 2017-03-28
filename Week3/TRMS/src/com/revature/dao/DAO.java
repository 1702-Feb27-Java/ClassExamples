package com.revature.dao;

import java.util.ArrayList;
import com.revature.pojo.*;

public interface DAO
{
	public Employee getUser(String user);
	public ArrayList<Employee> getAllUsers();
	public Reimburstment getReim(int emp_id);
	public ArrayList<Reimburstment> getAllReim(int emp_id);
	public ArrayList<Reimburstment> allReims();
}

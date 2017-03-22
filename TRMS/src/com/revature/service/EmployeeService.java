package com.revature.service;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.pojo.Employee;

public class EmployeeService {
	static EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
	public boolean login(String username, String password) {

		Employee temp = employeeDao.getEmployeeByLogin(username,password);
		System.out.println(temp);
		if(temp != null)
			return true;
		return false;
	}
}

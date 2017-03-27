package com.revature.service;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.pojo.Employee;

public class EmployeeService {
	EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

	public Employee loginEmployee(String username, String password) {
		Employee temp = employeeDao.getEmployeeByLogin(username,password);
		return temp;
	}
	
}

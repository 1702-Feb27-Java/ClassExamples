package com.revature.test;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.service.EmployeeService;

public class ServiceTest {

	public static void main(String[] args) {
		EmployeeDaoImpl eDao = new EmployeeDaoImpl();
		EmployeeService es = new EmployeeService();
		System.out.println("Testing login method in Service Test: " + es.login("admin", "admin"));

	}

}

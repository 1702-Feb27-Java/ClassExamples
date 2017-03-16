package com.revature.main;

import com.revature.service.EmployeeService;

public class Main {

	static EmployeeService serveEmp = new EmployeeService();
	public static void main(String[] args) {
		
		System.out.println(serveEmp.loginEmployee("benwebsta", "password"));	
		
	}

}

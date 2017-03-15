package com.revature.dao;

public class Test {

	public static void main(String[] args) {

	EmployeeDAO emp= new EmployeeDAO();
	//emp.seeAllCustomersAccounts();
	//emp.getRoleid(162);
	//emp.deleteCustomer(128);
	int x=emp.checkStatus(102);
	System.out.println(x);
	
	}

}

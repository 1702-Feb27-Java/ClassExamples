package com.revature.dao;

public class Test {

	public static void main(String[] args) {

	EmployeeDAO emp= new EmployeeDAO();
	//emp.seeAllCustomersAccounts();
	emp.approveAccount(101);
	CustomerDao cust= new CustomerDao();
	int x=cust.getRoleid(128);
	System.out.println(x);
	}

}

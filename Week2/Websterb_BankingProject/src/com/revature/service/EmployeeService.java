package com.revature.service;

import com.revature.dao.DAOEmployee;
import com.revature.dao.DAOEmployeeImpl;

public class EmployeeService {
	static CustomerService serve = new CustomerService();
	static DAOEmployeeImpl daoEmp = new DAOEmployeeImpl();
	
	public boolean addEmployee(String fn, String ln, String un, String pw){
		if(!serve.doesUsernameExist(un)){
			daoEmp.addEmployee(fn, ln, un, pw);
			return true;
		}
		else{
			return false;
		}
	}
	
}

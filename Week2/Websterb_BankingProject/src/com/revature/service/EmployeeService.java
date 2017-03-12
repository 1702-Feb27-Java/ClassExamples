package com.revature.service;

import java.util.ArrayList;

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
	
	public int loginEmployee(String username, String password){
		int employeeId = 0;//0 = no username found
		int dbEmpId = 0;
		String dbPass = "";
		ArrayList<String> employeeLoggin = new ArrayList<String>();
		employeeLoggin = daoEmp.loginEmployee(username, password);
		if(employeeLoggin != null){
			dbEmpId = Integer.parseInt(employeeLoggin.get(0));
			dbPass = employeeLoggin.get(1);
			if(password.equals(dbPass)){
				employeeId = dbEmpId;//login correct
				return employeeId;
			}
			else{
				employeeId = 1;//password wrong
				return employeeId;
			}
		}
		return employeeId;
	}
}

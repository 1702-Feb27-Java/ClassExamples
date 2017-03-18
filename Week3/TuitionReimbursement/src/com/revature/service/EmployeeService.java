package com.revature.service;

import java.util.ArrayList;
import java.util.Properties;

import com.revature.dao.DAOEmployeeImpl;

public class EmployeeService {
	
	public int loginEmployee(String username, String password){
		DAOEmployeeImpl daoEmp = new DAOEmployeeImpl();
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

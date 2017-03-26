package com.revature.service;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.*;
import com.revature.pojo.Employee;

import java.util.ArrayList;
import com.revature.connect.*;

public class ServiceDAOImpl implements ServiceDAO {

	@Override
	public boolean EMP_LOGIN(String username, String Password) {
		ArrayList<Employee> t;
		int arrSize = EmployeeDAOImpl.workers.size();
		//calling method from the EmployeeDAOImpl
		EmployeeDAOImpl this_emp = new EmployeeDAOImpl();
		t = this_emp.SignInEmployee(username, Password);
		
		if(t.size() == arrSize){
			return false;
		}
		
		return true;
		
	}
}

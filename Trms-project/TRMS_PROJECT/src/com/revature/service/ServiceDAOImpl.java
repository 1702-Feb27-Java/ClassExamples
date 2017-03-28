package com.revature.service;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.*;
import com.revature.pojo.Employee;

import java.util.ArrayList;
import com.revature.connect.*;

public class ServiceDAOImpl implements ServiceDAO {

	@Override
	public boolean EMP_LOGIN(String username, String Password) {
		ArrayList<Employee> t = new ArrayList<>();
		
		int arrSize = EmployeeDAOImpl.workers.size();
		//calling method from the EmployeeDAOImpl
		EmployeeDAOImpl this_emp = new EmployeeDAOImpl();
		t = this_emp.SignInEmployee(username);
		if(t.isEmpty()){
			System.out.println("test");
		}
		
		for(Employee e : t){
			if (e.getPassword() == null)
				return false;
			else if(e.getPassword().equals(Password)){
				System.out.println("User is valid!");
				return true;
			}
		}
		System.out.println("User is not valid!!");
		return false;
		
		
	}

	@Override
	public int Emp_Role_Check(String username) {
		EmployeeDAOImpl this_emp = new EmployeeDAOImpl();
		this_emp.getEmployeeRoleNum(username);
		
		int role_number = this_emp.getEmployeeRoleNum(username);
		
		return role_number;
	}

	@Override
	public int Dept_role_Check(String username) {
		EmployeeDAOImpl this_emp = new EmployeeDAOImpl();
		this_emp.getEmployeeDeptNum(username);
		
		int dept_num = this_emp.getEmployeeDeptNum(username);
		
		return dept_num;
	}
	
	
	
	
	
}

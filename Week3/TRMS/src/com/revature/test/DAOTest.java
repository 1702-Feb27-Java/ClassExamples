package com.revature.test;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.pojo.Employee;

public class DAOTest {

	public static void main(String[] args) {
		
		// EMPLOYEE TEST
		Employee e = new Employee();
	    
	    e.setFirstName("firstname");
	    e.setLastName("lastname");
	    e.setUsername("username");
	    e.setPassword("password");
	    e.setReportsTo(1);
	    e.setEmail("email");
	    e.setRoleId(1);
	    e.setDeptId(1);
	    
	    // insert into database
	    EmployeeDaoImpl eDao = new EmployeeDaoImpl();
	    eDao.create(e);
		System.out.println(eDao.getEmployee(e.getEmployeeId()));
		eDao.delete(e);
		System.out.println(eDao.listEmployees());
		
		e = eDao.getEmployeeByLogin("admin", "admin");
		System.out.println("in login method: " + e);
		
		// REIMBURSEMENT TEST
	}

}

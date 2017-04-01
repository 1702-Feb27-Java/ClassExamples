package com.revature.test;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.LookupDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimbursement;

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
		Reimbursement r = new Reimbursement();
		ReimbursementDaoImpl rDao = new ReimbursementDaoImpl();
		r = rDao.getReimbursement(1);
		System.out.println(r);
		

	}

}

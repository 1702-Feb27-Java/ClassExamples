package com.revature.test;

import com.revature.pojo.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class ServiceTest {

	public static void main(String[] args) {
		EmployeeService es = new EmployeeService();
		//System.out.println("Testing login method in Service Test: " + es.login("admin", "admin"));
		Employee testEmp = es.loginEmployee("admin", "admin");
		System.out.println(testEmp);
		Employee nullEmp = es.loginEmployee("admin", "pass");
		System.out.println(nullEmp);
		
		// TESTING REIMBURSEMENT SERVICE
		ReimbursementService rs = new ReimbursementService();
		System.out.println(rs.getPendingReimbursementsForEmployee(testEmp));
	}

}

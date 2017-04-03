package com.revature.test;

import com.revature.pojo.Employee;
import com.revature.pojo.Grade;
import com.revature.service.EmployeeService;
import com.revature.service.LookupService;
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
		
		// LOOKUP SERVICE TEST
		LookupService ls = new LookupService();
		System.out.println(ls.getDepts());
		for (Grade g : ls.getGrades()) {
			g.getGradeFormat();
			System.out.println(g.getGradeFormat());
		}
		
		// 
		System.out.println(ls.getGradeById(1));
		System.out.println(ls.getAppLevelTitleById(1));
		Employee e1 = es.loginEmployee("benco2", "supervisor");
		System.out.println("e1's dept: " + e1.getDeptId());
		System.out.println("e1 is benco: " + e1.isBenCo());
	}

}

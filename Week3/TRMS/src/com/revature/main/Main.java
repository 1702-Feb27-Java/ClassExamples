package com.revature.main;

import com.revature.dao.DAOEmployeeImpl;

public class Main {

	static DAOEmployeeImpl daoEmp = new DAOEmployeeImpl();
	public static void main(String[] args) {
		
		daoEmp.createEmployee(2, "Ben", "Webster", "benwebsta", "password", "7146421889", "tacosnak@gmail.com");

	}

}

package com.revature.main;

import com.revature.jdbc.StudentDAOImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StudentDAOImpl dao = new StudentDAOImpl();
		
		dao.setup();
		
		dao.prepareInsertStudent();
		
		dao.insertStudent();
		
		dao.selectStudent();
		
		dao.close();
		
	}

}

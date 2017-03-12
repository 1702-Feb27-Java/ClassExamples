package com.revature.test;

import com.revature.dao.DAOUserImp;
import com.revature.pojo.UserClass;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DAOUserImp dao = new DAOUserImp();
//		UserClass dummy = new UserClass();
//		
//		dummy.setFirstName("Barry");
//		dummy.setLastName("Allen");
//		dummy.setUsername("speedster");
//		dummy.setPassword("1234");
//		
//		dao.addUser(dummy);
	
		System.out.println(dao.getUserByID(5));
		

	}

}

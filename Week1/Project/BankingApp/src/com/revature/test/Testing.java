package com.revature.test;

import com.revature.dao.DAOAccountImp;
import com.revature.dao.DAOUserImp;
import com.revature.pojo.AccountClass;
import com.revature.pojo.UserClass;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAOAccountImp daoA = new DAOAccountImp();
		DAOUserImp dao = new DAOUserImp();
		UserClass dummy = new UserClass();
		
		dummy = dao.getUserByUsername("wonderwoman");
		
		System.out.println(dummy);
//			
		daoA.addAccount(2, dummy);
//		
		//AccountClass dummyA = new AccountClass();
//		dummyA.setTypeID(1);


	}

}

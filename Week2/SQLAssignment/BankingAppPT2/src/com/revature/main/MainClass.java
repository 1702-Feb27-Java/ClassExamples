package com.revature.main;

import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.Users;

public class MainClass {
	
	static AccountDAOImpl AccDao = new AccountDAOImpl();
	static UserDAOImpl UsDao = new UserDAOImpl();

	public static void main(String[] args) {
		
		StartProgram();
		
	}
	
	
	public static void StartProgram() {
		//now that we have an admin and an employee we can start adding customers
		Users usr = new Users(23, "peter", "pan", "ppan", "neverland", 3);
		UsDao.UserSignUp(usr);
		AccDao.applyForAccount(usr);
		
		
	}

}

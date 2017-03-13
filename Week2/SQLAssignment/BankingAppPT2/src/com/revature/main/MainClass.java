package com.revature.main;

import com.revature.dao.UserDAOImpl;
import com.revature.pojo.Users;

public class MainClass {

	public static void main(String[] args) {
		
		Users new_user = new Users(2, "john", "smith", "johns", "1234", 2); //adding an employee
		UserDAOImpl udao = new UserDAOImpl();
		
		udao.UserSignUp(new_user);

	}

}

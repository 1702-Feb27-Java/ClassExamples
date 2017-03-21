package com.revature.trms;

import com.revature.dao.DAOImpl;

public class UserService {
		
	public static void addNewUser(String firstName, String lastName, String username,
			String password, String email, int role, int dept, int sup){
		
		User u = new User(firstName, lastName, username, password, email,
				role, dept, sup);
		DAOImpl.insertUserData(u);
	}
}

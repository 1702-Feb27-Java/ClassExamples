package com.revature.trms;

import com.revature.dao.DAOImpl;

public class UserService {
		
	public static void addNewUser(String firstName, String lastName, String username,
			String password, String email, String role, String dept, int supId){
		
		User u = new User(firstName, lastName, username, password, 
				email, role, dept, supId);
				
		DAOImpl.insertUserData(u);
	}
	
	public static User getUserInfo(int userId){
		User u = DAOImpl.getUserDetails(userId);
		return u;
	}
}

package com.revature.trms;

import com.revature.dao.DAOImpl;

public class UserService {
		
	public static void addNewUser(String firstName, String lastName, String username,
			String password, String email, int roleId, int deptId, int supId){
		
		User u = new User(firstName, lastName, username, password, email, supId);
		Role r = new Role(roleId);
		Department d = new Department(deptId);
		
		DAOImpl.insertUserData(u, r, d);
	}
	
	public static User getUserInfo(int userId){
		User u = DAOImpl.getUserDetails(userId);
		return u;
	}
}

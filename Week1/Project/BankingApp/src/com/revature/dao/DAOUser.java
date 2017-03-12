package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.UserClass;

public interface DAOUser {
	
	// add
	public void addUser(UserClass uc);
	
	// update
	public void updateFirstName(int a, String f);
	public void updateLastName(UserClass uc, String l);
	public void updateUsername(UserClass uc, String u);
	public void updatePassword(UserClass uc, String pw);
	
	// read
	ArrayList<UserClass> getAllUsers();
	UserClass getUserByID(int id);

}

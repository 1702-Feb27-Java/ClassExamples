package com.revature.dao;

import java.util.ArrayList;
import java.util.Hashtable;

import com.revature.pojo.UserClass;

// interface for methods to read and update the Users table in the database
public interface DAOUser {
	
	// add
	public void addUser(UserClass uc);
	
	// update
	public void updateFirstName(UserClass uc, String f);
	public void updateLastName(UserClass uc, String l);
	public void updateUsername(UserClass uc, String u);
	public void updatePassword(UserClass uc, String pw);
	
	// read
	ArrayList<UserClass> getAllUsers();
	UserClass getUserByID(int id);
	UserClass getUserByUsername(String username);
	Hashtable<String, String> getUsernamePW();
	

}

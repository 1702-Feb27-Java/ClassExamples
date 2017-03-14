package com.revature.banking2.dao;

import java.util.ArrayList;

import com.revature.banking2.pojo.User;

/**
 * Data access for DB and application.
 * 
 * @author Michael Hobbs
 *
 */
public interface UserDao {

	void addUser(String username, String password, String firstName, String lastName, User.Role role);
	
	void updateUser(User user);
	
	ArrayList<User> getUsers(User.Role role);
	
	void addAccount();
	
}

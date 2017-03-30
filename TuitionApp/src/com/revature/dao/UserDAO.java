package com.revature.dao;

import java.util.ArrayList;
import java.util.Hashtable;

import com.revature.pojo.AppClass;
import com.revature.pojo.NotifClass;
import com.revature.pojo.UserClass;

public interface UserDAO {

	//add
	public void addUser(UserClass uc);
	public void addNotif(UserClass uc);
	
	//read
	// ArrayList<UserClass> getManagement(); // anyone with role_id > 1
	ArrayList<NotifClass> getNotifByUserID();
	UserClass getUserByUsername(String username);
	UserClass getNamesByUserID(int userID);
	
	// returns true if there are supervisors in this dept
	boolean checkForSuper(int deptID); 
	
	// for login authentication
	Hashtable<String, String> getUsernamePW();
}

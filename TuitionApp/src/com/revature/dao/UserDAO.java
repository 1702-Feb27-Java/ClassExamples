package com.revature.dao;

import java.util.ArrayList;
import java.util.Hashtable;

import com.revature.pojo.NotifClass;
import com.revature.pojo.UserClass;

public interface UserDAO {

	//add
	public void addUser(UserClass uc);
	public void addNotif(int userID, UserClass uc, String message);
	
	//read
	// ArrayList<UserClass> getManagement(); // anyone with role_id > 1
	ArrayList<NotifClass> getNotifByUserID(int userID);
	UserClass getUserByUsername(String username);
	UserClass getNamesByUserID(int userID);
	int getUserIDbyAppID(int appID);
	
	// update
	void clearNotification(NotifClass notif);
	
	// returns true if there are supervisors in this dept
	boolean checkForSuper(int deptID); 
	
	// for login authentication
	Hashtable<String, String> getUsernamePW();
}

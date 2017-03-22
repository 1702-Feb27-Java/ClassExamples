package com.revature.dao;

import java.util.ArrayList;
import java.util.Hashtable;

import com.revature.pojo.AppClass;
import com.revature.pojo.NotifClass;
import com.revature.pojo.UserClass;

public interface UserDAO {

	//add
	public void addUser(UserClass uc);
	
	//upate
	public void updateApp(UserClass uc);
	public void updateNotif(UserClass uc);
	
	//read
	ArrayList<UserClass> getAllUsers();
	ArrayList<AppClass> getAppsByUserID();
	ArrayList<NotifClass> getNotifByUserID();
	
	// for login authentication
	Hashtable<String, String> getUsernamePW();
}

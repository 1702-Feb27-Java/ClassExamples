package com.revature.dao;

import java.util.ArrayList;
import java.util.Hashtable;

import com.revature.pojo.AppClass;
import com.revature.pojo.NotifClass;
import com.revature.pojo.UserClass;

public interface UserDAO {

	//add
	public void addUser(UserClass uc);
	public void addApp(UserClass uc, AppClass ac);
	public void addNotif(UserClass uc);
	
	//read
	// ArrayList<UserClass> getManagement(); // anyone with role_id > 1
	ArrayList<AppClass> getAppsByUserID();
	ArrayList<NotifClass> getNotifByUserID();
	UserClass getUserByUsername(String username);
	
	// for login authentication
	Hashtable<String, String> getUsernamePW();
}


package com.revature.dao;

import com.revature.pojo.Users;

public interface UserDAO {
	
	//methods for the USERS table in SQL
	public void UserSignUp(Users user);			//method for user sign up
	public int UserFirst_and_last_name(Users user, String fname, String lname);
	public int UpdateUsername_password(Users user, String usr, String pass);
	
	//Function to validate user
	public int ValidateUser(String user_name, String Password);
	
	

}

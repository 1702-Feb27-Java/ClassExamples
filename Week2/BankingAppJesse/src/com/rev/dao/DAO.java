package com.rev.dao;

import java.util.ArrayList;

import com.rev.pojo.UserInformation;

public interface DAO {

	int users(String id, String rid);//
	//pull and display single user informtaion based off user id
	UserInformation getUserById(int id);//
	
	//change balance after with or dep
	int setBalance (UserInformation  nusr, String username);//
	
	//update user information 
	int updateUser(String b, String c, int d, int e);
	
	//get all information for users(display full table)
	ArrayList<UserInformation> getAllInfo();
	
	//I think i will be able to set up a function like the one above so that when the program starts it reads the data base and stores the users in arrays
	//based off of their role_id (custoemr, employee, and admin).
	int insertNewUser(String a, String b);
	
}


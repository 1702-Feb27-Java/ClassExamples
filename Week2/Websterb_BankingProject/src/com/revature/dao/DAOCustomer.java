package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.User;

public interface DAOCustomer {
	//firstname, lastname, username, password
	int addCustomer(String fn, String ln, String un, String pw);
	int applyForAccount(int userId, int actId, int typeId);
	ArrayList<String> loginCustomer(String un, String pw);
	ArrayList<User> getAllUsers();
}

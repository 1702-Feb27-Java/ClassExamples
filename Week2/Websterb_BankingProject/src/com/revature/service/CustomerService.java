package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.DAOCustomerImpl;
import com.revature.pojo.User;

public class CustomerService {

	static DAOCustomerImpl daoCust = new DAOCustomerImpl();
	
	public boolean doesUsernameExist(String un){
		ArrayList<User> users = new ArrayList<User>();
		users = daoCust.getAllUsers();
		
		for(User user : users){
			String temp = user.getUsername();
			if(temp.equals(un)){
				return true;
			}
		}
		return false;
	}
	
	public boolean addCustomer(String fn, String ln, String un, String pw){
		if(!doesUsernameExist(un)){
			daoCust.addCustomer(fn, ln, un, pw);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean applyForAccount(int userId, int actId, int typeId){
		if(daoCust.applyForAccount(userId, actId, typeId) == 0){
			return false;
		}
		else{
			return true;
		}
	}
	public int loginCustomer(String username, String password){
		int customerId = 0;//0 = no username found
		int dbCustId = 0;
		String dbPass = "";
		ArrayList<String> customerLoggin = new ArrayList<String>();
		customerLoggin = daoCust.loginCustomer(username, password);
		if(customerLoggin != null){
			dbCustId = Integer.parseInt(customerLoggin.get(0));
			dbPass = customerLoggin.get(1);
			if(password.equals(dbPass)){
				customerId = dbCustId;//login correct
				return customerId;
			}
			else{
				customerId = 1;//password wrong
				return customerId;
			}
		}
		return customerId;
	}
	
}

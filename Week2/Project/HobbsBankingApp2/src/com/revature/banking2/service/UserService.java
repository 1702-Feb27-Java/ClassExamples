package com.revature.banking2.service;

import java.util.ArrayList;

import com.revature.banking2.pojo.User;

public interface UserService {
	
	ArrayList<User> getCustomers();
	
	ArrayList<User> getEmployees();
	
	ArrayList<User> getAdmins();
	
	void addCustomer(String username, String password, String firstName, String lastName);
	
	void addEmployee(String username, String password, String firstName, String lastName);
	
	void addAdmin(String username, String password, String firstName, String lastName);

	void updateCustomer(User customer);
	
	void updateEmployee(User employee);
	
	void updateAdmin(User admin);
	
}

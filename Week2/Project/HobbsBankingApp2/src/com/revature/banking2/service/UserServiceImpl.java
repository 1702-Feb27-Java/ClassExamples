package com.revature.banking2.service;

import java.util.ArrayList;

import com.revature.banking2.dao.UserDao;
import com.revature.banking2.dao.UserDaoImpl;
import com.revature.banking2.pojo.User;
import com.revature.banking2.pojo.User.Role;

public class UserServiceImpl implements UserService {
	
	private static UserDao userDao;
	private static UserService userService;
	
	private UserServiceImpl() {
		userDao = new UserDaoImpl();
	}
	
	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	@Override
	public void updateCustomer(User customer) {
		userDao.updateUser(customer);
	}

	@Override
	public void updateEmployee(User employee) {
		userDao.updateUser(employee);
	}

	@Override
	public void updateAdmin(User admin) {
		userDao.updateUser(admin);
	}

	@Override
	public ArrayList<User> getCustomers() {
		return userDao.getUsers(User.Role.customer);
	}

	@Override
	public ArrayList<User> getEmployees() {
		return userDao.getUsers(User.Role.employee);
	}

	@Override
	public ArrayList<User> getAdmins() {
		return userDao.getUsers(User.Role.admin);
	}

	@Override
	public void addCustomer(String username, String password, String firstName, String lastName) {
		userDao.addUser(username, password, firstName, lastName, Role.customer);
	}

	@Override
	public void addEmployee(String username, String password, String firstName, String lastName) {
		userDao.addUser(username, password, firstName, lastName, Role.employee);
	}

	@Override
	public void addAdmin(String username, String password, String firstName, String lastName) {
		userDao.addUser(username, password, firstName, lastName, Role.admin);
	}
	
	

}

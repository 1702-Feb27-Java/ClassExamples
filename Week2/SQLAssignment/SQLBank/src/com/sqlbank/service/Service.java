package com.sqlbank.service;

import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;

public interface Service
{
	int isUserValid(String uname, String pword);
	boolean isUsernameValid(String uname);
	
	boolean createNewCus(String name, String uname, String pword);
	Admin getAdmin (String uname);
	Employee getEmployee (String uname);
	Customer getCustomer (String uname);
}

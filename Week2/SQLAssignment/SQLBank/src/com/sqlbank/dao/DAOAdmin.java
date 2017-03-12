package com.sqlbank.dao;

import java.util.ArrayList;

import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;

public interface DAOAdmin extends DAO
{
	
	//VIEW ACCOUNTS OF OTHER TYPES OF PEOPLE
	Customer viewAccount (Customer cus);
	//--VIEW ALL ACCOUNTS
	ArrayList<People> viewAccounts();
	
	//EDIT ACCOUNTS OF PEOPEL
	Admin editAccount(Admin ad);
	Employee editAccount (Employee em);
	Customer editAccount (Customer cus);
	
}

package com.sqlbank.dao;

import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;

/**
 * 
 * @author tobon
 * CRUD OPERATIONS ON OUR DATABASE
 */
public interface DAO
{
	//CREATE USER
	/**
	 * @param uname : username from new customer
	 * @param pwrod : password from new customer
	 * @return int : rows affected
	 */
	boolean addUser(String name, String uname, String pword);
	
	//VALIDATE USER
	/**
	 * @param uname := username user passed in
	 * @param pword := password user passed in
	 * @return int 
	 * 0 NO DATA
	 * 1 ADMIN
	 * 2 EMPLOYEE
	 * 3 CUSTOMER
	 * -1 ERROR IN DATABASE
	 */
	int checkUser(String uname, String pword);

	public boolean checkUsername (String uname);
	
	public int checkType (String uname);
	
	//RETRIEVE USER
	/**
	 * WILL BE CALLED ONLY IF checkUser RETURNS VALID TYPE 1,2,3 
	 * @param uname := username to create People out of
	 * @return type of People
	 */
	Admin returnAd (String uname);
	
	/**
	 * @param uname to get employee info from
	 * @return Employee : employee retrieved from db
	 */
	Employee returnEm (String uname);
	
	/**
	 * @param uname to get employee info from
	 * @return Customer : Customer retrieved from db
	 */
	Customer returnCus (String uname);
	
	//UPDATE USER
	public boolean updateUsername (String old, String newOne);
	public boolean updatePassword (String old, String newOne);
}

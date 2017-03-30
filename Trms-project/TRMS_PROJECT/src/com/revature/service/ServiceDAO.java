package com.revature.service;


public interface ServiceDAO {
	
	public boolean EMP_LOGIN(String username, String Password);
	public int Emp_Role_Check(String username);
	public int Dept_role_Check(String username);
	

	

}

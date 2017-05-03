package com.revature.pojo;

public class Employee
{
	String fname, lname, username, password, email, address;
	int dept_id, reportsto, role_id, emp_id, allowence;
	
	public Employee()
	{
		
	}
	public String getFname()
	{
		return fname;
	}
	public void setFname(String fname)
	{
		this.fname = fname;
	}
	public String getLname()
	{
		return lname;
	}
	public void setLname(String lname)
	{
		this.lname = lname;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public int getDept_id()
	{
		return dept_id;
	}
	public void setDept_id(int dept_id)
	{
		this.dept_id = dept_id;
	}
	public int getReportsto()
	{
		return reportsto;
	}
	public void setReportsto(int reportsto)
	{
		this.reportsto = reportsto;
	}
	public int getRole_id()
	{
		return role_id;
	}
	public void setRole_id(int role_id)
	{
		this.role_id = role_id;
	}
	public int getEmp_id()
	{
		return emp_id;
	}
	public void setEmp_id(int emp_id)
	{
		this.emp_id = emp_id;
	}
	public int getAllowence()
	{
		return allowence;
	}
	public void setAllowence(int allowence)
	{
		this.allowence = allowence;
	}
}

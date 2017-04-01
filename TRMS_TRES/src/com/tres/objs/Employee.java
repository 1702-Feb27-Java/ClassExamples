package com.tres.objs;

public class Employee
{
	//VALUES EMPLOYEE HAS ==================================================================================
	private String fname,
			username,
			pwd,
			email,
			dept,
			reportsTo,
			role;
	private int balance,
				repid,
				deptid,
				roleid,
				id;
	//SETTERS AND GETTERS ==================================================================================
	public final String getFname()
	{
		return fname;
	}
	public final void setFname(String fname)
	{
		this.fname = fname;
	}
	public final String getDept()
	{
		return dept;
	}
	public final void setDept(String dept)
	{
		this.dept = dept;
	}
	public final String getReportsTo()
	{
		return reportsTo;
	}
	public final void setReportsTo(String reportsTo)
	{
		this.reportsTo = reportsTo;
	}
	public final String getRole()
	{
		return role;
	}
	public final void setRole(String role)
	{
		this.role = role;
	}
	public final String getUsername()
	{
		return username;
	}
	public final void setUsername(String username)
	{
		this.username = username;
	}
	public final String getPwd()
	{
		return pwd;
	}
	public final void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public final String getEmail()
	{
		return email;
	}
	public final void setEmail(String email)
	{
		this.email = email;
	}
	public final int getBalance()
	{
		return balance;
	}
	public final void setBalance(int balance)
	{
		this.balance = balance;
	}
	public final int getRepid()
	{
		return repid;
	}
	public final void setRepid(int repid)
	{
		this.repid = repid;
	}
	public final int getDeptid()
	{
		return deptid;
	}
	public final void setDeptid(int deptid)
	{
		this.deptid = deptid;
	}
	public final int getRoleid()
	{
		return roleid;
	}
	public final void setRoleid(int roleid)
	{
		this.roleid = roleid;
	}
	public final int getId()
	{
		return id;
	}
	public final void setId(int id)
	{
		this.id = id;
	}
	//Constructor ==================================================================================
	public Employee(String fname, String dept, String reportsTo, String role, String username, String pwd, String email,
			int balance, int repid, int deptid, int roleid, int id)
	{
		super();
		this.fname = fname;
		this.username = username;
		this.pwd = pwd;
		this.email = email;
		this.dept = dept;
		this.reportsTo = reportsTo;
		this.role = role;
		this.balance = balance;
		this.repid = repid;
		this.deptid = deptid;
		this.roleid = roleid;
		this.id = id;
	}
}

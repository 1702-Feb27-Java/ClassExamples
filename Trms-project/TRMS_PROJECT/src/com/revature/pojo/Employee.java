package com.revature.pojo;

public class Employee {
	
	private String Fname;
	private String Lname;
	private int Dept_id;
	private int Role_id;
	private String username;
	private String password;
	private int Allowance;
	
	//getters and setters
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public int getDept_id() {
		return Dept_id;
	}
	public void setDept_id(int dept_id) {
		Dept_id = dept_id;
	}
	public int getRole_id() {
		return Role_id;
	}
	public void setRole_id(int role_id) {
		Role_id = role_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAllowance() {
		return Allowance;
	}
	public void setAllowance(int allowance) {
		Allowance = allowance;
	}
	
	@Override
	public String toString() {
		return "Employee [Fname=" + Fname + ", Lname=" + Lname + ", Dept_id=" + Dept_id + ", Role_id=" + Role_id
				+ ", username=" + username + ", password=" + password + ", Allowance=" + Allowance + "]";
	}
	
	
	
	
	

}

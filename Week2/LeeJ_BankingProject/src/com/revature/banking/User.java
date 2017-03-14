package com.revature.banking;

import java.util.ArrayList;

public class User {
	protected int id = 0;
	protected String firstname = null;
	protected String lastname = null;
	protected String username = null;
	protected String password = null;
	protected ArrayList<Integer> accountIds = new ArrayList<>();
	protected int roleId = 0;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String firstname, String lastname, String username, String password) {
		// TODO Auto-generated constructor stub
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public ArrayList<Integer> getAccountIds() {
		return accountIds;
	}
	public void setAccountIds(ArrayList<Integer> accountIds) {
		this.accountIds = accountIds;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", accountIds=" + accountIds + ", roleId=" + roleId + "]";
	}
}

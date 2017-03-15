package com.revature.pojo;

public class Users {
	
	private int user_id;
	private String Firstname;
	private String Lastname;
	private String username;
	private String password;
	private int role_id;
	public Accounts a;
	
	//constructors
	public Users() {
		//this.user_id = 0;
		//this.Firstname = "";
		//this
	}
	
	public Users(int user_id, String firstname, String lastname, String username, String password, int role_id) {
		super();
		this.user_id = user_id;
		Firstname = firstname;
		Lastname = lastname;
		this.username = username;
		this.password = password;
		this.role_id = role_id;
	}

	//getters and setters
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
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

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", Firstname=" + Firstname + ", Lastname=" + Lastname + ", username="
				+ username + ", password=" + password + ", role_id=" + role_id + "]";
	}
	
	
	

}

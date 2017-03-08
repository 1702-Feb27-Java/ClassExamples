package com.mory;

public class User {
	protected String userName;
	protected int password;


	
	public User(String userName, int Password){
		this.userName=userName;
		this.password=password;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getPassword() {
		return password;
	}


	public void setPassword(int password) {
		this.password = password;
	}

}
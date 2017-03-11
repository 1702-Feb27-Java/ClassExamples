package com.revature.bankingapp.model;

public abstract class User {
	private int id;
	private String username;
	private String password;
	
	User(int id, String username, String password){
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	User(String username, String password){
		this.id = 0;
		this.username = username;
		this.password = password;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int n){
		this.id = n;
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
	
	public abstract String getUserType();
}

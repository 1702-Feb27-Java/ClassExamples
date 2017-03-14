package com.revature.starting;

public class Admin {
	private String type;
	private String name;
	private String password;
	
	public Admin (String type, String name, String password){
		this.type = type;
		this.name = name;
		this.password = password;
		
		
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

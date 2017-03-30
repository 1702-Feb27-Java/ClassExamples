package com.revature.starting;

public class Admin {
	private String type;
	private String name;
	private String password;
	private int id;

	public Admin(String type, String name, String password, int id) {
		this.type = type;
		this.name = name;
		this.password = password;
		this.id = id;

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "admin [id=" + this.id + ", username=" + this.name + " Role= " + this.type + " pw: " + this.password + "]";
	}
}
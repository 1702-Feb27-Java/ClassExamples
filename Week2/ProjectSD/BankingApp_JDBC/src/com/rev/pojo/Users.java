package com.rev.pojo;

public class Users {
	int userID;
	String userName;
	int roleID;
	
	/**
	 * @param userName
	 * @param roleID
	 */
	public Users(String userName, int roleID, int userID) {
		super();
		this.userName = userName;
		this.roleID = roleID;
		this.userID = userID;
	}
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}

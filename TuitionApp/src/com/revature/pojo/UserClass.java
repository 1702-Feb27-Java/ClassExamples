package com.revature.pojo;

public class UserClass {
	private String firstname, lastname, username, pw, email;
	private int userID, deptID, roleID, reportsto, appID, notifID;

	public UserClass(int userID, String firstname, String lastname, String username, String pw, String email, int deptID,
			 int roleID, int appID, int reportsto) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.pw = pw;
		this.email = email;
		this.userID = userID;
		this.deptID = deptID;
		this.roleID = roleID;
		this.reportsto = reportsto;
		this.appID = appID;
	}

	public UserClass() {

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

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public int getReportsto() {
		return reportsto;
	}

	public void setReportsto(int reportsto) {
		this.reportsto = reportsto;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getNotifID() {
		return notifID;
	}

	public void setNotifID(int notifID) {
		this.notifID = notifID;
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	@Override
	public String toString() {
		return "UserClass [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", pw=" + pw
				+ ", email=" + email + ", userID=" + userID + ", deptID=" + deptID + ", roleID=" + roleID
				+ ", reportsto=" + reportsto + ", appID=" + appID + "]";
	}

}

package com.rev.pojo;

import java.util.ArrayList;

public class UserInformation {

	private int id;
	private String un;
	private String rid;
	private String pw;
	static ArrayList<UserInformation> userinfo;
	private String savings;
	private String checkings;
	private int cbalance;
	private int sbalance;

	private String role;

	public UserInformation(int id, String un, String rid, String pw) {
		super();
		this.id = id;
		this.un = un;
		this.rid = rid;
		this.pw = pw;
		this.userinfo = userinfo;
		this.cbalance = cbalance;
		this.sbalance = sbalance;
		this.savings = savings;
		this.checkings = checkings;

	}

	public String getSavings() {
		return savings;
	}

	public void setSavings(String savings) {
		this.savings = savings;
	}

	public String getCheckings() {
		return checkings;
	}

	public void setCheckings(String checkings) {
		this.checkings = checkings;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getCbalance() {
		return cbalance;
	}

	public void setCbalance(int cbalance) {
		this.cbalance = cbalance;
	}

	public int getSbalance() {
		return sbalance;
	}

	public void setSbalance(int sbalance) {
		this.sbalance = sbalance;
	}

	public static ArrayList<UserInformation> getUserinfo() {
		return userinfo;
	}

	public static void setUserinfo(ArrayList<UserInformation> userinfo) {
		UserInformation.userinfo = userinfo;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public UserInformation() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {

		/////////////// changinf numeric value of role id to actual role
		if (rid.equals("3")) {
			this.role = "Customer";
		} else if (rid.equals("2")) {
			this.role = "Employee";
		} else if (rid.equals("1"))

			this.role = "Admin";

		this.rid = rid;
	}

	@Override
	public String toString() {
		return "UserInformation [id=" + this.id + ", un=" + this.un + ", rid=" + this.rid + " Role= " + this.role + " pw: " + this.pw + "]";
	}

}

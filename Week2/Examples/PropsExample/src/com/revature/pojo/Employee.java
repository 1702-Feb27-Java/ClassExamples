package com.revature.pojo;
/**
 * 
 * @author Ryan
 *
 *
 *	This is simple a pojo that has getters and setters for the sake of storing data
 *	that can be inserted into the database.
 */
public class Employee {
	private String fname, lname;
	private int eid, titleID, managerID;
	public Employee(String fname, String lname, int titleID, int managerID){
		this.eid = 0;
		this.fname = fname;
		this.lname = lname;
		this.titleID = titleID;
		this.managerID = managerID;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getTitleID() {
		return titleID;
	}
	public void setTitleID(int titleID) {
		this.titleID = titleID;
	}
	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
}

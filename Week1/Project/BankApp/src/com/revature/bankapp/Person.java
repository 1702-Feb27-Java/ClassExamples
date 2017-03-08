package com.revature.bankapp;

import java.io.Serializable;

public class Person implements Serializable {

	private String fname, lname, email, pnum;
	
	public Person(String fname, String lname, String pnum, String email){
		this.fname = fname;
		this.lname = lname;
		this.pnum = pnum;
		this.email = email;
	}
	
	@Override
	public String toString(){
		return fname + ":" + lname + ":" + pnum + ":" + email;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getEmail() {
		return email;
	}

	public String getPnum() {
		return pnum;
	}

	public void setFname(String fname) {
		// TODO Auto-generated method stub
		this.fname = fname;
		
	}
	public void setLname(String lname){
		this.lname = lname;
	}
	public void setPnum(String pnum){
		this.pnum = pnum;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	
}

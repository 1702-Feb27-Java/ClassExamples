package com.revature.trms;

public class Department {

	private int deptId = 0;
	private String dept;
	
	public Department() {
		
	}
	
	public Department(int deptId) {
		this.deptId = deptId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptid(int deptId) {
		this.deptId = deptId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}

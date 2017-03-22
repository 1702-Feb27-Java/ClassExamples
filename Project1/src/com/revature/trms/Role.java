package com.revature.trms;

public class Role {
	
	private int roleId = 0;
	private String role;
	
	public Role() {
		
	}
	
	public Role(int roleId) {
		this.roleId = roleId;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

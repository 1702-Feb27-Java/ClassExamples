package com.revature.bankingapp.database.model;

public class Role {
	public int getRoleId() {
		return roleId;
	}

	public String getRole() {
		return role;
	}

	final int roleId;
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

	public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	final String role;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}
	

	
}

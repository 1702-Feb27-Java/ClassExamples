package database.model;

import database.service.Service;

public class User implements Comparable<User>{
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", roleId=" + roleId + ", deptId=" + deptId + ", supervisorId="
				+ supervisorId + "]";
	}
	public User(Integer userId, String firstName, String lastName, String username, String password, Integer roleId,
			Integer deptId, Integer supervisorId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.deptId = deptId;
		this.supervisorId = supervisorId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	private Integer userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Integer roleId;
	private Integer deptId;
	private Integer supervisorId;
	public Integer getSupervisorId() {
		return supervisorId;
	}

	public User getSupervisor(){
		return Service.getInstance().getUserFromId(supervisorId);
	}
	
	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getUserId() {
		return userId;
	}
	public Dept getDept() {
		return Service.getInstance().getDepts().get(deptId);
	}
	@Override
	public int compareTo(User arg0) {
		return this.username.compareToIgnoreCase(arg0.getUsername());
	}
	
}

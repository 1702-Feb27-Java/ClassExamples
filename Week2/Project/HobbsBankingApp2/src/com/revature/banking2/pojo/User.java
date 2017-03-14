package com.revature.banking2.pojo;

public class User {

	public enum Role {
		admin(1), employee(2), customer(3);
		
		int id;
		
		private Role(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
	}
	
	private Integer id;
	private String username, password;
	private String firstName, lastName;
	private Role role;
	
	public User() {
		
	}
	
	public User(User original) {
		copyUser(original);
	}
	
	/**
	 * 
	 * @param username
	 * @return true if the username is valid
	 */
	public static boolean isValidUsername(String username) {
		return username.matches("[A-Za-z0-9_]*");
	}
	
	/**
	 * 
	 * @param password
	 * @return true if the password is valid
	 */
	public static boolean isValidPassword(String password) {
		return password.matches("[A-Za-z0-9_!@#$%^&*()-]*");
	}
	
	public void copyUser(User original) {
		this.id = original.getId();
		this.username = original.getUsername();
		this.password = original.getPassword();
		this.firstName = original.getFirstName();
		this.lastName = original.getLastName();
		this.role = original.getRole();
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + "]";
	}
	
}

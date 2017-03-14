package pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	int roleId;
	private List<Integer> listOfaccounts= new ArrayList<Integer>();
	public User(int userId, String firstName, String lastName, String userName, String password, int roleId,
			List<Integer> listOfaccounts) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
		this.listOfaccounts = listOfaccounts;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public List<Integer> getListOfaccounts() {
		return listOfaccounts;
	}
	public void setListOfaccounts(List<Integer> listOfaccounts) {
		this.listOfaccounts = listOfaccounts;
	}
	
	
}
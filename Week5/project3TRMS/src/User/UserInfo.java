package User;

import java.util.ArrayList;

public class UserInfo {
	// all columns from the employee table turned in to vars
	private String firstname;// use to dsiplay helloe "" on page
	private String lastname;// use to dsiplay helloe "" on page
	private String address;
	private int employee_id; // i think i want to save this for the full
							// duration of the session
	private int dep_id;// if benco send out, else only view from the same
						// department
	private int reportsto;// can only approve those who report to you
	private int role_id;// decide which page to send them to
	private String username;// verify to login
	private String password;// verify to login
	private String email;
	private int allowance;
	// array list of information to ensure it can view everything
	static ArrayList<UserInfo> userinfo;
//	
//	public UserInfo()
//	{
//		super();
//	}

//	public UserInfo(String firstname, String lastname, int employee_id, int dep_id, int reportsto, int role_id,
//			String username) {
//
//		super();
//		this.firstname = firstname;
//		this.lastname = lastname;
//		// this.address = address; //not sure if needed here
//		this.employee_id = employee_id;
//		this.dep_id = dep_id;
//		this.reportsto = reportsto;
//		this.role_id = role_id;
//		this.username = username;
//		// this.password = password;
//		// this.email = email; //not sure if needed here
//		// this.allowance = allowance; //not sure if needed here
//
//	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	public int getReportsto() {
		return reportsto;
	}

	public void setReportsto(int reportsto) {
		this.reportsto = reportsto;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAllowance() {
		return allowance;
	}

	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}

	public static ArrayList<UserInfo> getUserinfo() {
		return userinfo;
	}

	public static void setUserinfo(ArrayList<UserInfo> userinfo) {
		UserInfo.userinfo = userinfo;
	}

}

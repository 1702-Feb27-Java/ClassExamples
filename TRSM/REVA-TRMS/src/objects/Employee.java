package objects;

public class Employee {
	  private int eId;
	  private int reportsto;
	  private String depart;
	  private int pending;
	  private int awarded;
	  private String firstName;
	  private String lastName;
	  private String userName;
	  private String password;
	  private String email;
	  private String role;
	  
	  public Employee(){
		  this.reportsto = 0;
		  this.depart = null;
		  this.password = null;
		  this.pending = 0;
		  this.awarded = 0;
		  this.firstName = null;
		  this.lastName = null;
		  this.userName = null;
		  this.email = null;
		  this.role = null;
		  this.eId = 0;
	  }
	  
	  public Employee(int reportsto, String depart, int pending, int awarded, String firstName, String lastName,
			  String userName, String password, String email, String role, int eId){
		  
		  this.reportsto = reportsto;
		  this.depart = depart;
		  this.pending = pending;
		  this.awarded = awarded;
		  this.firstName = firstName;
		  this.lastName = lastName;
		  this.userName = userName;
		  this.password = password;
		  this.email = email;
		  this.role = role;
		  this.eId = eId;
		  
	  }

	public int getReportsto() {
		return reportsto;
	}

	public void setReportsto(int reportsto) {
		this.reportsto = reportsto;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public int getAwarded() {
		return awarded;
	}

	public void setAwarded(int awarded) {
		this.awarded = awarded;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [reportsto=" + reportsto + ", depart=" + depart + ", pending=" + pending + ", awarded="
				+ awarded + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + ", role=" + role + "]";
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}
	
	
	
	
	  
	  
	 

}

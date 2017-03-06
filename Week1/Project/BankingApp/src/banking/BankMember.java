package banking;



public class BankMember {
	
	private String name;	
	private Type type;
	private String userName = null;
	private String password = null;
	private Status checkingStatus;
	private Status savingStatus;
	private int checking;
	private int savings;
	
	
	/*
	 * A Bank member is a person with information stored in the banking app
	 * @param name the name of the person
	 * @param type the type of person in the system can be a Customer, an Employee, or an Admin
	 */
	public BankMember(String name, Type type){
		this.type = type;
		this.name = name;
		checkingStatus = Status.NONE;
		savingStatus = Status.NONE;
		checking = 0;
		savings = 0;
	}
	
	/*
	 * makes a new BankMember with all fields set
	 */
	public BankMember(String name, Type type, String userName, String password, Status checkingStatus, 
			Status savingStatus, int checking, int savings){
		this.name = name;
		this.type = type;
		this.userName = userName;
		this.password = password;
		this.checkingStatus = checkingStatus;
		this.savingStatus = savingStatus;
		this.checking = checking;
		this.savings = savings;
	}

	//////////////////////////
	////// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getCheckingStatus() {
		return checkingStatus;
	}

	public void setCheckingStatus(Status checkingStatus) {
		this.checkingStatus = checkingStatus;
	}

	public Status getSavingStatus() {
		return savingStatus;
	}

	public void setSavingStatus(Status savingStatus) {
		this.savingStatus = savingStatus;
	}

	public int getChecking() {
		return checking;
	}

	public void setChecking(int checking) {
		this.checking = checking;
	}

	public int getSavings() {
		return savings;
	}

	public void setSavings(int savings) {
		this.savings = savings;
	}
	
	
	//////////////////
	/////////////////	end of getters and setters

}

/*package com.rev.bankapp.main;


public class User{

	private String username, password, fname, lname;
	int accountNumber, roleID;
	
	
	private final int ACCESS_LEVEL = 0;

	public UserAccount(String username, String password, Person person) {


	}

	public UserAccount(String username, String password, String accountNumber, boolean accountReqSav, boolean accountReqCheck, Person person) {
		this.username = username;
		this.password = password;
		this.accountNumber = new StringBuffer(accountNumber);
		this.accountReqSav = accountReqSav;
		this.accountReqCheck = accountReqSav;
		this.person = person;

	}

	public void createCheckings(CheckingAccount ca){
		this.ca = ca;
	}
	
	public void createSavings(SavingAccount sa){
		this.sa = sa;
	}
	
	public CheckingAccount getChecking(){
		return ca;
	}
	public SavingAccount getSaving(){
		return sa;
	}
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public StringBuffer getAccountNumber() {
		return accountNumber;
	}

	public int getAccessLevel() {
		return ACCESS_LEVEL;
	}
	
	public Person getPerson(){
		return person;
	}

	public static void setStaticUserNum(int i) {
		userNum = i;
	}

	public boolean getCheckReqStat() {
		return accountReqCheck;
	}

	public boolean getSavReqStat() {
		return accountReqSav;
	}
	
	public void setCheckReq(boolean b){
		accountReqCheck = b;
	}
	
	public void setSavReq(boolean b){
		accountReqSav = b;
	}

	
	@Override
	public String toString(){
		return "USER: " + username + " Checking Request: " + accountReqCheck + " Savings Request: " + accountReqSav;
	}

	public void setUsername(String username) {
		this.username = username;
		
	}
	public void setPassword(String password){
		this.password = password;
	}

}
*/
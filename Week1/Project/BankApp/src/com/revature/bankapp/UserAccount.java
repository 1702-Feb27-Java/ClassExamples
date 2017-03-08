package com.revature.bankapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Person person;
	protected String username;
	protected String password;
	protected StringBuffer accountNumber = new StringBuffer("0000000000000000");
	protected static int userNum = 0;
	private boolean accountReqSav = false;
	private boolean accountReqCheck = false;
	private CheckingAccount ca = null;
	private SavingAccount sa = null;
	
	private final int ACCESS_LEVEL = 0;

	public UserAccount(String username, String password, Person person) {
		this.username = username;
		this.password = password;
		this.person = person;
		userNum++;
		String userN = "" + userNum;
		accountNumber.replace((accountNumber.length() - userN.length()), accountNumber.length(), userN);

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

package com.projectone;

public class UsernamePass  {
	
	private boolean SignedIn;

	
	private String sPassword;
	public UsernamePass()
	{
		
		
	}
	public UsernamePass(String sUsername,String sPassword)
	{
		this.sPassword= sPassword;
		this.sUserName= sUsername;
		
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public String getsUserName() {
		return sUserName;
	}
	public void setsUserName(String sUserName) {
		this.sUserName = sUserName;
	}
	public boolean isSignedIn() {
		return SignedIn;
	}
	public void setSignedIn(boolean signedIn) {
		SignedIn = signedIn;
	}
	private String sUserName;
	

}

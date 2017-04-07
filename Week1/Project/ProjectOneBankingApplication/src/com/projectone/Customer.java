package com.projectone;

public class Customer extends UsernamePass
{
	private String sFName;
	private String sLName;
	private int accountNum;

	private String AccountCheckOrSavings;	
	public String getAccountCheckOrSavings() {
		return AccountCheckOrSavings;
	}
	public void setAccountCheckOrSavings(String accountCheckOrSavings) {
		AccountCheckOrSavings = accountCheckOrSavings;
	}

	
    public boolean issApproved() {
		return sApproved;
	}
	public void setsApproved(boolean sApproved) {
		this.sApproved = sApproved;
	}
	private boolean sApproved; 
 
	public static int getCcount() {
		return ccount;
	}
	public static void setCcount(int ccount) {
		Customer.ccount = ccount;
	}
	private static int ccount;
	 Bank bank;
public Customer(String sFName, String sLName, int accountNum)
{	Main.customLogger.info("Customer Object Created");

	if(ccount>=1)
	{
		ccount ++;
		
	}
	else
	{
		ccount = 1;
		
	}
	

	this.sFName=sFName;
	this.sLName = sLName;
	this.accountNum= accountNum;
}
public Customer(String sFName, String sLName, int accountNum,String user , String pass)
{	Main.customLogger.info("Customer Object Created");

	if(ccount>=1)
	{
		ccount ++;
		
	}
	else
	{
		ccount = 1;
		
	}
	
	this.sFName=sFName;
	this.sLName = sLName;
	this.accountNum= accountNum;
	this.setsUserName(user);
	this.setsPassword(pass);
}
public Customer(String sFName, String sLName, int accountNum,String user , String pass,boolean sApproved)
{
	Main.customLogger.info("Customer Object Created");

	if(ccount>=1)
	{
		ccount ++;
		
	}
	else
	{
		ccount = 1;
		
	}
	this.sApproved = sApproved;
	this.sFName=sFName;
	this.sLName = sLName;
	this.accountNum= accountNum;
	this.setsUserName(user);
	this.setsPassword(pass);
}
public Customer(String sFName, String sLName, int accountNum,String user , String pass,boolean sApproved,String AccountCheckOrSavings)
{
	Main.customLogger.info("Customer Object Created\n");
	if(ccount>=1)
	{
		ccount ++;
		
	}
	else
	{
		ccount = 1;
		
	}
	this.AccountCheckOrSavings = AccountCheckOrSavings;
	this.sApproved = sApproved;
	this.sFName=sFName;
	this.sLName = sLName;
	this.accountNum= accountNum;
	this.setsUserName(user);
	this.setsPassword(pass);
}
 public String getsFName() {
		return sFName;
	}
	public void setsFName(String sFName) {
		this.sFName = sFName;
	}
	public String getsLName() {
		return sLName;
	}
	public void setsLName(String sLName) {
		this.sLName = sLName;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}


}

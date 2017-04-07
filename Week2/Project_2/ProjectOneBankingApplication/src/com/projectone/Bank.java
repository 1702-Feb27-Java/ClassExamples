package com.projectone;

public class Bank  {



	private double money;
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) throws Exception {
		if(money <0)
		{
			
			throw new Exception("Balance cannot be less than 0 ");
			
		}
		this.money = money;
	}
	private Admin admin;
	private Employee employee;
	private UsernamePass loginInfo;
	private Customer cCustomer;
	private String accountType;
	private String sCorS;
private int userid;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getAcctid() {
	return acctid;
}
public void setAcctid(int acctid) {
	this.acctid = acctid;
}
private int acctid;
	public Customer getCustomer()
	{
		return this.cCustomer;
		
	}
	public Admin getAdmin()
	{
		
		return this.admin;
	}
	public Employee getEmployee()
	{
		
		return this.employee;
	}
	public Bank()
	{
		Customer.class.getName();
	
	}
	public Bank(Customer c)
	{
		String 	s=c.getClass().getName();
		this.cCustomer= c;
		this.setAccountType("Customer");
	///c.bank.set

	}
	public Bank(Customer c, double money)
	{
		String 	s=c.getClass().getName();
		this.cCustomer= c;
		this.setAccountType("Customer");
		this.money=money;
	///c.bank.set

	}
	
	
	public Bank(Customer c, double money,int userid,int acctid)
	{
		String 	s=c.getClass().getName();
		this.cCustomer= c;
		this.setAccountType("Customer");
		this.money=money;
		this.userid = userid;
		this.acctid = acctid;
		
	///c.bank.set

	}
	public Bank (Employee e )
	{
		this.employee = e;
		this.setAccountType("Employee");
		
	}
	public Bank (Admin a)
	{
		
		
		this.admin = a;
		this.setAccountType("Admin");
	}

	
	public void  withdraw(double dWithdraw) throws Exception
	{	Main.customLogger.info("Money Withdrawn");

		
		if((this.money - dWithdraw)<0)
		{
			
			
			
		}
		else
		{
			this.money -= dWithdraw;
		}
		
		
	}
	public void deposit (double dDeposit) throws Exception
	{
		Main.customLogger.info("Money Deposited");
		if(( dDeposit)<0)
		{
			
			throw new Exception("You can not deposit a negative amount");
			
			
			
		}
		else
		{
			this.money += dDeposit;
		}
		
			
	   
	}
	public String getsCorS() {
		return sCorS;
	}
	public void setsCorS(String sCorS) {
		this.sCorS = sCorS;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



}

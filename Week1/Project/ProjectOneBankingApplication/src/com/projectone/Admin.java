package com.projectone;

public class Admin extends Employee
{

	public Admin()
	{
		Main.customLogger.info("Admin Object Created");
	}
	public Admin(String sFirst, String sLast) {
		super(sFirst, sLast);
		Main.customLogger.info("Admin Object Created");
	}
	public Admin(String sFirst, String sLast,String user,String pw) {
		super(sFirst, sLast,user,pw);
		Main.customLogger.info("Admin Object Created");
	}
	

}

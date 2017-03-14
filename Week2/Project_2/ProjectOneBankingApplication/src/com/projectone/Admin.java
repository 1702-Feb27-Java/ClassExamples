package com.projectone;
import com.project1.util.*;
public class Admin extends Employee
{

	private static BankDriver b = new BankDriver();
	
	public Admin()
	{
		b.logMessages("Admin Object Created");
		Main.customLogger.info("Admin Object Created");
	}
	public Admin(String sFirst, String sLast) {
		super(sFirst, sLast);
		b.logMessages("Admin Object Created");
		Main.customLogger.info("Admin Object Created");
	}
	public Admin(String sFirst, String sLast,String user,String pw) {
		super(sFirst, sLast,user,pw);
		b.logMessages("Admin Object Created");
		Main.customLogger.info("Admin Object Created");
	}
	

}

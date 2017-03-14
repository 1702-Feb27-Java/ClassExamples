package com.projectone;

public class Employee  extends UsernamePass{

private String sFirst ;
private String sLast;
private static BankDriver b = new BankDriver();
public Employee()
{
	b.logMessages("Employee Object Created");
	Main.customLogger.info("Employee Object Created");
}
public Employee (String sFirst, String sLast)
{b.logMessages("Employee Object Created");
	Main.customLogger.info("Employee Object Created");
	this.sFirst = sFirst;
	this.sLast = sLast;
}
public Employee (String sFirst, String sLast,String user,String pw)
{
	
	super(user,pw);Main.customLogger.info("Employee Object Created");
	this.sFirst = sFirst;
	this.sLast = sLast;
	b.logMessages("Employee Object Created");
}



public String getFirst() {
	return sFirst;
}
public void setFirst(String sFirst) {
	this.sFirst = sFirst;
}
public String getLast() {
	return sLast;
}
public void setLast(String sLast ) {
	this.sLast = sLast;
}


}

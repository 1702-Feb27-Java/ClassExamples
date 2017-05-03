package com.sqlbank.peoplepack;


public class Employee extends People 
{
    public Employee(int id, String fname, String lname, String username, String password, String role)
	{
		super(id, fname, lname, username, password, role);
		// TODO Auto-generated constructor stub
	}
	public void getMenu()
    {
    	System.out.println("=========== WELCOME " + this.getUsername()+ " ==========");
    	System.out.println("[1] VIEW ACCOUNTS");
    	System.out.println("[2] APPROVE ACCOUNTS");
    	System.out.println("[0] EXIT ");
    	System.out.println("Choose you option: ");
    }
    public void getOption(Customer c, String type)
    {
    	System.out.println("----------- Account Approval ------------------");
    	System.out.println("[1] Approve "+ c.getUsername() + "'s " + type + " account[s]" );
    	System.out.println("[2] Decline "+ c.getUsername() + "'s " + type + " account[s]" );
    	System.out.println("Anything else will exit");
    	System.out.println("Choose you option: ");
    }

    public void viewMyAccount ()
    {
    	System.out.println("********* ACCOUNT DETAILS FOR : " + this.getFname() + " " + this.getLname() + " ************");
    	System.out.println("USERNAME: " + this.getUsername());
    	System.out.println("PASSWORD: " + this.getPassword());
    	System.out.println("******************************************************************************************");
    }

    //CONSTRUCTORS ====================================================================
   
}
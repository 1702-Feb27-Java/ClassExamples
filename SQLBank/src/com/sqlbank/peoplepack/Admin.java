package com.sqlbank.peoplepack;


public class Admin extends People 
{
    //FROM THE PEOPLE CLASS ====================================================
    
    public Admin(int id, String fname, String lname, String username, String password, String role)
	{
		super(id, fname, lname, username, password, role);
		// TODO Auto-generated constructor stub
	}
	public void getMenu()
    {
    	System.out.println("=========== WELCOME " + this.getUsername()+" ==========");
    	System.out.println("[1] VIEW MY ACCOUNT");
    	System.out.println("[2] VIEW ACCOUNTS");
    	System.out.println("[3] VIEW PERSONS ACCOUNT");
    	System.out.println("[4] EDIT ACCOUNTS");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }
    public void getEditMenu(Customer cus)
    {
    	System.out.println("------------------ EDIT " + cus.getUsername() +"------------------");
    	System.out.println("[1] CHANGE USERNAME");
    	System.out.println("[2] CHANGE PASSWORD");
    	System.out.println("[3] CHANGE AN ACCOUNT BALANCE");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }
    public void getEditMenu2(Customer cus)
    {
    	System.out.println("------------------ EDIT " + cus.getUsername() +" ACCOUNT------------------");
    	System.out.println("[1] CHANGE USERNAME");
    	System.out.println("[2] CHANGE PASSWORD");
    	System.out.println("[3] CHANGE AN ACCOUNT BALANCE");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }

    public void getEditMenu(Employee emp)
    {
    	System.out.println("------------------ EDIT " + emp.getUsername() +"------------------");
    	System.out.println("[1] CHANGE USERNAME");
    	System.out.println("[2] CHANGE PASSWORD");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }
    public void getEditMenu(Admin a)
    {
    	System.out.println("------------------ EDIT " + a.getUsername() +"------------------");
    	System.out.println("[1] CHANGE USERNAME");
    	System.out.println("[2] CHANGE PASSWORD");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }
    
    public void viewMyAccount ()
    {
    	System.out.println("********* ACCOUNT DETAILS FOR : " + this.getFname() + " " + this.getLname() + " ************");
    	System.out.println("USERNAME: " + this.getUsername());
    	System.out.println("PASSWORD: " + this.getPassword());
    	System.out.println("******************************************************************************************");
    }
  
}


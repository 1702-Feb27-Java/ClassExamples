package com.sqlbank.peoplepack;

public class People
{
	private int id;
	private String fname;
    private String lname;
    private String username;
    private String password;
    private String role; 
    public final int getId()
	{
		return id;
	}

	public final void setId(int id)
	{
		this.id = id;
	}

    
    public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getFname()
	{
		return fname;
	}

	public void setFname(String fname)
	{
		this.fname = fname;
	}

	public String getLname()
	{
		return lname;
	}

	public void setLname(String lname)
	{
		this.lname = lname;
	}

	public String getPassword()
    {
    	return this.password;
    }

    public void setPassword(String newPass)
    {
    	this.password = newPass;
    }

    public String getUsername()
    {
    	return this.username;
    }
    
    public void setUsername(String newUser)
    {
    	this.username = newUser;
    }

    public void printAccount ()
    {

    	System.out.println("\nROLE: " + this.getRole());
    	System.out.println("\tFIRST NAME: " + this.getFname());
		System.out.println("\tLAST NAME: " + this.getLname());
		System.out.println("\t\tUSERNAME: " + this.getUsername());
		System.out.println("\t\tPASSWORD: " + this.getPassword());
    }

	public People(int id, String fname, String lname, String username, String password, String role)
	{
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.role = role;
	}

}


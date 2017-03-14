package com.sqlbank.peoplepack;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.bankservices.CustomerServices;

public class Customer extends People implements CustomerServices
{
	public Customer(int id, String fname, String lname, String username, String password, String role)
	{
		super(id, fname, lname, username, password, role);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Account> accounts;
    //IMPLEMENTED FOMR THE CUSTOMERSERVICE =======================================
    @Override 
    public boolean signUp(String username, String password)
    {
        return false;
    }

    @Override
    public boolean applyAccnt (String type)
    {
        return false;
    }

    @Override
    public int withdraw (int amount, String type)
    {
       return 0; 
    }

    @Override
    public int deposit (int amount, String type)
    {
    	return 0;
    }
    public void getMenu()
    {
	   System.out.println("============= WELCOME " + this.getUsername()+ " ============= ");
	   System.out.println("[1] VIEW ACCOUNT");
	   System.out.println("[2] CHANGE USERNAME");
	   System.out.println("[3] CHANGE PASSWORD");
	   System.out.println("[4] SIGN UP FOR ACCOUNT");
	   System.out.println("[5] DEPOSIT TO ACCOUNT");
	   System.out.println("[6] WITHDRAW FROM ACCOUNT");
	   System.out.println("[0] EXIT");
	   System.out.println("Choose an option: ");
    }
    public void updateAccount(String type)
    {

    }
    //CONSTRUCTORS =============================================================
}


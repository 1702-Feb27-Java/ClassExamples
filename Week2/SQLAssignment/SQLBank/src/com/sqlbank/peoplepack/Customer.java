package com.sqlbank.peoplepack;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.bankservices.CustomerServices;

public class Customer extends People implements CustomerServices
{
	public Customer(String fname, String lname, String username, String password, String role)
	{
		super(fname, lname, username, password, role);
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
    public static void getMenu(Customer c)
    {
	   System.out.println("============= WELCOME " + c.getUsername()+ " ============= ");
	   System.out.println("[1] SIGN UP FOR CHECKING ACCOUNT");
	   System.out.println("[2] SIGN UP FOR SAVINGS ACCOUNT");
	   System.out.println("[3] DEPOSIT TO CHECKING ACCOUNT");
	   System.out.println("[4] DEPOSIT TO SAVINGS ACCOUNT");
	   System.out.println("[5] WITHDRAW FROM CHECKING ACCOUNT");
	   System.out.println("[6] WITHDRAW FROM SAVINGS ACCOUNT");
	   System.out.println("[0] EXIT");
	   System.out.println("Choose an option: ");
    }
    public void updateAccount(String type)
    {

    }
    //CONSTRUCTORS =============================================================
}


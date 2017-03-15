package com.sqlbank.peoplepack;

import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;

public class Customer extends People 
{
	public Customer(int id, String fname, String lname, String username, String password, String role)
	{
		super(id, fname, lname, username, password, role);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Account> accounts;

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
    public void signAccount()
    {
    	System.out.println("================ CHOSE ACCOUNT TYPE =========================");
    	System.out.println("[1] SIGN UP FOR SAVINGS ACCOUNT");
    	System.out.println("[2] SIGN UP FOR CHECKING ACCOUNT");
    	System.out.println("[0] EXIT");
    	System.out.println("Choose an option: ");

    }
    //CONSTRUCTORS =============================================================
}


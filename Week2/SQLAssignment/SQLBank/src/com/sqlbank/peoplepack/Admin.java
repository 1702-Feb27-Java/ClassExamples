package com.sqlbank.peoplepack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.sqlbank.bankservices.AdminServices;

public class Admin extends People implements AdminServices
{
    //FROM THE PEOPLE CLASS ====================================================
    
    public Admin(String fname, String lname, String username, String password, String role)
	{
		super(fname, lname, username, password, role);
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
    
    ///VIEWACCOUNT FROM A SPECIFIC PERON ======================================================================
    //OVERRIDING THE ADMINSERVICES
    @Override
    public void viewAccounts (String person)
    {

    }
    //VIEWALL ACOUNTS FROM ALL USERS ============================================================================
    public void viewAccounts ()
    {
   
    }
    //EDIT THE USERNAME FOR A CUSTOMER WITH OPTION ==================================================================
    @Override
    public boolean editUsername (Customer name, String option)
    {

    	return false;
    }
    //EDIT THE EMPLOYEE FOR A CUSTOMER WITH OPTION ==================================================================
    @Override
    public boolean editUsername (Employee name, String option)
    {
    	return false;
    }
    //EDIT THE ADMIN FOR A CUSTOMER WITH OPTION ==================================================================
    @Override
    public boolean editUsername (Admin name, String option)
    {
    	return false;
    }
    //CHANGE PASSWORD FOR CUSTOMER ===================================================
    @Override
    public void editPassword (Customer name, String option)
    {

    }
    //CHANGE PASSWORD FOR EMPLOYEE ===================================================
    @Override
    public void editPassword (Employee name, String option)
    {
  
    }
    //CHANGE PASSWORD FOR ADMIN ===================================================
    @Override
    public void editPassword (Admin name, String option)
    {

    }
    //CONSTRUCTORS ==================================================
}


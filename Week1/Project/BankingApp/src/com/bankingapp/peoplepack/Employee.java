package com.bankingapp.peoplepack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.bankingapp.bankservices.EmployeeServices;

public class Employee extends People implements EmployeeServices
{
    //FIELDS ONLY THE CUSTOMER CLASS HAS =======================================
    public String getPassword()
    {
        return this.password;
    }

    public String getUsername()
    {
        return this.username;
    }
    
    public static void getMenu(Employee emp)
    {
    	System.out.println("=========== WELCOME " + emp.getUsername()+ " ==========");
    	System.out.println("[1] VIEW ACCOUNTS");
    	System.out.println("[2] APPROVE ACCOUNTS");
    	System.out.println("Choose you option: ");
    	
    }

    //OVERRIDES THE METHODS FROM THE EMPLOYEESERVICES INTERFACE
    //VIEWS ALL THE CUSTOMERS ON THE SYSTEM =================================================
    @Override
    public void viewCustomers()
    {
    	//OPEN FILE 
    	//VIEW EVERY ACCOUNT ON THE SSYSTEM
    	BufferedReader br =null;
    	try
		{
			br=new BufferedReader(new FileReader("logins.txt"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	String line;
    	try
		{
			while((line=br.readLine()) != null)
			{
				if (line.isEmpty())
				{
					continue;
				}
				if(line.startsWith("3"))
				{
					String[] fields = line.split(":");
					//ELSE PRINT OUT HIS INFORMATION	
					People.printAccount(fields);
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    //CHECKS TO SEE IF A CUSTOMER HAS A PENDING ACCOUNT
    public String checkAccounts(String name)
    {
    	String line;
    	String user = null;
    	int pendingCount = 0;
    	BufferedReader br =null;
    	try
		{
			br=new BufferedReader(new FileReader("logins.txt"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	try
		{
			while((line=br.readLine()) != null)
			{
				if (line.isEmpty())
				{
					continue;
				}
				String[] fields = line.split(":");
				//IF ITS NOT THE PERSON WE ARE LOOKING FOR SKIP
				if (!fields[1].equals(name))
					continue;
				
				//ELSE PRINT OUT HIS INFORMATION	
				user = line;
				//HAS A PENDING IN CHECKING
				if (fields[3].equals("P"))
				{
					pendingCount+=1;
				}
				//HAS A PENDING IN SAVINGS
				if (fields[4].equals("P"))
				{
					pendingCount+=2;
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	if(user != null )
    		user = user + "," + Integer.toString(pendingCount);
       	return user;
    }
    //APPROVES A CHECKING ACCOUNT ON A CUSTOMER ========================================
    @Override
    public boolean approveChkAccount(Customer c, boolean choice) 
    { 
    	c.setHasChecking(choice);
        return false;
    }
    //APPROVES A SAVINGS ACCOUNT ON A CUSTOMER ========================================
    @Override
    public boolean approveSavAccount(Customer c, boolean choice)
    { 
        c.setHasSavings(choice);
        return false;
    }
    //CONSTRUCTORS ====================================================================
    public Employee ( String username, String password)
    {
    	this.username = username;
    	this.password = password;
    }
    public Employee ( String whole)
    {
    	System.out.println(whole);
    	String[] values = whole.split(":");
    	this.username = values[1];
    	this.password = values[2];
    }
}


package com.bankingapp.peoplepack;

import com.bankingapp.bankaccount.CheckingAccount;
import com.bankingapp.bankaccount.SavingsAccount;
import com.bankingapp.bankservices.CustomerServices;

public class Customer extends People implements CustomerServices
{
    //FIELDS ONLY THE CUSTOMER CLASS HAS =======================================
    public CheckingAccount check;
    public SavingsAccount savings;
    private boolean hasChecking;
    private boolean hasSavings;

    //FROM THE PEOPLE CLASS ====================================================
    public String getPassword()
    {
        return this.password;
    }

    public String getUsername()
    {
        return this.username;
    }

    //OWN METHODS A CUSTOMER HAS ================================================
    public boolean getHasChecking ()
    {
        return this.hasChecking;
    }

    public void setHasChecking (boolean change)
    {
        this.hasChecking = change;
    }

    public boolean getHasSavings ()
    {
        return this.hasSavings;
    }

   public void setHasSavings (boolean change)
    {
        this.hasSavings = change;
    }

    //IMPLEMENTED FOMR THE CUSTOMERSERVICE =======================================
    @Override 
    public boolean signUp(String username, String password)
    {
        //CHECK FILES IF USERNAME DOES NOT APPEAR IN IT

            //return true;

        return false;
    }

    @Override
    public boolean applyAccnt (String type)
    {
        if (type.equals("Checking"))
        {
            //TODO NEED TO OPEN A REQUEST TO CRATE NEW ACCOUNT
            //NOT CREATE IT
            //this.check = new CheckingAccount();
        }
        else
        {
            //TODO NEED TO OPEN A REQUEST TO CRATE NEW ACCOUNT
            //NOT CREATE IT
            //this.avings new SavingsAccount();
        }
        return false;
    }

    @Override
    public boolean withdraw (int amount, String type)
    {
        if (type.equals("Checking"))
        {
            if (amount < ((this.check).getAmount()))
            {
                (this.check).addAmount(-1*amount);
                return true;
            }
        }
        else
        {
            if (amount < ((this.savings).getAmount()))
            {
                (this.savings).addAmount(-1*amount);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean deposit (int amount, String type)
    {
        if (type.equals("Checking"))
        {
            (this.check).addAmount(-1*amount);
            return true;
        }
        return false;
    }
    
   public static void getMenu(Customer c)
   {
	   System.out.println("============= WELCOME " + c.getUsername()+ " ============= ");
	   System.out.println("[1] SIGN UP FOR SAVINGS ACCOUNT");
	   System.out.println("[2] SIGN UP FOR CHECKING ACCOUNT");
	   System.out.println("[3] DEPOSIT TO CHECKING ACCOUNT");
	   System.out.println("[4] DEPOSIT TO SAVINGS ACCOUNT");
	   System.out.println("[5] WITHDRAW FROM CHECKING ACCOUNT");
	   System.out.println("[6] WITHDRAW FROM SAVINGS ACCOUNT");
   }

    //CONSTRUCTORS =============================================================
    public Customer (String uname, String password)
    {
        this.username = uname;
        this.password = password;
        this.hasChecking = false;
        this.hasSavings = false;
    }
    public Customer (String whole)
    {
    	String[] values = whole.split(":");
    	this.username = values[1];
    	this.password = values[2];
    	if(values[3].equals("N/A") || values[3].equals("P"))
    	{
    		this.setHasChecking(false);
    	} 
    	else
    	{
    		int res = Integer.parseInt(values[4]);
    		check = new CheckingAccount(res);
    		this.setHasChecking(true);
    	}
    	if(values[5].equals("N/A") || values[5].equals("P"))
    	{
    		this.setHasSavings(false);
    	}
    	else
    	{
    		savings = new SavingsAccount(Integer.parseInt(values[6]));
    		this.setHasSavings(true);
    	}
    }
}


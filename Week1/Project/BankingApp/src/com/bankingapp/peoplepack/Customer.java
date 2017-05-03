package com.bankingapp.peoplepack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.Line;

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
    	boolean pending;
        if (type.equals("Checking"))
        {
        	if (this.getHasChecking())
        		return false;
        	
        	pending = this.isPending(type);
        	
        	if (!pending)
        	{
        		this.updateAccount("C");
        		return true;
        	}
        }
        else
        {
        	if(this.getHasSavings())
        		return false;

        	pending = this.isPending(type);
        	
        	if (!pending)
        	{
        		this.updateAccount("S");
        		return true;
        	}
        }
        return false;
    }

    @Override
    public int withdraw (int amount, String type)
    {
        if (type.equals("Checking"))
        {
        	if(!this.getHasChecking())
        	{
        		return 2;
        	}
            if (amount < ((this.check).getAmount()))
            {
                (this.check).addAmount(-1*amount);
                return 0;
            }
        }
        else
        {
        	if(!this.getHasSavings())
        	{
        		return 2;
        	}
            if (amount < ((this.savings).getAmount()))
            {
                (this.savings).addAmount(-1*amount);
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int deposit (int amount, String type)
    {
        if (type.equals("Checking"))
        {
        	if(this.getHasChecking())
        	{
        		(this.check).addAmount(amount);
        		return 0;
        	}
        }
        else if (type.equals("Savings"))
        {
        	if(this.getHasSavings())
        	{
        		(this.savings).addAmount(amount);
        		return 0;
        	}
        }
        
       return 2;
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

    public boolean isPending(String type)
    {
    	String line = null;
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
				if(line.startsWith("3") )
				{
					//IF ITS NOT THE PERSON WE ARE LOOKING FOR SKIP
					if (!fields[1].equals(this.getUsername()))
						continue;
				
					//HAS A PENDING IN CHECKING
					if(type.equals("Checking"))
					{
						if (fields[3].equals("P"))
						{
							br.close();
							return true;
						}
					}
					else
					{
						//HAS A PENDING IN SAVINGS
						if (fields[5].equals("P"))
						{
							br.close();
							return true;
						}
					}
				}		
			}	
			br.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	return false;
    }
    //
    public void updateAccount(String type)
    {
    	//OPEN FILE 
    	//VIEW EVERY ACCOUNT ON THE SSYSTEM
    	BufferedReader br =null;
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	try
		{
			br=new BufferedReader(new FileReader("logins.txt"));
			fw = new FileWriter("loginstemp.txt");
			bw = new BufferedWriter(fw);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}catch (IOException e)
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
				String[] fields = line.split(":");
				if(line.startsWith("3") && fields[1].equals(this.getUsername()))
				{
					if(type.equals("C"))
					{
						bw.write("3:" + this.getUsername() + ":" +this.getPassword() + ":P:N/A" + fields[5]+ ":" +  fields[6]);
						System.out.println("3:" + this.getUsername() + ":" +this.getPassword() + ":P:N/A" + fields[5]+ ":" +  fields[6]);
					}
					else if(type.equals("S"))
					{
						bw.write("3:" + this.getUsername() + ":" +this.getPassword()+ ":" + fields[3]+ ":"+ fields[4] + ":P:N/A");
						System.out.println("3:" + this.getUsername() + ":" +this.getPassword()+ ":" + fields[3]+ ":"+ fields[4] + ":P:N/A");
					}
				}
				else
				{
					bw.write(line+"\n");
				}
			}
				bw.close();
				br.close();
				fw.close();
				People.updateFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
    	
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
    		this.check.setAccountNumber(this.getUsername().hashCode());
    		this.setHasChecking(true);
    	}
    	if(values[5].equals("N/A") || values[5].equals("P"))
    	{
    		this.setHasSavings(false);
    	}
    	else
    	{
    		savings = new SavingsAccount(Integer.parseInt(values[6]));
    		this.savings.setAccountNumber(this.getUsername().hashCode()+1);
    		this.setHasSavings(true);
    	}
    }
}


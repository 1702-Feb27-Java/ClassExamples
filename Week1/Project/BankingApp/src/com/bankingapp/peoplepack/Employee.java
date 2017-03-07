package com.bankingapp.peoplepack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.bankingapp.bankaccount.CheckingAccount;
import com.bankingapp.bankaccount.SavingsAccount;
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
    	System.out.println("[0] EXIT ");
    	System.out.println("Choose you option: ");
    }
    public static void getOption(Customer c, String type)
    {
    	System.out.println("----------- Account Approval ------------------");
    	System.out.println("[1] Approve "+ c.getUsername() + "'s " + type + " account[s]" );
    	System.out.println("[2] Decline "+ c.getUsername() + "'s " + type + " account[s]" );
    	System.out.println("Anything else will exit");
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
				if (fields[5].equals("P"))
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
    public void approveChkAccount(Customer c, boolean choice) 
    { 
    	c.setHasChecking(choice);
    	if(choice)
    	{
    		c.check = new CheckingAccount(0);
        	c.check.setAccountNumber(c.getUsername().hashCode()+1);
    	}
    }
    //APPROVES A SAVINGS ACCOUNT ON A CUSTOMER ========================================
    @Override
    public void approveSavAccount(Customer c, boolean choice)
    { 
        c.setHasSavings(choice);
        if(choice)
        {
        	c.savings = new SavingsAccount(0);
        	c.savings.setAccountNumber(c.getUsername().hashCode()+1);
        }
    }
    
    public void updateFile(Customer c)
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
				if(line.startsWith("3"))
				{
					String[] fields = line.split(":");
					if(fields[1].equals(c.getUsername()))
					{
						String newUser= null;
						if (c.getHasChecking() && c.getHasSavings())
						{
							newUser = "3:"+c.getUsername()+":"+c.getPassword()+":"+c.check.getAccountNumber()+":"+c.check.getAmount()+
											 ":" + c.savings.getAccountNumber() + ":" + c.savings.getAmount()+"\n";
						}
						else if (c.getHasChecking())
						{
							newUser = "3:"+c.getUsername()+":"+c.getPassword()+":"+c.check.getAccountNumber()+":"+c.check.getAmount() +
												":N/A:N/A\n";
						}
						else if (c.getHasSavings())
						{
							newUser = "3:"+c.getUsername()+":"+c.getPassword()+":N/A:N/A:" +c.savings.getAccountNumber()+":"+c.savings.getAmount()+"\n";
						}
						else
							newUser = "3:"+c.getUsername()+":"+c.getPassword()+":N/A:N/A:N/A:N/A\n";
					
						bw.write(newUser);
						System.out.println("ADDED USER: " + newUser);
					}
					else
					{
						bw.write(line+"\n");
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
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
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


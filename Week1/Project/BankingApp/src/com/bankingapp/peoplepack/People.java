package com.bankingapp.peoplepack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class People
{
    String password;
    String username;

    abstract String getPassword();

    final void setPassword(String newPass)
    {
    	this.password = newPass;
    }

    abstract String getUsername();
    
    final void setUsername(String newUser)
    {
    	this.username = newUser;
    }

    public static void getMenu(){}

    //Login for portal with
    //USERNAME a username
    //PASSWORD for their password
    //
    //TRUE if login succesful
    //FALSE if error occurs
    final static public String login (String username, String password)
    {
    	BufferedReader br = null;
    	try
		{
			br=new BufferedReader(new FileReader("logins.txt"));
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String line;
    	try
		{
			while((line=br.readLine()) != null)
			{
				if (line.isEmpty())
					continue;
				String[] fields = line.split(":");
				if (fields[1].equals(username) && fields[2].equals(password))
				{
					br.close();
					System.out.println("TRUE");
					return fields[0];
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	return "0";
    }
    final static public boolean ifExists (String username)
    {
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
				String[] fields = line.split(":");
				if (fields[1].equals(username))
				{
					br.close();
					return true;
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	return false;
    }
    
    final static void printAccount (String [] fields)
    {
    	for (int i = 0; i < fields.length; i++)
		{
			switch (i)
			{
				case 0:
					switch (fields[i])
					{
						case "1":
							System.out.println("ADMIN");
							break;
						case "2":
							System.out.println("EMPLOYEE");
							break;
						case "3":
							System.out.println("CUSTOMER");
							break;
					}
					break;
				case 1:
					System.out.println("\t Username: " + fields[i]);
					break;
				case 2:
					System.out.println("\t Password: " + fields[i]);
					break;
				case 3:
					System.out.println("\t\t CheckingAccount: " + fields[i]);
					break;
				case 4:
					System.out.println("\t\t\t Balance: " + fields[i]);
					break;
				case 5:
					System.out.println("\t\t SavingsAccount: " + fields[i]);
					break;
				case 6:
					System.out.println("\t\t\t Balance: " + fields[i]);
					break;
			}
		}	
    }
}


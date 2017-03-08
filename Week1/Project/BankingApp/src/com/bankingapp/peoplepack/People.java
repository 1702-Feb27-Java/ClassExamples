package com.bankingapp.peoplepack;

import java.awt.image.ReplicateScaleFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
//					System.out.println(line);
					return line;
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
   
    final static public void pushToFile (String s)
    {
    	String [] values = s.split(":");
		FileWriter fw = null;
		try
		{
			fw = new FileWriter("loginstemp.txt");
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
    	BufferedReader br =null;
    	String userToChange= null;
    	try
		{
			br=new BufferedReader(new FileReader("logins.txt"));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
    	String line;
    	String[] fields= null;
    	try
		{
			while((line=br.readLine()) != null)
			{
				if (line.isEmpty())
				{
					continue;
				}
				fields = line.split(":");
				if(fields[0].equals(values[0]) && fields[1].equals(values[1]))
				{
					bw.write(s+"\n");
				}
				else
				{
					bw.write(line+"\n");
				}
			}
			People.updateFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    final static public void updateFile ()
    {
		File oldfile = new File("logins.txt");
		File updatedFile = new File("loginstemp.txt");
		

		Path oldpath;
		Path updatedPat;
		try
		{ 
			oldpath = Paths.get(oldfile.getCanonicalPath());
			Path source = Files.move(oldpath, oldpath.resolveSibling("old.txt"), StandardCopyOption.REPLACE_EXISTING);
			if(updatedFile.exists())
			{
				updatedPat = Paths.get(updatedFile.getCanonicalPath());
				Path source2 = Files.move(updatedPat, updatedPat.resolveSibling("logins.txt"), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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


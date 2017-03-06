package com.bankingapp.peoplepack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.bankingapp.bankservices.AdminServices;

public class Admin extends People implements AdminServices
{
    //FROM THE PEOPLE CLASS ====================================================
    public String getPassword()
    {
        return this.password;
    }

    public String getUsername()
    {
        return this.username;
    }
    
    public static void getMenu(Admin admin)
    {
    	System.out.println("=========== WELCOME " + admin.getUsername()+" ==========");
    	System.out.println("[1] VIEW ACCOUNTS");
    	System.out.println("[2] VIEW PERSONS ACCOUNT");
    	System.out.println("[3] EDIT ACCOUNTS");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }
    public static void getEditMenu(Customer cus)
    {
    	System.out.println("------------------ EDIT " + cus.getUsername() +"------------------");
    	System.out.println("[1] CHANGE USERNAME");
    	System.out.println("[2] CHANGE PASSWORD");
    	System.out.println("[3] CHANGE CHECKING BALANCE");
    	System.out.println("[4] CHANGE SAVINGS BALANCE");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }

    public static void getEditMenu(Employee emp)
    {
    	System.out.println("------------------ EDIT " + emp.getUsername() +"------------------");
    	System.out.println("[1] CHANGE USERNAME");
    	System.out.println("[2] CHANGE PASSWORD");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }
    public static void getEditMenu(Admin a)
    {
    	System.out.println("------------------ EDIT " + a.getUsername() +"------------------");
    	System.out.println("[1] CHANGE USERNAME");
    	System.out.println("[2] CHANGE PASSWORD");
    	System.out.println("[0] EXIT");
    	System.out.print("Choose your option: ");
    }
    
    ///VIEWACCOUNT FROM A SPECIFIC PERON ======================================================================
    //OVERRIDING THE ADMINSERVICES
    @Override
    public void viewAccounts (String person)
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
				String[] fields = line.split(":");
				//IF ITS NOT THE PERSON WE ARE LOOKING FOR SKIP
				if (!fields[1].equals(person))
					continue;
				
				//ELSE PRINT OUT HIS INFORMATION	
				People.printAccount(fields);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    //VIEWALL ACOUNTS FROM ALL USERS ============================================================================
    public void viewAccounts ()
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
				//ELSE PRINT OUT HIS INFORMATION	
				People.printAccount(fields);
				
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    //EDIT THE USERNAME FOR A CUSTOMER WITH OPTION ==================================================================
    @Override
    public boolean editUsername (Customer name, String option)
    {
    	
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
				if(fields[1].equals(option))
				{
					return false;
				}
				else if (fields[1].equals(name.getUsername()))
				{
					userToChange = line;
				}
				else
				{
					bw.write(line+"\n");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	if(userToChange != null)
    	{
    		String[] split = userToChange.split(":");
    		name.setUsername(option);
    		split[1] = option;
    		userToChange = String.join(":", split);
    		System.out.println(userToChange);
    		try
			{
				bw.write(userToChange);
				bw.close();
				br.close();
				fw.close();
    			File old = new File("logins.txt");
    			if(old.delete())
    			{
    				System.out.println("Deleted correctly");
    			}
    			//Files.delete(old.toPath());;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return true;
    	}
    	return false;
    }
    //EDIT THE EMPLOYEE FOR A CUSTOMER WITH OPTION ==================================================================
    @Override
    public boolean editUsername (Employee name, String option)
    {
    	
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
				if(fields[1].equals(option))
				{
					return false;
				}
				else if (fields[1].equals(name.getUsername()))
				{
					userToChange = line;
				}
				else
				{
					bw.write(line+"\n");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	if(userToChange != null)
    	{
    		String[] split = userToChange.split(":");
    		split[1] = option;
    		name.setUsername(option);
    		userToChange = String.join(":", split);
    		System.out.println(userToChange);
    		try
			{
				bw.write(userToChange);
				bw.close();
				br.close();
				fw.close();
    			File old = new File("logins.txt");
    			if(old.delete())
    			{
    				System.out.println("Deleted correctly");
    			}
    			//Files.delete(old.toPath());;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return true;
    	}
    	return false;
    }
    //EDIT THE ADMIN FOR A CUSTOMER WITH OPTION ==================================================================
    @Override
    public boolean editUsername (Admin name, String option)
    {
    	
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
				if(fields[1].equals(option))
				{
					return false;
				}
				else if (fields[1].equals(name.getUsername()))
				{
					userToChange = line;
				}
				else
				{
					bw.write(line+"\n");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	if(userToChange != null)
    	{
    		String[] split = userToChange.split(":");
    		split[1] = option;
    		name.setUsername(option);
    		userToChange = String.join(":", split);
    		System.out.println(userToChange);
    		try
			{
				bw.write(userToChange);
				bw.close();
				br.close();
				fw.close();
    			File old = new File("logins.txt");
    			if(old.delete())
    			{
    				System.out.println("Deleted correctly");
    			}
    			//Files.delete(old.toPath());;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return true;
    	}
    	return false;
    }
    //CHANGE PASSWORD FOR CUSTOMER ===================================================
    @Override
    public void editPassword (Customer name, String option)
    {
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
				if(fields[1].equals(name.getUsername()) && fields[2].equals(name.getPassword()))
				{
					userToChange = line;
				}
				else
				{
					bw.write(line+"\n");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	if(userToChange != null)
    	{
    		String[] split = userToChange.split(":");
    		split[2] = option;
    		name.setPassword(option);
    		userToChange = String.join(":", split);
    		System.out.println(userToChange);
    		try
			{
				bw.write(userToChange);
				bw.close();
				br.close();
				fw.close();
    			File old = new File("logins.txt");
    			if(old.delete())
    			{
    				System.out.println("Deleted correctly");
    			}
    			//Files.delete(old.toPath());;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}	
    }
    //CHANGE PASSWORD FOR EMPLOYEE ===================================================
    @Override
    public void editPassword (Employee name, String option)
    {
    	
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
				if(fields[1].equals(name.getUsername()) && fields[2].equals(name.getPassword()))
				{
					userToChange = line;
				}
				else
				{
					bw.write(line+"\n");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	if(userToChange != null)
    	{
    		String[] split = userToChange.split(":");
    		split[2] = option;
    		name.setPassword(option);
    		userToChange = String.join(":", split);
    		System.out.println(userToChange);
    		try
			{
				bw.write(userToChange);
				bw.close();
				br.close();
				fw.close();
    			File old = new File("logins.txt");
    			if(old.delete())
    			{
    				System.out.println("Deleted correctly");
    			}
    			//Files.delete(old.toPath());;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    //CHANGE PASSWORD FOR ADMIN ===================================================
    @Override
    public void editPassword (Admin name, String option)
    {
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
				if(fields[1].equals(name.getUsername()) && fields[2].equals(name.getPassword()))
				{
					userToChange = line;
				}
				else
				{
					bw.write(line+"\n");
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	if(userToChange != null)
    	{
    		String[] split = userToChange.split(":");
    		split[2] = option;
    		name.setPassword(option);
    		userToChange = String.join(":", split);
    		System.out.println(userToChange);
    		try
			{
				bw.write(userToChange);
				bw.close();
				br.close();
				fw.close();
    			File old = new File("logins.txt");
    			if(old.delete())
    			{
    				System.out.println("Deleted correctly");
    			}
    			//Files.delete(old.toPath());;
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}	
    }
    //CONSTRUCTORS ==================================================
    public Admin (String username, String password)
    {
    	this.password = password;
    	this.username = username;
    }
    public Admin (String whole)
    {
    	String[] values = whole.split(":");
    	this.password = values[2];
    	this.username = values[1];
    }
    
}


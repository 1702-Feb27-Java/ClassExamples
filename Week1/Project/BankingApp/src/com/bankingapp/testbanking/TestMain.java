package com.bankingapp.testbanking;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankingapp.peoplepack.Admin;
import com.bankingapp.peoplepack.Customer;
import com.bankingapp.peoplepack.Employee;



public class TestMain
{
    public static void welcome ()
    {
        System.out.println("=========== BELCOME TO THE BANK APP =========");
        System.out.print("Do you have login credentials [Y/N]: ");
    }
    
    public static void choosePersonType()
    {
        System.out.println("=========== CHOOSE TYPE =========");
        System.out.println("[1] An Admin");
        System.out.println("[2] An Employee");
        System.out.println("[3] A Customer");
        System.out.print("What are you?: ");
    }

    public static String loginScreen(String type)
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("=========== LOGIN TO THE BANK APP =========");
        System.out.print("ENTER YOUR USERNAME: ");
        String username = sc.next();
        System.out.print("ENTER YOUR PASSWORD: ");
        String password = sc.next();
        return type+":"+username+":"+password;
    }
    
	static final Logger l = Logger.getRootLogger();
    //====================================== MAIN =================================== 
    public static void main (String[] args)
    {
    	Admin admin = null;
    	Employee emp = null;
    	Customer cus = null;
        Scanner sc = new Scanner(System.in);
        String in; 
        welcome();
        in = sc.next();

        while (!in.equals("Y") && !in.equals("N"))
        {
        	System.out.println("\nYou did not answer correctly");
            welcome();
            in = sc.next();
        }

        if (in.equals("Y"))
        {
        	choosePersonType();
        	in = sc.next();
        	while (!in.equals("1") && !in.equals("2") && !in.equals("3"))
        	{
        		System.out.println("\nYou did not answer correctly");
        		choosePersonType();
        		in = sc.next();
        	}
            String check;
            boolean correctLogin=false;
           // System.out.println(in);
           	check = loginScreen(in);
           	System.out.println(check);
           	String []user = check.split(":");
           	if (user[0].equals("1"))
           	{
           		admin = new Admin(user[1], user[2]);
           	//	correctLogin = admin.login(user[0],user[1], user[2]);
           	}
           	else if (user[0].equals("2"))
           	{
           		emp = new Employee(user[1], user[2]);
           	//	correctLogin = emp.login(user[0],user[1], user[2]);
           	}
            else
           	{
           		cus = new Customer(user[1], user[2]);
           	//	correctLogin = cus.login(user[0],user[1], user[2]);
           	}
           	if (!correctLogin)
           	{
           		System.out.println("\nYOU HAVE ENTERED A WRONG USERNAME/PASSWORD OR YOU ARE THAT TYPE OF PERSON ");
           		System.out.println("PLEASE RUN PROGRAM AGAIN");
           		System.exit(0);
           	}
    		sc = new Scanner(System.in);   
           do
           {
        	   
        	   switch (user[0])
        	   {
        		   case "1":
        			   admin.getMenu();
        			   break;
        		   case "2":
        			   break;
        		   case "3":
        			   break;
        	   }
        	   
        	   
           }while (true);
        }

    	//l.trace("This is the least concerned level of a log");
		//l.debug("Tipically used for ... debugging");
		//l.info("Used when you wish to provide fun info about a section of the code");
		//l.warn("Possible chance of a program failure in this section");
		//l.error("Error in code at this point, usually terminates program");
		//l.fatal("This level of log usualy is resered for unrecorebale issues");
        //
        sc.close();
    }
}

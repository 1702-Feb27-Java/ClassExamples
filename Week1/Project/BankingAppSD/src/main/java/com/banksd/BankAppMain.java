package com.banksd;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.banksd.bank.Bank;

public class BankAppMain {
	public static final Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//log.trace("TRACE : Banking Application Launched");
		log.debug("main : Bank");
		Bank bBank = new Bank();
		Scanner scan = new Scanner(System.in);
		int iIn = 0;
		do{
		System.out.println("=====================================");
		System.out.println("Welcome to D-Bank");
		System.out.println("=====================================");	
		System.out.println("1.Sign-up");
		System.out.println("2.Register");
		System.out.println("3.Bank Details");
		System.out.println("4.Bank Queries / Information");
		System.out.println("5.Exit");
		System.out.println("=====================================");	
		System.out.println("Select the options : ");
		String sIn=null;
		try{
			sIn= scan.nextLine();
			
			//User input can be Numbers [1 - 3] as options for selection
		 	while(Pattern.matches("^[1-5]", sIn) ==false){
		 		System.out.println("Enter options [1-5] : ");
		 		sIn= scan.next();
		 	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		iIn = Integer.parseInt(sIn);
		switch(iIn){
		case 1:	System.out.println("Login creadentials : ");
				boolean iRet=false;
				String  sUserName="";
				do{
				System.out.println("Username : ");
				sUserName = scan.nextLine();
				if(sUserName.equals(""))iRet=true;
				}while(iRet==true);
					
				String  sPwd ="";
				iRet=false;
				do{
				System.out.println("Password : ");
				sPwd = scan.nextLine();
				if(sPwd.equals(""))iRet=true;
				}while(iRet==true);
				
				log.trace("main : Sign Up : "+ sUserName);
				BankServices.initView(sUserName,sPwd);
				break;
				
		case 2:	boolean bRet = BankServices.registerUsers();
				if(bRet==true){
					System.out.println("Registered Successfully...");
					log.trace("main : Registered Successfully");
				}
				else
					log.error("main : Failed Account Registration");
				break;
		case 3: if(bBank !=null)
					bBank.DisplayBankDetails();
					log.info("main : Bank Details displayed");
				break;
		case 4: if(bBank !=null)
					bBank.DisplayBankInfo();
					log.info("main : Display Additional Bank Information / Queries");
				break;
		case 5:	System.out.println("****** Thank you for using our Application.******"); 
				log.info("main : End of Application");
				break;
		default:System.out.println("Default");
		}
		}while(iIn != 5);
	}
}

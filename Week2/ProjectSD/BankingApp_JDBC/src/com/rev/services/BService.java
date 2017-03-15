package com.rev.services;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.rev.dao.BankImp;
import com.rev.pojo.BAccount;
import com.rev.pojo.Users;

public class BService {
	private static Scanner sScanInput = new Scanner(System.in);
	static BankImp bankImp = new BankImp();
	
	public static boolean runApp(){
		int iIn = 0;
		do{
		System.out.println("=====================================");
		System.out.println("Welcome to D-Bank");
		System.out.println("=====================================");	
		System.out.println("1.Sign-up");
		System.out.println("2.Register");
		System.out.println("3.Exit");
		System.out.println("=====================================");	
		System.out.println("Select the options : ");
		String sIn=null;
		try{
			sIn= sScanInput.nextLine();
			
			//User input can be Numbers [1 - 3] as options for selection
		 	while(Pattern.matches("^[1-3]", sIn) ==false){
		 		System.out.println("Enter options [1-3] : ");
		 		sIn= sScanInput.next();
		 	}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		iIn = Integer.parseInt(sIn);
		switch(iIn){
		case 1:	System.out.println("Login credentials :\nUsername : ");
				boolean bCheck=false;
				String  sUserName=""; String  sPwd ="";
				boolean iRet=false;
				do{	
					//Enter the Username
					iRet=false;
					do{
					//System.out.println("Username : ");
					sUserName = sScanInput.nextLine();
					if(sUserName.length()<=0){
						iRet=true;}
					else
						iRet=false;
					}while(iRet==true);
						
					//Enter the Password
					iRet=false;
					do{
					System.out.println("Password : ");
					sPwd = sScanInput.nextLine();
					if(sPwd.equals(""))iRet=true;
					}while(iRet==true);
					
					bCheck = checkLogin(sUserName, sPwd);
					if(bCheck==false){
						System.out.println();
						System.out.println("Incorrect Username / Password. Please Re-Enter : ");
					}
				}while(bCheck==false);
				
				initView(sUserName,sPwd);
				break;
				
		case 2:	boolean bRet =	createProfileView();
				if(bRet==true){
					System.out.println("Registered Successfully...");
				}
				break;
		case 3:	System.out.println("****** Thank you for using our Application.******"); 
				
				break;
		default:System.out.println("Default");
		}
		}while(iIn != 3);
		return false;
	}
	
	public static boolean createProfileView(){
		
		System.out.println("----REGISTER---");
		boolean bRet=true, bIsNull=false;
		String fName="", lName="", iName=null, iPswd="", iRole="";
		
		do{	
		System.out.println("Username :  ");
		iName = sScanInput.nextLine();
		if(iName.equals(""))
			bIsNull=true;

		//Check if User already Exists
		String iUserEx = bankImp.getUser(iName);
		if(iUserEx.equals(iName))
		{
			System.out.println("User Already Exists.");
			bRet=true;
		}
		else
			break;
		}while(bRet==true || bIsNull==false);

		bRet=false;
		do{
		System.out.println("Password :  ");
		iPswd = sScanInput.nextLine();
		if(iPswd.equals(null))bRet=true;
		}while(bRet==true);
		
		bRet=false;
		do{
		System.out.println("First Name : ");
		fName = sScanInput.nextLine();
		if(fName.equals(""))bRet=true;
		}while(bRet==true);
		
		bRet=false;
		do{
		System.out.println("Last Name : ");
		lName = sScanInput.nextLine();
		if(lName.equals(""))bRet=true;
		}while(bRet==true);
		
		bRet=false;
		do{
		System.out.println("User type [2. Employee, 3. Customer] Select (1/2) : ");
		iRole = sScanInput.nextLine();
		if(iRole.equals(""))bRet=true;
		}while(bRet==true);

		int iR = Integer.parseInt(iRole);
		boolean bState = bankImp.regUser(fName,lName,iName,iPswd,iR);
		if(bState == true)
			System.out.println("Registered successfully");
		System.out.println();
		return false;
	}
	
	public static boolean checkLogin(String isUser, String isPwd){
		boolean bRet = bankImp.checkLogin(isUser, isPwd);
		if(bRet==false)
			System.out.println("User does not exists.");
		return bRet;
	}
	
	public static boolean initView(String isUser, String isPwd){
		System.out.println();
		System.out.println("  *****Welcome "+isUser+" *****");
		Users uI = bankImp.getUserDetails(isUser);
		int iIn=0;
		
		do{
		switch(uI.getRoleID()){
			case 3 : 	  System.out.println("*****CUSTOMER SERVICES*****");
						  System.out.println("1. Open Account\n2. Close Account\n3. View Status");
						  System.out.println("4. Desposit\n5. Withdraw\n6. Sign Out");
						  System.out.println("Please select an option : ");
						  Scanner sc =new Scanner(System.in);
						  String sIn=null;
						  try{
								sIn= sc.nextLine();
							 	while(Pattern.matches("^[1-6]", sIn) ==false){
							 		System.out.println("Enter options [1-6] : ");
							 		sIn= sc.nextLine();
							 	}
								
							}catch(Exception e){
								e.printStackTrace();
						  }
							
						  iIn = Integer.parseInt(sIn);
						  switch(iIn){
						    case 1: createAcc(uI.getUserName());	
						    		break;
							case 2:	reqClosure(uI.getUserName());					
									break;
							case 3: BAccount bAcc = bankImp.getAccount(isUser);
									viewStatus(	bAcc.getAccNo(),
												bAcc.getAccBal(),
												bAcc.getAccType(),
												bAcc.getTask(), 
												bAcc.getStatusID());		
									break;
							case 4: reqDeposit(uI.getUserName());					
									break;
							case 5: reqWidthdrawal(uI.getUserName());				
									break;
							default:break;
						  }
						  System.out.println();
						  break;
							
			case 2   :  	iIn =initEmp(uI.getUserName());
							break;
			case 1   :      break;
			default  :      System.out.println("ERROR OCCURRED");
							iIn = 6;
							break;
		  }	//switch
		}while(iIn!=6);
		
		System.out.println("***** Thank you for using our service *****");
		System.out.println();
		
		return true;
	}
	
	public static void createAcc(String uUserN){
		System.out.println("Select Type of Account [1. Checking, 2. Savings] : ");
		String iType = sScanInput.nextLine();
		int iCh = Integer.parseInt(iType);
		
		bankImp.createAcc(uUserN, iCh, 0);
	}
	
	public static void reqClosure(String uUserN){
		bankImp.deleteAcc(uUserN);
	}
	
	public static void  viewStatus(int iAccNo, double dAmt, int iAccType,String task, int iStatus){
		if(task!=null && iStatus==1){
		String[] aTask = task.split("/");
		if(aTask.length>1){
			iAccNo=-1;
		}
		}
		System.out.println("----------ACCOUNT DETAILS--------------");
		if(iAccNo==0)
			System.out.println("No Account / Information found");
		else if(iAccNo==-1)
			System.out.println("Account Request Pending");
		else
		{
			System.out.println("ACCOUNT_NO\tBALANCE");
			System.out.println(iAccNo+"\t\t"+dAmt);
		}
		
		System.out.println("----------------------------------------");
	}
	
	public static void reqDeposit(String iUserN){
		System.out.println("Please enter the account : ");
		String iA = sScanInput.nextLine();
		double iAmt = Double.parseDouble(iA);
		bankImp.updateAcc(iUserN, iAmt);
	}

	public static void  reqWidthdrawal(String iUserN){
		System.out.println("Please enter the account : ");
		String iA = sScanInput.nextLine();
		double iAmt = Double.parseDouble(iA);
		bankImp.updateAcc(iUserN, (iAmt + (-(2*iAmt))));
	}
	
	public static int initEmp(String iUserL)
	{
		  System.out.println("*****EMPLOYEE SERVICES*****");
		  System.out.println("1. View pending applications\n2. Approve Account\n3. Approve Closure");
		  System.out.println("4. Approve Desposits\n5. Approve Withdrawal\n6. Sign Out");
		  System.out.println("Please select an option : ");
		  Scanner sc =new Scanner(System.in);
		  String sIn="";
		  int iIn=0;
			try{
				sIn= sc.nextLine();
			 	while(Pattern.matches("^[1-6]", sIn) ==false){
			 		System.out.println("Enter options [1-6] : ");
			 		sIn= sc.nextLine();
			 	}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			iIn = Integer.parseInt(sIn);
			switch(iIn){
			case 1: viewPendingApp(iUserL);
					break;
			case 2: updateApp(iUserL);
					break;
			case 3: appClosure(iUserL);
					break;
			case 4: appDeposit(iUserL);
					break;
			case 5: appWithdraw(iUserL);
					break;
			default:break;
			}
			System.out.println();
	return iIn;
	}
	
	public static void viewPendingApp(String iUserL){
		ArrayList<BAccount> bAcc = bankImp.getAccounts(1);
		int iCount=0;
		System.out.println("------------PENDING ACCOUNTS------------");
		System.out.println("No.\t\tACCOUNT_NO");
		System.out.println("----------------------------------------");
		for(BAccount b : bAcc){
			System.out.println((++iCount)+"\t\t"+b.getAccNo());
		}
		if(iCount==0)
			System.out.println("No pending accounts");
		System.out.println("----------------------------------------");
	}
	
	public static void updateApp(String iUserL){
		ArrayList<BAccount> bAcc = bankImp.getAccounts(1);
		
		for(BAccount b: bAcc){
			String task = b.getTask();
			String status = "";
			double iAmt = 0;
			String[] aTask = task.split("/");
			if(aTask.length>1){
				status=aTask[0];
				iAmt=Double.parseDouble(aTask[1]);
				iAmt+=b.getAccBal();
			}
			else
				status = task;
			
			bankImp.createAccA(b.getAccNo(), iAmt,2,iUserL);
		}
	}
	
	public static void appDeposit(String iUserL){
		ArrayList<BAccount> bAcc = bankImp.getAccounts(1);
		for(BAccount b: bAcc){
			String task = b.getTask();
			String status = "";
			double iAmt = 0;
			String[] aTask = task.split("/");
			if(aTask.length>1){
				continue;
			}
			else{
				iAmt = Double.parseDouble(task);
				iAmt+=b.getAccBal();
			}
			bankImp.updateApp(b.getAccNo(), 2, iAmt,iUserL);
		}
	}
	
	public static void appWithdraw(String iUserL){
		ArrayList<BAccount> bAcc = bankImp.getAccounts(1);
		for(BAccount b: bAcc){
			String task = b.getTask();
			int status = 1;
			double iAmt = 0;
			String[] aTask = task.split("/");
			if(aTask.length>1){
				continue;
			}
			else{
				iAmt = Double.parseDouble(task);
				iAmt+=b.getAccBal();
				if(iAmt < 0)
				{
					status = 3;
					iAmt = b.getAccBal();
				}
				else
					status =2;
			}
			
			bankImp.updateApp(b.getAccNo(), status, iAmt,iUserL);
		}
	}
	
	public static void appClosure(String iUserL){
		ArrayList<BAccount> bAcc = bankImp.getAccounts(1);
		for(BAccount b: bAcc){
			String task = b.getTask();
			int status = 1;
			double iAmt = 0;
			String[] aTask = task.split("/");
			if(aTask.length>1){
				continue;
			}
			else if (task.equals("D"))
			{
				if(b.getAccBal()==0)
					status=2;
				else
				{
					status=3;
					iAmt=b.getAccBal();
				}
			}
			
			bankImp.deleteAccA(b.getAccNo(), status, iAmt, iUserL);
		}
		
	}
}

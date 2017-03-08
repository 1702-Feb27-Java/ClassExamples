package com.banksd.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.banksd.BankServices;

public class Employee {
	String eName;
	public void setEName(String sCustN){
		this.eName = sCustN; 
		//getAccountDetails
	}
	
	public void viewPendingApp(char cType){
		//Create attribute Application [Status] : approved , waiting 
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		int iCnt=0;
		String key="";
		switch(cType){
		case 'p' : 	System.out.println("===============================");
					System.out.println("*** PENDING APPLICATIONS ***");
					System.out.println("===============================");
					System.out.println("  User\tStatus\n------------------------");
					key="Pending";
					break;
		case 'a' :  System.out.println("*** APPROVED APPLICATIONS ***");
					System.out.println("===============================");
					System.out.println("  User\tStatus");
					key="Pending";
					break;
		default:	System.out.println("No Application");
					break;
		}

		ArrayList<String> sArr = BankServices.readFile(strFilePath);
		for(String iS : sArr){
			String[] sStr = iS.split(":");
			String strF = sStr[(sStr.length)-1];

			if(sStr.length>0 && ((sStr[(sStr.length)-1].equals(key))==true))
				System.out.println(++iCnt+" "+sStr[0]+"\t"+sStr[(sStr.length)-1]);
		}
		if(iCnt==0)
			System.out.println("No Applications");
		System.out.println("===============================\n");
	}
	
	public void updateApp(){
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		int iCnt=0;
		System.out.println("===============================");
		System.out.println("*** APPROVED APPLICATIONS ***");
		System.out.println("===============================");
		System.out.println("  User\tStatus");
		
		Map<String ,String> mC = new HashMap();
		ArrayList<String> sArr = BankServices.readFile(strFilePath);
		for(String iS : sArr){
			String[] sStr = iS.split(":");
			if(sStr.length>0 && sStr[(sStr.length)-1].equals("Pending")){
				if(sStr.length==8){
					String[] sTaskngs= sStr[6].split("-");
					String sTask="";
					if((sTaskngs.length >0)==true){
					String accType = sTaskngs[1];
					String iLiteral = "";
					if(accType.equals("C")) iLiteral="C";
					else if(accType.equals("S")) iLiteral="S";
					
					long lAcc = Account.genAcc();
					String iAcc = iLiteral+lAcc;
					String iAccN= iAcc.substring(0, 9);
					sStr[4]=iAccN;	sStr[5]="0"; sStr[6]="OPENED";
					}
				}
				String SNew="";
				for(int i=0;i<(sStr.length)-1;i++){
					SNew+=sStr[i]+":";
				}
				SNew+="Approved";
				BankServices.replaceLine(strFilePath, SNew);
				System.out.println(++iCnt+" "+sStr[0]+"\t"+"Approved");
			}
		}
		System.out.println("===============================");
	}
	
	public static int init(Employee iEmp)
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
			case 1: iEmp.viewPendingApp('p');
					break;
			case 2: iEmp.updateApp();
					break;
			case 3: iEmp.appClosure();
					break;
			case 4: iEmp.appDeposit();
					break;
			case 5: iEmp.appWithdraw();
					break;
			default:break;
			}
	return iIn;
	}

	public void appWithdraw() {
		
		String iStatus="";
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		int iCnt=0;
		System.out.println("=========================================");
		System.out.println("*** UPDATED APPLICATIONS ***");
		System.out.println("=========================================");
		System.out.println("  User\tStatus");
		Map<String ,String> mC = new HashMap();
		ArrayList<String> sArr = BankServices.readFile(strFilePath);
		System.out.println();
		for(String iS : sArr){
			String[] sStr = iS.split(":");
			if(sStr.length==8 && sStr[(sStr.length)-1].equals("Pending")){
				if(sStr.length==8){
					String[] sTaskngs= sStr[6].split("-");
					String sAmt ="";
					String sTask="";
					if(sTaskngs.length >0){
						sTask = sTaskngs[0];
						sAmt = sTaskngs[1];
						if(sTask.equals("WITH")){
							String iAcc = sStr[4];
							long iaccN  = Long.parseLong(iAcc.substring(1, iAcc.length()-1)); 
							
							String accType="";
							if(iAcc.charAt(0)=='C')accType="Checking";
							else if(iAcc.charAt(0)=='S')accType="Savings";
								
							double lBal = Double.parseDouble(sStr[5]);
							Account amt = new Account(iaccN,accType,lBal);
							if(lBal < Double.parseDouble(sAmt))
								iStatus = "DENIED";
							else{
								amt.getBalance(Double.parseDouble(sAmt));
								iStatus = "APPROVED";
								sStr[6] = "null";
								double dBal = amt.getBalance();
								sStr[5] = dBal+"";
							}
						}	
					}
				}
				
				String SNew="";
				for(int i=0;i<(sStr.length)-1;i++){
					SNew+=sStr[i]+":";
				}
				SNew+=iStatus;
				BankServices.replaceLine(strFilePath, SNew);
				if(iStatus.equals("APPROVED"))
					System.out.println(++iCnt+" "+sStr[0]+"\t"+"Approved");
				else
					System.out.println(++iCnt+" "+sStr[0]+"\t"+"Denied");
			}
		}
		System.out.println("===============================");
	}

	public void appDeposit() {
		String iStatus="";
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		int iCnt=0;
		System.out.println("=========================================");
		System.out.println("*** APPROVED APPLICATIONS ***");
		System.out.println("=========================================");
		System.out.println("  User\tStatus");
		Map<String ,String> mC = new HashMap();
		ArrayList<String> sArr = BankServices.readFile(strFilePath);
		System.out.println();
		for(String iS : sArr){
			String[] sStr = iS.split(":");
			if(sStr.length==8 && sStr[(sStr.length)-1].equals("Pending")){
				if(sStr.length==8){
					String[] sTaskngs= sStr[6].split("-");
					String sAmt ="";
					String sTask="";
					if(sTaskngs.length >0){
						sTask = sTaskngs[0];
						sAmt = sTaskngs[1];
						if(sTask.equals("DEPOSIT")){
							String iAcc = sStr[4];
							long iaccN  = Long.parseLong(iAcc.substring(1, iAcc.length()-1)); 
							
							String accType="";
							if(iAcc.charAt(0)=='C')accType="Checking";
							else if(iAcc.charAt(0)=='S')accType="Savings";
								
							double lBal = Double.parseDouble(sStr[5]);
							Account amt = new Account(iaccN,accType,lBal);
								
							amt.setBalance(Double.parseDouble(sAmt));
							iStatus = "APPROVED";
							sStr[6] = "null";
							double dBal = amt.getBalance();
							sStr[5] = dBal+"";
						}	
					}
				}
				
				String SNew="";
				for(int i=0;i<(sStr.length)-1;i++){
					SNew+=sStr[i]+":";
				}
				SNew+=iStatus;
				BankServices.replaceLine(strFilePath, SNew);
				System.out.println(++iCnt+" "+sStr[0]+"\t"+"Approved");
			}
		}
		System.out.println("===============================");
	}

	public void appClosure() {
		String iStatus="";
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		int iCnt=0;
		System.out.println("=========================================");
		System.out.println("*** APPROVED CLOSURE APPLICATIONS ***");
		System.out.println("=========================================");
		System.out.println("  User\tStatus");
		ArrayList<String> sArr = BankServices.readFile(strFilePath);
		System.out.println("-----------------------------------------");
		for(String iS : sArr){
			String[] sStr = iS.split(":");
			if(sStr.length==8 && sStr[(sStr.length)-1].equals("Pending")){
					String sTask=sStr[6];
						if(sTask.equals("CLOSURE")){
							sStr[4]="null";
							sStr[5]="0";
							sStr[6] = "null";
							iStatus = "APPROVED";
							
							String SNew="";
							for(int i=0;i<(sStr.length)-1;i++){
								SNew+=sStr[i]+":";
							}
							SNew+=iStatus;

							BankServices.replaceLine(strFilePath, SNew);
							System.out.println(++iCnt+" "+sStr[0]+"\t"+"Approved");
					}
				}
		}		
		System.out.println("===============================");
	}
}
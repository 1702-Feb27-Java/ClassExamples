package com.banksd.bank;

import java.util.Scanner;

import com.banksd.BankServices;

public class Customer {
	String CustName;
	Account Acc;
	
	public void setCustName(String sCustN){
		this.CustName = sCustN; 
		//getAccountDetails
	}
	
	public void openAccount(){
		//Create attribute Application [Status] : approved , waiting 
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		String strVal = BankServices.getValueFFile(strFilePath,this.CustName,":");
		System.out.println("Select Type of Account [Checking, Savings] : ");
		Scanner sc= new Scanner(System.in);
		String iType = sc.nextLine();
		String iCh = "";
		if(iType.equals("Checking"))iCh+="C";
		else if(iType.equals("Savings"))iCh+="S";
		else{iCh+="C";}
		String[] sA = strVal.split(":");
		
		if(sA.length>1){
			sA[sA.length-1]=("Pending");
			sA[sA.length-2]=("OPEN-"+iCh);
		}
		
		StringBuilder sb= new StringBuilder("");
		for(int i=0;i<sA.length;i++){
			sb.append(sA[i]);
			if(i!=sA.length-1)
				sb.append(":");
		}
		BankServices.replaceLine(strFilePath, sb.toString());
		System.out.println("MESSAGE : Request sent for approval");
	}
	
	public void viewStatus(){
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		StringBuilder strEntry =new StringBuilder(BankServices.getValueFFile(strFilePath,this.CustName,":"));
		String[] sArr = strEntry.toString().split(":");
		System.out.println("-------------------Account Details------------------");
		System.out.println(" USER\tACCOUNT_NUMBER\t\tBALANCE\tSTATUS");
		System.out.println("----------------------------------------------------");
		if(sArr.length==8)
			System.out.println(" "+sArr[0]+"\t"+sArr[4]+"\t\t"+sArr[5]+"\t"+sArr[7]);
	}
	
	public void reqDeposit(){
		System.out.println("-----------ENTER AMOUNT TO DEPOSIT-----------");
		Scanner sc = new Scanner(System.in);
		String sAmt = sc.nextLine();
		
		String sTask = "DEPOSIT-"+sAmt; 
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		String strVal = BankServices.getValueFFile(strFilePath,this.CustName,":");
		String[] sA = strVal.split(":");
		if(sA.length>1){
			sA[sA.length-1]=("Pending");
			sA[sA.length-2]=sTask;
		}
		
		StringBuilder sb= new StringBuilder("");
		for(int i=0;i<sA.length;i++){
			sb.append(sA[i]);
			if(i!=sA.length-1)
				sb.append(":");
		}
		BankServices.replaceLine(strFilePath, sb.toString());
		System.out.println("MESSAGE : Request sent for deposit");
		System.out.println("----------------------------------------------");
	}
	
	public void reqWidthdrawal(){
		System.out.println("-----------ENTER AMOUNT TO WITHDRAW-----------");
		Scanner sc = new Scanner(System.in);
		String sAmt = sc.nextLine();
		
		String sTask = "WITH-"+sAmt; 
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		String strVal = BankServices.getValueFFile(strFilePath,this.CustName,":");
		String[] sA = strVal.split(":");
		if(sA.length>1){
			sA[sA.length-1]=("Pending");
			sA[sA.length-2]=sTask;
		}
		
		StringBuilder sb= new StringBuilder("");
		for(int i=0;i<sA.length;i++){
			sb.append(sA[i]);
			if(i!=sA.length-1)
				sb.append(":");
		}
		BankServices.replaceLine(strFilePath, sb.toString());
		System.out.println("MESSAGE : Request sent for withdrawal");
		System.out.println("----------------------------------------------");
	}
	
	public void reqClosure(){ 
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		String strVal = BankServices.getValueFFile(strFilePath,this.CustName,":");
		String[] sA = strVal.split(":");
		if(sA.length>1){
			sA[sA.length-1]=("Pending");
			sA[sA.length-2]="CLOSURE";
		}
		
		StringBuilder sb= new StringBuilder("");
		for(int i=0;i<sA.length;i++){
			sb.append(sA[i]);
			if(i!=sA.length-1)
				sb.append(":");
		}
		BankServices.replaceLine(strFilePath, sb.toString());
		System.out.println("MESSAGE : Request sent for Closure");
	}
}

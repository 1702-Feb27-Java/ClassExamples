package com.banksd.bank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.banksd.BankAppMain;
import com.banksd.BankServices;

public class Bank{
	int id;
	String bankName;
	Address bankAddress;
	
	public Bank(){
		//Get the data from db / file
		String strPAth = BankServices.readFile(BankServices.propertiesPath, "bankId", "=");
		this.id 		 = Integer.parseInt(strPAth);
		this.bankName 	 = BankServices.readFile(BankServices.propertiesPath, "bankName", "=");
		this.bankAddress = Address.getAddress(this.id);
	}
	
	public void DisplayBankInfo(){
		System.out.println("*********** BANK INFO **************");
		String iFilePathToBankInfo = BankServices.readFile(BankServices.propertiesPath, "bankInfo", "=");
		if(iFilePathToBankInfo.length()==0)
			System.out.println("*** NO INFORMATION FOUND ***");
		ArrayList<String>spContent = BankServices.readFile(iFilePathToBankInfo);
		for(String iStr : spContent){
			System.out.println(iStr);
		}
	}
	
	public String getbankName(){
		return this.bankName;
	}
	
	public String getbankAddress(){
		return this.bankAddress.getAddress();
	}
	
	public void DisplayBankDetails(){
		System.out.println("Bank Details : ");
		System.out.println("--------------------");
		System.out.println("Name 	: "+getbankName());
		System.out.println("Address : "+getbankAddress());
	}
}

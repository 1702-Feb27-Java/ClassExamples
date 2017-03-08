package com.banksd.bank;

import java.util.ArrayList;

import com.banksd.BankServices;

public class Address {
	
	int id;
	String address;
	String state;
	String city;
	int zipCode;
	
	/**
	 * @param id
	 * @param address
	 * @param state
	 * @param city
	 * @param zipCode
	 */
	public Address(int id, String address, String state, String city, int zipCode) {
		super();
		this.id = id;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
	}
	
	public String getAddress(){
		return this.address+",\n\t  "+this.city+",\n\t  "+this.state+",\n\t  "+this.zipCode;
	}
	
	public static Address getAddress(int idx){
		
		String addr=null, sta=null, cty=null; 
		String idAdd = null, zipC=null;
		
		//Read the address from the file
		String iFilePathToAddInfo = BankServices.readFile(BankServices.propertiesPath, "bankAddressPath", "=");
		String strAddress = BankServices.readFile("C:\\Users\\Samsel\\workspace\\BankingAppSD\\src\\main\\resources\\Address.txt", Integer.toString(idx), "=");
		/*
		 * create Address element for this entry
		 * FORMAT : BankID=[id=1000;address=DBank,Loudon County;state=Virginia;city=Ashburn;zipcode=20148;]
		 */
		strAddress = strAddress.substring(1,strAddress.length()-1);
		String[] strAdd = strAddress.split(";");
		for(String iStr : strAdd){
			String[] sValues = iStr.split(":");
			if(sValues.length>0){
				char cV = (sValues[0].charAt(0));
				switch(cV){
				case 'i' :  String strV = sValues[1];
							idAdd= strV;
							break;
				case 'a' : addr= sValues[1];
							break;
				case 'c' : sta= sValues[1];
							break;
				case 's' : cty= sValues[1];
							break;
				case 'z' : zipC= sValues[1];
							break;
				default : break;
				}
			}
		}
		
		Address adOBj = new Address(Integer.parseInt(idAdd),addr,sta,cty,Integer.parseInt(zipC));
		
		//return the Address element
		return adOBj;
	};
}
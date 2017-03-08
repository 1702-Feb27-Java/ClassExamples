package com.banksd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;


public class TBankServices {
	static String resPath= "C:\\Users\\Samsel\\workspace\\BankingAppSD\\src\\test\\resources";
	
	@Test
	public void testWriteFile(){
		ArrayList<String> aS = new ArrayList();
		aS.add("Dian:dia:30/02/1986:Customer:null:null:null:null");
		BankServices.writeToFile(resPath+"\\BSTestWriteS.txt",aS,'p');
		ArrayList<String> strRes = BankServices.readFile(resPath+"\\BSTestWriteS.txt");

		assertEquals(true,strRes.contains("Dian:dia:30/02/1986:Customer:null:null:null:null"));
	}
	
	@Test
	public void testUserExists(){
		//boolean bExist= BankServices.userExists("Dian");
		String keyToSearch="Dian";
		boolean sRes=false;
		ArrayList<String> strRes = BankServices.readFile(resPath+"\\BSTestWriteS.txt");
		for(String s:strRes){
			String[] sLine = s.split(":");
			if((sLine.length > 0)&&(sLine[0].equals(keyToSearch))){
				sRes=true;
				
				break;
			}
		}
		assertEquals(true,sRes);
		
	}
	
	@Test
	public void testgetValueFromFile(){
		String exp 	 = "Dian:dia:30/02/1986:Customer:null:null:OPEN-C:Pending";
		String sCurr = BankServices.getValueFFile(resPath+"\\BSTestWriteS.txt", "Dian", ":");
		assertEquals(true,exp.equals(sCurr));
	}
	
	@Test
	public void testReplaceLine(){
		String modF = "Dian:dia:30/02/1986:Customer:null:null:OPEN-C:Pending";
		BankServices.replaceLine(resPath+"\\BSTestWriteS.txt",modF);
		String sCurr = BankServices.getValueFFile(resPath+"\\BSTestWriteS.txt", "Dian", ":");

		assertEquals(true,sCurr.equals(modF));
	}
}

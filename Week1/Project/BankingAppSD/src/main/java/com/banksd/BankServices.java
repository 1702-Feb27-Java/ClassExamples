package com.banksd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.banksd.bank.Customer;
import com.banksd.bank.Employee;

public class BankServices {
	
	public static String propertiesPath = "C:/Users/Samsel/workspace/BankingAppSD/src/main/resources/bankInfoProperties.txt";
	
	public static boolean registerUsers(){
		System.out.println("----REGISTER---");
		Scanner sc = new Scanner(System.in);
		boolean bRet=true, bIsNull=false;
		String iName=null;
		do{	
		System.out.println("Username :  ");
		iName = sc.nextLine();
		if(iName.equals(""))
			bIsNull=true;

		//Check if User already Exists
		bRet = userExists(iName);
		if(bRet==true)
			System.out.println("User Already Exists.");
		else
			break;
		}while(bRet==true || bIsNull==false);

		bRet=false;String iPswd="";
		do{
		System.out.println("Password :  ");
		iPswd = sc.nextLine();
		if(iPswd.equals(null))bRet=true;
		}while(bRet==true);
		
		bRet=false;String iDob="";
		do{
		System.out.println("DOB [dd/MM/YYYY] :  ");
		iDob = sc.nextLine();
		if(iDob.equals(""))bRet=true;
		}while(bRet==true);
		
		bRet=false;String iType="";
		do{
		System.out.println("User type [Customer,Employee] : ");
		iType = sc.nextLine();
		if(iType.equals(""))bRet=true;
		}while(bRet==true);

		//ACCNO:BAL:TASK:STATUS as null
		String iAcc		= "null";
		String iBal		= "null";
		String iTask 	= "null";
		String iStatus 	= "null";
		String apd = iAcc+":"+iBal+":"+iTask+":"+iStatus;

		String strFilePath  = readFile(BankServices.propertiesPath,"loginProfile","=");
		ArrayList<String> strToWrite = new ArrayList();
		strToWrite.add(iName+":"+iPswd+":"+iDob+":"+iType+":"+apd);
		writeToFile(strFilePath, strToWrite,'a');
		return true;
	}
	
	public static void writeToFile(String strFilePath, ArrayList<String> strToWrite, char ctype){
		BufferedWriter bw = null;
		PrintWriter pw	  = null;
		BufferedReader br = null;
		try {
			if(ctype=='a')
				bw = new BufferedWriter(new FileWriter(strFilePath,true));
			else
				bw = new BufferedWriter(new FileWriter(strFilePath));
				
			pw = new PrintWriter(bw);
			for(String s : strToWrite){
			pw.println(s);
			}
			pw.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean userExists(String iStr){
		String strFilePath  = readFile(BankServices.propertiesPath,"loginProfile","=");
		String strRowExists = readFile(strFilePath,iStr,":");
		if(strRowExists==null)
			return false;
		else
			return true;
	}
	
	public static ArrayList<String> readFile(String ifilepath){
		ArrayList<String> oStrValues = new ArrayList<String>();
		try(BufferedReader bf = new BufferedReader(new FileReader(ifilepath))){
			String strLine = null;
			while((strLine=bf.readLine()) !=null){
				oStrValues.add(strLine);
			}
		} catch (FileNotFoundException e) {
			BankAppMain.log.trace("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			BankAppMain.log.trace("IOException : Error accessing the File.");
			e.printStackTrace();
		}
		return oStrValues;
	}

	public static String readFile(String ifilepath, String key, String regx){
		String filePath = null;
		BufferedReader bf=null;
		try{
			bf = new BufferedReader(new FileReader(ifilepath));
			while((filePath= bf.readLine()) != null){
				String[] strDefine = filePath.split(regx);
				if((strDefine.length>0) && strDefine[0].equals(key)){
					filePath =  strDefine[1];
					break;
				}
				else
					continue;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally{
			try{
			bf.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		return filePath;
	}
	
	public static String getValueFFile(String ifilepath, String key, String regx){
		String filePath = null;
		BufferedReader bf=null;
		try{
			bf = new BufferedReader(new FileReader(ifilepath));
			while((filePath= bf.readLine()) != null){
				String[] strDefine = filePath.split(regx);
				if((strDefine.length>0) && strDefine[0].equals(key)){
					//filePath =  strDefine[1];
					break;
				}
				else
					continue;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally{
			try{
			bf.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		return filePath;
	}
	
	public static boolean initView(String sUser, String sPswd){

		System.out.println();
		boolean bRet = userExists(sUser);
		if(bRet==false)
		{
		System.out.println("User does not exists. Please Register.");
		return false;
		}
		System.out.println("*****Welcome "+sUser+" *****");
		String sType = getTypeOfUser(sUser);
		
		Customer c;
		Employee e =null;
		
		switch(sType){
		case "Customer" : c = new Customer(); c.setCustName(sUser); 
						  init(sType,c);
						  break;
		case "Employee" : e = new Employee(); e.setEName(sUser); 
						  init(sType,e);
						  break;
		default     	: break;
		}
		
		return true; 
	}
	
	private static String getTypeOfUser(String iUser) {
		// TODO Auto-generated method stub
		String strFilePath  = BankServices.readFile(BankServices.propertiesPath,"loginProfile","=");
		StringBuilder strEntry =new StringBuilder(BankServices.getValueFFile(strFilePath,iUser,":"));
		String[] sArr = strEntry.toString().split(":");
		if(sArr.length >3){
			return sArr[3];
		}
		return "";
	}

	public static void init(String iView,Object iUser)
	{
		int iIn=0;
		
		do{
		switch(iView){
			case "Customer" : System.out.println("*****CUSTOMER SERVICES*****");
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
							case 1: ((Customer) iUser).openAccount();		break;
							case 2:	((Customer) iUser).reqClosure();		break;
							case 3: ((Customer) iUser).viewStatus();		break;
							case 4: ((Customer) iUser).reqDeposit();		break;
							case 5: ((Customer) iUser).reqWidthdrawal();	break;
							default:break;
							}
							break;
							
			case "Employee"   :  iIn =Employee.init((Employee) iUser);
								 break;
		}	
		}while(iIn!=6);
		
		System.out.println("***** Thank you for using our service *****");
	}

	public static void replaceLine(String strFilePath, String iEntry) {
		// TODO Auto-generated method stub
		String[] sArray = iEntry.split(":");
		String iUser="";
		String iVal="";
		if(sArray.length > 0){
			iUser += sArray[0];
		//for(String i: sArray){
		/*iVal+=":"+i;*/
		}
		iVal = iEntry.substring(iUser.length(),iEntry.length());
		
		Map<String,String> fEntries = new HashMap();
		try(BufferedReader bf = new BufferedReader(new FileReader(strFilePath))){
			String strLine = "";
			while((strLine=bf.readLine()) !=null){
				String[] sA = strLine.split(":");
				String iU="";
				if(sA.length > 0){
					iU = sA[0];
					String iV ="";
					for(int i=1;i<sA.length;i++){
						iV+=":"+sA[i];
					}
					fEntries.put(iU, iV);
				}
			}
			bf.close();

		}catch (IOException e) {
			BankAppMain.log.trace("IOException : Error accessing BankInfo.");
			e.printStackTrace();
		}
		//replace Entry
		fEntries.put(iUser, iVal);
		
		ArrayList<String> sVal = new ArrayList<String>();
		for(Map.Entry<String, String> e: fEntries.entrySet()){
			sVal.add(e.getKey()+e.getValue());
		}
		
		//Write to file
		writeToFile(strFilePath,sVal,'n');
	}
}

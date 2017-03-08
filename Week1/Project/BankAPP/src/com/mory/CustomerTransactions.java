package com.mory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class CustomerTransactions {

	private static HashMap<String, Customer> CUSTOMERINFOSMAP = new HashMap<String, Customer>();
	private static String DBfileEmployee = "src/com/mory/databases/DBtableEmployee.txt";

	private static CustomerTransactions TransactionInstance;


	// Singleton Transaction class
	private CustomerTransactions() {
	}

	public static CustomerTransactions getTransaction() {
		if (TransactionInstance == null) {
			TransactionInstance = new CustomerTransactions();
		}
		return TransactionInstance;
	}
	
	
/**
 * Medtho to add customer to the customer Map
 * @param customer a customer Object
 * @return
 */
	public static boolean addToCustomerToDB(Customer customer) {
		if (CUSTOMERINFOSMAP.containsKey(customer.getAccount().getAccountNumber())) {
			return false;
		}
		CUSTOMERINFOSMAP.put(customer.getName().toUpperCase(), customer);
		return true;
	}

public static HashMap<String, Customer> getMap() {
	return CUSTOMERINFOSMAP;
}

public static void setMap(HashMap<String, Customer> cUSTOMERINFOSMAP) {
	CUSTOMERINFOSMAP = cUSTOMERINFOSMAP;
}
}
	

	/***
	 * dispaly list of account associated with specified user name
	 * @param name User's name
	 */
	/*public static void displayAccounts(String name){
		
	}
	*/
	
	
/**
 * Persit the two string representation of the Customer
 * Object to the customer data base fiule
 * @param customer Customer Object
 */
	/*public static void persistToDbfile(Customer customer) {
		// toSimpleText(DBfileCustomer);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DBfileCustomer))) {
			String line;
			for (String s : CUSTOMERINFOSMAP.keySet()) {
				Object o = CUSTOMERINFOSMAP.get(s);
				bufferedWriter.write(o.toString());
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	*/
	
	

	

	// Method to call for user login

	

	// Read from file
/*	
	public void load(){
	String userName ="";
	 int password;
	String accountType;
	int balance;
	boolean isActive;
	int accountNumber;

	try (BufferedReader bfReader = new BufferedReader(new FileReader(DBfileCustomer))) {
		String line;
			
			while ((line = bfReader.readLine()) != null) {
				String[] userInfoArray = line.split(":");
				accountType=userInfoArray[0];
				accountNumber=Integer.parseInt(userInfoArray[1]);
				userName=userInfoArray[2];
				password=Integer.parseInt(userInfoArray[3]);
				isActive=Boolean.parseBoolean(userInfoArray[3]);
				balance=Integer.parseInt(userInfoArray[4]);
				//CHECKING:500:Mory:0:false.500
				Customer customer= customer.getCustomer(accountType, accountNumber,userName, password,balance);
				
				CUSTOMERINFOSMAP.put(userName, customer);
				
			}
	}
	}
}*/

			
	

	

	
	
	
	
	
	
	
	
	
	

	/*
	 * public static void toSimpleText(String file) { try (BufferedWriter writer
	 * = new BufferedWriter(new FileWriter(file))) { writer.write(""); } catch
	 * (IOException e) { }
	 * 
	 * }
	 */


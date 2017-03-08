package com.mory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Transaction {

	static HashMap<Integer, Customer> CUSTOMERINFOSMAP = new HashMap<Integer, Customer>();
	static HashMap<Integer, Customer> CUSTOMERINFOSMAP = new HashMap<Integer, Customer>();
	/*
	 * private static HashMap<String, Employee> BANKERINFOSMAP = new
	 * HashMap<String, Employee>(); private static HashMap<String, Admin>
	 * ADMININFOSMAP = new HashMap<String, Admin>();
	 */

	private static String DBfileCustomer = "src/main/java/com/mory/databases/DBtableCustomer.txt";
	private static Transaction TransactionInstance;

	// Singleton Transaction class
	private Transaction() {
	}

	public static Transaction getTransaction() {
		if (TransactionInstance == null) {
			TransactionInstance = new Transaction();
		}
		return TransactionInstance;
	}

	/**
	 * Medtho to add customer to the customer Map
	 * 
	 * @param customer
	 *            a customer Object
	 * @return
	 */
	public static boolean addCustomerToDB(Customer customer) {
		if (CUSTOMERINFOSMAP.containsKey(customer.getAccount().getAccountNumber())) {
			return false;
		}
		CUSTOMERINFOSMAP.put(customer.getAccount().getAccountNumber(), customer);
		return true;
	}

	public static void persistCustomerToDbfile(Customer customer) {
		// toSimpleText(DBfileCustomer);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DBfileCustomer))) {
			String line;
			for (int s : CUSTOMERINFOSMAP.keySet()) {
				Object o = CUSTOMERINFOSMAP.get(s);
				bufferedWriter.write(o.toString());
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayList(){
		for(Integer i : CUSTOMERINFOSMAP.keySet()){
			System.out.println(i);
		}
		/*for(Customer customer:CUSTOMERINFOSMAP.values()){
			System.out.println(customer.getAccount());
			
		}*/
	}
	// Read from file

	public static void load() {
		String userName = "";
		int Password;
		String accountType;
		int deposit;
		boolean isActive;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DBfileCustomer))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] userInfoArray = line.split(":");
				accountType = userInfoArray[0];
				deposit = Integer.parseInt(userInfoArray[1]);
				userName = userInfoArray[2];
				Password = Integer.parseInt(userInfoArray[3]);
				isActive = Boolean.parseBoolean(userInfoArray[4]);
				Customer customer = new Customer(userName, Password, accountType, deposit, isActive);
				if(customer.getAccount().getAccountNumber() == null){
					System.out.println("NO Account");
				}
				CUSTOMERINFOSMAP.put(customer.getAccount().getAccountNumber(), customer);
				

			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

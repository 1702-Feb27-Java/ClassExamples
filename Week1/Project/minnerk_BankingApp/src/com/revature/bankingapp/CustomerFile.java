/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: CustomerFile.java
* PROGRAMMER: KEITH MINNER
* 
* PURPOSE: ALLOW A USER TO SIGN UP FOR A BANKING SERVICE TO INCLUDE A CHECKING AND / OR SAVINGS ACCOUNT
* WITH THE CAPABILITIES TO DEPOSIT, WITHDRAW, VIEW AND EDIT PERSONAL INFORMATION.  AN EMPLOYEE CAN
* VIEW CUSTOMER INFORMATION AND APPROVE ACCOUNTS.
*========================================================================================================
*										PROJECT FILES
*
* Customer.java					Menus.java
* CustomerClassTest.java			MenusClassTest.java
* CustomerFile.java				Person.java
* CustomerFileTest.java			PersonClassTest.java	
* Employee.java					UserScreen.java
* EmployeeClassTest.java			UserScreenTest.java
*========================================================================================================
*										PACKAGE & IMPORT FILES
*********************************************************************************************************
*/

package com.revature.bankingapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.util.FactoryConnection;
/**
*********************************************************************************************************
*	@ CLASS CUSTOMERFILE
*********************************************************************************************************
*/	
public class CustomerFile {

	static final Logger l =  Logger.getRootLogger();
	static Scanner in = new Scanner(System.in);

/**
*********************************************************************************************************
*	@ METHOD TO ADD A PERSON TO THE TEXT FILE
*********************************************************************************************************
*/	
	public static void newPersonToFile(Customer c) {

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("person.txt", true));
			String s = c.toString(); // specific customers info
			bw.write(s); // write to line
			bw.newLine(); // next line
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

/**
*********************************************************************************************************
*	@ METHOD TO DELETE A LINE OF TEXT FROM A FILE AT A SPECIFIC REFERENCE
*********************************************************************************************************
*/	
	public static void updateRecord(Customer c) {
		String file = "person.txt";
		String lineToRemove = c.toString();
		
		try {
			File inFile = new File(file);
			if (!inFile.isFile()) {
			System.out.println("Parameter is not an existing file");
				return;
			}
		
		//Construct the new file that will later be renamed to the original filename. 
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
		BufferedReader br = new BufferedReader(new FileReader(file));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		String line;
		
		//Read from the original file and write to the new 
		//unless content matches data to be removed.
		while ((line = br.readLine()) != null) {
			if (!line.trim().equals(lineToRemove)) {
				pw.println(line);
				pw.flush();
			}
		}
		pw.close();
		br.close();

		//Delete the original file
		if (!inFile.delete()) {
			System.out.println("Could not delete file");
			return;
		}
			//Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
/**
*********************************************************************************************************
*	@ METHOD TO VERIFY THE USER LOGIN OF AN PERSON
*********************************************************************************************************
*/	
	// Method to verify the user login of an individual
	public static Customer verifyLogin(String s1, String s2, int i) {

		BufferedReader br = null;
		Customer c = null;
		try {
			br = new BufferedReader(new FileReader("person.txt"));
			String s = br.readLine();

			// Finds the specific info and sets all of the variables in
			// a customer based on what is in the string array
			while (s != null) {
				String[] sArr = s.split(":");
				    //username              password             role
				if (sArr[3].equals(s1) && sArr[4].equals(s2)) {
					if (Integer.parseInt(sArr[6]) == 1)
						System.out.println("\nYour account is still pending, please speak with a "
								+ "bank representative ");
					else if (Integer.parseInt(sArr[6]) == 3)
						System.out.println("\nYour account has been denied, please speak with a "
								+ "bank representative ");
					else
						c = Customer.setCustomerInfo(sArr);
				}
				s = br.readLine();
			}
			if (c.getFirstName() == null) { // if it is not found, displays
											// message and restarts LoginMenus
				System.out.println("Invalid Username or Password!");
				Menus.loginMenu(i);
				System.out.println();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
/**
*********************************************************************************************************
*	@ METHOD TO VERIFY A USER ID DOES NOT ALREADY EXIST
*********************************************************************************************************
*/	
	
}
		
	
			
			
	

/**
*********************************************************************************************************
*  										END CLASS CUSTOMERFILE
*********************************************************************************************************
*/


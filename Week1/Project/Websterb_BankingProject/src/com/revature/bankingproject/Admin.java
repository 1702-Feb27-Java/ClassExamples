package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Admin{
	static final Logger l =  Logger.getRootLogger();
	/**
	 * Check for username and password, return true if admin logs in
	 * @param username
	 *            the string username passed in
	 * @param password
	 *            the string password passed in
	 * @return the boolean true or false whether admin logged in
	 */
	public boolean login(String username, String password) {
		boolean success = false;// whether the login was successful
		String line;
		
		//these two booleans used for checking validity of login
		boolean usernameFound = false;
		boolean passwordMatch = false;
		
		boolean usernameFoundOnce = false;//keep track if username was found at all
		String tempCustomerId = "";
		String realCustomerId = "";

		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\bankingproject\\Data.txt",
						true))) {

			// loop through every line in the file to check for username
			while ((line = br.readLine()) != null) {
				usernameFound = false;
				passwordMatch = false;
				
				int location = 0;
				int colonCount = 0;// switch variable to see how many colon you've seen
				String currentUserName;
				String dataType = "";

				// loop through every character in the line, check for username match
				for (int i = 0; i < line.length(); i++) {

					// when we find a colon or end of line, there is a word
					if (line.charAt(i) == ':' || i == line.length() - 1) {
						colonCount++;// increment colon every time we find a word

						// get the type of data on that line
						if (colonCount == 1) {
							dataType = line.substring(location, i);
							
							// if it is not a correct data type in data file break
							if (!(dataType.equals("admin"))) {
								break;
							}
							;
						}

						// only check customer data types
						if (dataType.equals("admin") && colonCount == 3) {
							currentUserName = line.substring(location, i);
							// if username is in file mark boolean
							if (username.equals(currentUserName)) {
								usernameFound = true;
								usernameFoundOnce = true;//just to check if username was found at all
							}
						}

						// if we found a username match, check the password
						if (dataType.equals("admin") && colonCount == 4 && usernameFound) {
							if (password.equals(line.substring(location, i))) {
								passwordMatch = true;
								realCustomerId = tempCustomerId;//if password match, save id 
								break;//break once logged in
							}
							;
						}
						location = i + 1;
					}
				}
				if(usernameFound && passwordMatch){
					success = true;
					break;//break while loop once logged in
				}
			}
			if (!usernameFoundOnce) {// if username boolean still false, no username match found
				return false;
			} else if (usernameFoundOnce) {// if username found, check password
				if (!passwordMatch) {
					return false;
				}
			}
		} catch (IOException e) {
			l.error(e);
			success = false;
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			success = false;
			System.out.println("General Exception");
		}

		return success;
	}

	/**
	 * Get customerid based on customerusername
	 * @param customerUsername the username to check for
	 * @return the String customerid
	 */
	public String getCustomerId(String customerUsername){
		String tempCustomerId = "";
		
		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			String line = "";
			// loop through every line in the file to check for username
			while ((line = br.readLine()) != null) {
				
				int location = 0;
				int colonCount = 0;// switch variable to see how many colon you've seen
				String currentUserName;
				String dataType = "";

				// loop through every character in the line, check for username match
				for (int i = 0; i < line.length(); i++) {

					// when we find a colon or end of line, there is a word
					if (line.charAt(i) == ':' || i == line.length() - 1) {
						colonCount++;// increment colon every time we find a word

						// get the type of data on that line
						if (colonCount == 1) {
							dataType = line.substring(location, i);
							
							// if it is not a correct data type in data file break
							if (!(dataType.equals("customer"))) {
								break;
							}
						}
						// keep track of customerid in case there is a match on username
						if (colonCount == 2 && (dataType.equals("customer"))) {
							tempCustomerId = line.substring(location, i);
						}

						// only check customer data types
						if (dataType.equals("customer") && colonCount == 3) {
							currentUserName = line.substring(location, i);
							// if username is in file return customerid
							if (customerUsername.equals(currentUserName)) {
								return tempCustomerId;
							}
						}

						location = i + 1;
					}
				}
			}
		} catch (IOException e) {
			l.error(e);
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			System.out.println("General Exception");
		}
		return "";
	}
	
}

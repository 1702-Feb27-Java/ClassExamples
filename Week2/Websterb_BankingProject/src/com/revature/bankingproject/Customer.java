package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Customer {
	static final Logger l =  Logger.getRootLogger();
	private int customerId;// customerId for uniqueness

	/**
	 * Check username already exists, create account
	 * @param customerId
	 *            the input customer id
	 * @param username
	 *            the user namer of the account to be created
	 * @param password
	 *            the password for the account
	 * @return boolean whether it created account successfully
	 */
	public String signUpForServices(String username, String password, int customerBaseAccount) {
		String success;// whether the sign up was successful

		// assign instance variables to the input from main
		this.customerId = username.hashCode();
		
		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			String line;
			try {

				// loop through every line in the file to check for username
				while ((line = br.readLine()) != null) {

					int location = 0;
					int colonCount = 0;// switch variable to see how many colon
										// you've seen
					String currentUserName;
					String dataType = "";

					// loop through every character in the line, check for
					// username match
					for (int i = 0; i < line.length(); i++) {

						// when we find a colon or end of line, there is a word
						if (line.charAt(i) == ':' || i == line.length() - 1) {
							colonCount++;// increment colon every time we find a
											// word

							// get the type of data on that line
							if (colonCount == 1) {
								dataType = line.substring(location, i);

								// if it is not a correct data type in data file
								// break
								if (!(dataType.equals("customer"))) {
									break;
								}
								;
							}

							// only check customer data types
							if (dataType.equals("customer") && colonCount == 3) {
								currentUserName = line.substring(location, i);
								// if username is in file, throw custom
								// exception
								if (username.equals(currentUserName)) {
									throw new UserExistsException("This username is already in use.");
								}
							}
							location = i + 1;
						}
					}
				}
				
				// add customer account to data with id, username, and password
				if(customerBaseAccount == username.hashCode()){
					bw.write("customer:" + this.customerId + ":" + username + ":" + password + ":" + "benwebster".hashCode());
					bw.newLine();
				}
				else if(!(customerBaseAccount == username.hashCode())){
					bw.write("customer:" + customerBaseAccount + ":" + username + ":" + password + ":" + "benwebster".hashCode());
					bw.newLine();	
				}
				success = "Account created successfully!";
			} catch (UserExistsException e) {
				l.error(e);
				success = e.getMessage();
			}
		} catch (IOException e) {
			l.error(e);
			success = "Unable to create account.";
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			success = "Unable to create account.";
			System.out.println("General Exception");
		}

		return success;
	}

	/**
	 * Check for username and password, return results/if it matches
	 * @param username
	 *            the string username passed in
	 * @param password
	 *            the string password passed in
	 * @return the string version of customerid
	 */
	public String login(String username, String password) {
		String success = "test";// whether the login was successful
		String line;
		
		//these two booleans used for checking validity of login
		boolean usernameFound = false;
		boolean passwordMatch = false;
		
		boolean usernameFoundOnce = false;//keep track if username was found at all
		String tempCustomerId = "";
		String realCustomerId = "";

		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
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
							if (!(dataType.equals("customer"))) {
								break;
							}
							;
						}

						// keep track of customerid in case there is a match on login
						if (colonCount == 2) {
							tempCustomerId = line.substring(location, i);
						}

						// only check customer data types
						if (dataType.equals("customer") && colonCount == 3) {
							currentUserName = line.substring(location, i);
							// if username is in file mark boolean
							if (username.equals(currentUserName)) {
								usernameFound = true;
								usernameFoundOnce = true;//just to check if username was found at all
							}
						}

						// if we found a username match, check the password
						if (dataType.equals("customer") && colonCount == 4 && usernameFound) {
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
					break;//break while loop once logged in
				}
			}
			if (!usernameFoundOnce) {// if username boolean still false, no username match found
				return "Username not found.";
			} else if (usernameFoundOnce) {// if username found, check password
				if (!passwordMatch) {
					return "Password incorrect.";
				} else {
					success = realCustomerId;
				}
			}
		} catch (IOException e) {
			l.error(e);
			success = "IO Exception";
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			success = "General Exception";
			System.out.println("General Exception");
		}

		return success;
	}

	/**
	 * Creates saving account for customer if not one already
	 * @param customerId the foreign key id to associate with in the database
	 * @return String the result of the savings account application
	 */
	public String signUpForSavingsAccount(int customerId){
		String success = " ";
		String line = "";
		int balance = 0;
		int tempCustomerId;
		boolean savingsAccountExists = false;
		boolean valid = false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			
			while ((line = br.readLine()) != null) {
				tempCustomerId = 0;
				
				int location = 0;
				int colonCount = 0;// switch variable to see how many colon you've seen
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
							if (!(dataType.equals("savings"))) {
								break;
							}
						}
						
						if(colonCount == 2)
							tempCustomerId = Integer.parseInt(line.substring(location, i));
						
						if(dataType.equals("savings") && tempCustomerId == customerId)
							savingsAccountExists = true;

						location = i + 1;
					}
				}
			}
			if(!savingsAccountExists){
				// add savings account to the database
				bw.write("savings:" + customerId + ":" + valid + ":" + balance + ":" + "benwebster".hashCode());
				bw.newLine();
				success = "Applied for Savings Account!";
			}
			else{
				success = "Checking Account already exists";
			}
		}
		 catch (IOException e) {
			 	l.error(e);
				success = "Unable to create account.";
				System.out.println("IO Exception");
				success = "IO Exception";
			} catch (Exception e) {
				l.error(e);
				success = "Unable to create account.";
				System.out.println("General Exception");
				success = "General Exception";
			}
		return success;
	}
	
	/**
	 * Creates checking acocunt for customer if not one already
	 * @param customerId the foreign key id to associate with in the database
	 * @return String the result of the checking account application
	 */
	public String signUpForCheckingAccount(int customerId){
		String success = " ";
		String line = "";
		int balance = 0;
		int tempCustomerId;
		boolean checkingAccountExists = false;
		boolean valid = false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			
			while ((line = br.readLine()) != null) {
				tempCustomerId = 0;
				
				int location = 0;
				int colonCount = 0;// switch variable to see how many colon you've seen
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
							if (!(dataType.equals("checking"))) {
								break;
							}
						}
						
						if(colonCount == 2)
							tempCustomerId = Integer.parseInt(line.substring(location, i));
						
						if(dataType.equals("checking") && tempCustomerId == customerId)
							checkingAccountExists = true;

						location = i + 1;
					}
				}
			}
			if(!checkingAccountExists){
				// add checking account to the database
				bw.write("checking:" + customerId + ":" + valid + ":" + balance + ":" + "benwebster".hashCode());
				bw.newLine();
				success = "Applied for Checking Account!";
			}
			else{
				success = "Checking Account already exists";
			}
			
		}
		 catch (IOException e) {
			 	l.error(e);
				success = "Unable to create account.";
				System.out.println("IO Exception");
				success = "IO Exception";
			} catch (Exception e) {
				l.error(e);
				success = "Unable to create account.";
				System.out.println("General Exception");
				success = "General Exception";
			}
		return success;
	}
	
	/**
	 * Returns accounts for customer
	 * @param customerId the foreign key id to associate with in the database
	 * @return Arraylist of accounts represented as strings
	 */
	public ArrayList<String> getAccountsForCustomer(int customerId){
		//arraylist to store accounts
		ArrayList<String> accounts = new ArrayList<String>();
		String line;
		int tempCustomerId;
		
		boolean accountFound = false;//keep track if account was found at all

		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {

			// loop through every line in the file to check for checking or savings account
			while ((line = br.readLine()) != null) {
				tempCustomerId = 0;
				boolean tempValid = false;
				
				int location = 0;
				int colonCount = 0;// switch variable to see how many colon you've seen
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
							if (!(dataType.equals("savings")) && !(dataType.equals("checking"))) {
								break;
							}
						}
						
						if(colonCount == 2)
							tempCustomerId = Integer.parseInt(line.substring(location, i));
						
						//only show approved accounts
						if(colonCount == 3)
							if(line.substring(location, i).equals("true"))
								tempValid = true;
						
						int acccountBalance = 0;//temp accountBalance 0
						if(colonCount == 4)
							//get real account balance
							acccountBalance = Integer.parseInt(line.substring(location, i));
						
						if (colonCount == 4 && tempCustomerId == customerId && tempValid) {
							accountFound = true;//found an account 
							//add the account to the arraylist to return
							accounts.add(dataType);
							accounts.add("$" + Integer.toString(acccountBalance));
						}

						location = i + 1;
					}
				}
			}
			if(!accountFound){
				return null;
			}
		} catch (IOException e) {
			l.error(e);
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			System.out.println("General Exception");
		}

		return accounts;
	}

	/**
	 * Add money to the specified accounts balance
	 * 
	 * @param customerId foreign key for customer account
	 * @param accountType savings or checking
	 * @param amount of money to add to balance
	 * @return the new balance total
	 */
	public int depositMoney(int customerId, String accountType, int amount){
		int currBalance = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			String line = "";
			String oldText = "";
			
			int tempCustomerId;
			int prevBalance = 0;
			while ((line = br.readLine()) != null) {
				oldText += line + "\r\n";
				tempCustomerId = 0;

				int location = 0;
				int colonCount = 0;// switch variable to see how many colon you've seen
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
							if (!(dataType.equals(accountType))) {
								break;
							}
						}
						
						if(colonCount == 2)
							tempCustomerId = Integer.parseInt(line.substring(location, i));
						
						//get the balance and add to it
						if(dataType.equals(accountType) && tempCustomerId == customerId && colonCount == 4){
							prevBalance = Integer.parseInt(line.substring(location, i));
							currBalance = Integer.parseInt(line.substring(location, i));
							currBalance += amount;
						}
							

						location = i + 1;
					}
				}
			}
			String newText = oldText.replaceAll(accountType + ":" + customerId + ":" + "true" + ":" + prevBalance + ":" + "benwebster".hashCode(), 
								accountType + ":" + customerId + ":" + "true" + ":" + currBalance + ":" + "benwebster".hashCode());
			
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(
					"src\\com\\revature\\bankingproject\\Data.txt"));
			bw2.write(newText);
			bw2.close();
			
		}
		 catch (IOException e) {
			 l.error(e);
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			System.out.println("General Exception");
		}
		
		return currBalance;
	}
		
	/**
	 * Remove money to the specified accounts balance
	 * 
	 * @param customerId foreign key for customer account
	 * @param accountType savings or checking
	 * @param amount of money to add to balance
	 * @return the new balance total
	 */
	public int withdrawMoney(int customerId, String accountType, int amount){
		int currBalance = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			String line = "";
			String oldText = "";
			
			int tempCustomerId;
			int prevBalance = 0;
			while ((line = br.readLine()) != null) {
				oldText += line + "\r\n";
				tempCustomerId = 0;

				int location = 0;
				int colonCount = 0;// switch variable to see how many colon you've seen
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
							if (!(dataType.equals(accountType))) {
								break;
							}
						}
						
						if(colonCount == 2)
							tempCustomerId = Integer.parseInt(line.substring(location, i));
						
						//get the balance and add to it
						if(dataType.equals(accountType) && tempCustomerId == customerId && colonCount == 4){
							prevBalance = Integer.parseInt(line.substring(location, i));
							currBalance = Integer.parseInt(line.substring(location, i));
							currBalance -= amount;
						}
							

						location = i + 1;
					}
				}
			}
			String newText = oldText.replaceAll(accountType + ":" + customerId + ":" + "true" + ":" + prevBalance + ":" + "benwebster".hashCode(), 
								accountType + ":" + customerId + ":" + "true" + ":" + currBalance + ":" + "benwebster".hashCode());
			
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(
					"src\\com\\revature\\bankingproject\\Data.txt"));
			bw2.write(newText);
			bw2.close();
			
		}
		 catch (IOException e) {
			 l.error(e);
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			System.out.println("General Exception");
		}
		
		return currBalance;
	}

	
}

package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Employee {
	static final Logger l =  Logger.getRootLogger();
	private int employeeId;
	
	/**
	 * Create customer account checking to see if username is in database
	 * @param username the username for employee
	 * @param password password for employee
	 * @return the String result of the creation attempt
	 */
	public String createEmployeeAccount(String username, String password) {
		String success;// whether the sign up was successful

		// assign instance variables to the input from main
		this.employeeId = username.hashCode();
		
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
					int colonCount = 0;// switch variable to see how many colon you've seen
					String currentUserName;
					String dataType = "";

					// loop through every character in the line, check for username match
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
							if ((dataType.equals("customer") || dataType.equals("employee")) && colonCount == 3) {
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

				// add employee account to data with id, username, and password
				bw.write("employee:" + this.employeeId + ":" + username + ":" + password + ":" + username.hashCode());
				bw.newLine();
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
	 * @return the string version of employeeid
	 */
	public String login(String username, String password) {
		String success = "test";// whether the login was successful
		String line;
		
		//these two booleans used for checking validity of login
		boolean usernameFound = false;
		boolean passwordMatch = false;
		
		boolean usernameFoundOnce = false;//keep track if username was found at all
		String tempEmployeeId = "";
		String realEmployeeId = "";

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
							if (!(dataType.equals("employee"))) {
								break;
							}
							;
						}

						// keep track of employeeid in case there is a match on login
						if (colonCount == 2) {
							tempEmployeeId = line.substring(location, i);
						}

						// only check employee data types
						if (dataType.equals("employee") && colonCount == 3) {
							currentUserName = line.substring(location, i);
							// if username is in file mark boolean
							if (username.equals(currentUserName)) {
								usernameFound = true;
								usernameFoundOnce = true;//just to check if username was found at all
							}
						}

						// if we found a username match, check the password
						if (dataType.equals("employee") && colonCount == 4 && usernameFound) {
							if (password.equals(line.substring(location, i))) {
								passwordMatch = true;
								realEmployeeId = tempEmployeeId;//if password match, save id 
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
					success = realEmployeeId;
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
	 * Traverse the data file twice to get matching customers and matching accounts tied to those customers
	 * @param employeeId the current employee to match customers with
	 * @return Array list of string arrays, which hold customers and customer accounts
	 */
	public ArrayList<String[]> viewCustomerAccounts(int employeeId){
		ArrayList<String[]> listOfCustomers = new ArrayList<String[]>();
		
		//2 copies of data file to iterate through
		ArrayList<String> file= new ArrayList<String>();
		ArrayList<String> file2= new ArrayList<String>();
		
		String[] customer = new String[2];//String array of data line
		
		String line;
		

		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			
					//populate copies of data file to traverse
					while((line = br.readLine()) != null){
						file.add(line);
						file2.add(line);
					}
				} catch (IOException e) {
					System.out.println("IO Exception");
				} catch (Exception e) {
					System.out.println("General Exception");
				}
		//outer loop to check accounts tied to employee
		for(String line2 : file){
			//variables for storing and validating accounts
			int colonCount = 0;
			int location = 0;
			boolean match = false;
			String tempCustomerId = "";
			String tempCustomerUsername = "";
			boolean customerAdded = false;
			boolean savingsAdded = false;
			boolean checkingAdded = false;
			boolean savingsMatch = false;
			boolean checkingMatch = false;
			String tempSavingsBalance = "";
			String tempCheckingBalance = "";
			
			//loop through every character
			for(int i = 0; i < line2.length(); i++){
				// when we find a colon or end of line, there is a word
				if (line2.charAt(i) == ':' || i == line2.length() - 1) {
					colonCount++;
					//first slot is account type
					if(colonCount == 1){
						//break if it is not a customer type of account
						String dataType = line2.substring(location, i);
						if (!(dataType.equals("customer"))){
							break;
						}
					}
					
					//store customers id for future use
					if(colonCount == 2){
						tempCustomerId = line2.substring(location, i);
					}
					//store customers username for future use
					if(colonCount == 3){
						tempCustomerUsername = line2.substring(location, i);
					}
					//get employee id tied to customer account
					if(colonCount == 5){
						int customerEmployeeId = Integer.parseInt(line2.substring(location, i + 1));
						if(customerEmployeeId == employeeId){
							match = true;
						}
					}
					//if customer's employee id match with current employee then its a match
					if(match){
						//inner loop, traverse file with current customer id to look for matching 
						//savings and checking accounts
						for(String line3 : file2){
							int colonCount2 = 0;
							String dataType2 = "";
							int location2 = 0;
							String currCustomerId = "";
							
							//loop through every character
							for(int j = 0; j < line3.length(); j++){
								// when we find a colon or end of line, there is a word
								if (line3.charAt(j) == ':' || j == line3.length() - 1) {
									colonCount2++;
									if(colonCount2 == 1){
										dataType2 = line3.substring(location2, j);
										//only looking for saving and checking accounts
										if((!(dataType2.equals("savings"))) && (!(dataType2.equals("checking")))){
											break;
										}
									}
									if(colonCount2 == 2){
										currCustomerId = line3.substring(location2, j);
									}
									//only look at accounts that match customer id
									if(colonCount2 == 4 && currCustomerId.equals(tempCustomerId)){
										//get savings balance and mark as savings found
										if(dataType2.equals("savings") && !(savingsMatch)){
											tempSavingsBalance = line3.substring(location2, j);
											savingsMatch = true;
										}
										//get checking balance and mark as checking found
										else if(dataType2.equals("checking") && !(checkingMatch)){
											tempCheckingBalance = line3.substring(location2, j);
											//System.out.println("tempChk: " + tempCheckingBalance);
											checkingMatch = true;
										}
									}
	
									location2 = j + 1;
								}
							}
							//leave if checking and savings account already found
							if(checkingMatch && savingsMatch)
								break;

						}
						//only enter in database in a group of: customer, savings account(if they have one), and 
						//checking account(if they have one) all at once to group them in the array list
						if(!customerAdded && !savingsAdded && !checkingAdded){
							String[] customerName = {tempCustomerUsername, tempCustomerId};
							listOfCustomers.add(customerName);
							customerAdded = true;
							if(savingsMatch){
								String[] savingsAccount = {"savings", tempSavingsBalance};
								listOfCustomers.add(savingsAccount);
								savingsAdded = true;
							}
							if(checkingMatch){
								String[] checkingAccount = {"checking", tempCheckingBalance};
								listOfCustomers.add(checkingAccount);
								checkingAdded = true;
							}
						}
					}
					location = i + 1;
				}
			}
		}
		return listOfCustomers;
	}

	/**
	 * Gets first unapproved account and allows employee to approve or decline
	 * @param sc input scanner
	 * @return String array of the resulting account
	 */
	public String[] getAccountApplications(BufferedReader sc){
		String[] result = {"", "", ""};
		String line2;
		//2 copies of data file to iterate through
		ArrayList<String> file= new ArrayList<String>();
		ArrayList<String> file2= new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			//populate copies of data file to traverse
			while((line2 = br.readLine()) != null){
				file.add(line2);
				file2.add(line2);
			}
			
		}
		 catch (IOException e) {
			 l.error(e);
			System.out.println("IO Exception");
		} catch (Exception e) {
			l.error(e);
			System.out.println("General Exception");
		}
			
			//variables used in finding and updating accounts
			String oldText = "";
			String tempCustomerId = "";
			String currBalance = "";
			String employeeId = "";
			String accountType = "";
			String approve = "false";
			boolean found = false;
			boolean throughLine = false;
			
			//while ((line = br.readLine()) != null && !found) {
			for(String line : file){
				//only uses first account unapproved
				if(!found){
				
					//reset approve to false for each new line
					approve = "false";
	
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
								accountType = dataType;
								// only looking for savings and checking
								if ((!(dataType.equals("savings"))) && (!(dataType.equals("checking")))) {
									break;
								}
							}
							
							//maintain customerId so you dont have to go backwards in line
							if(colonCount == 2 && !found)
								tempCustomerId = line.substring(location, i);
							
							//only looking for unapproved accounts
							if(colonCount == 3){
								if((line.substring(location, i)).equals("true")){
									break;
								}
								else{
									//when an account is false, we found one to approve or decline
									found = true;
								}
							}
							//if you haven't been through the whole line once, get balance
							if(colonCount == 4 && !throughLine)
								currBalance = line.substring(location, i);
							//if you haven't been through the whole line once, get employeeId
							if(colonCount == 5 && !throughLine)
								employeeId = line.substring(location, i + 1);
							//if you're at the end of line and found a false account, you mark you've traversed
							if(colonCount == 5 && found)
								throughLine = true;
							
							location = i + 1;
						}
					}
				}
			}
			String[] account = {accountType, tempCustomerId, approve, currBalance, employeeId};
			boolean accountApproval = approveAccountApplications(account, sc);
			if(accountApproval)
				approve = "true";
			result[0] = accountType;
			result[1] = tempCustomerId;
			result[2] = approve;
			
			for(String newLine : file2){
				oldText += newLine + "\r\n";
			}
			//if employee approves, overwrite false to true on account, so its usable
			if(accountApproval){
				String newText = oldText.replaceAll(accountType + ":" + tempCustomerId + ":" + "false" + ":" + currBalance + ":" + employeeId, 
						accountType + ":" + tempCustomerId + ":" + "true" + ":" + currBalance + ":" + employeeId);
			
				try{
					BufferedWriter bw2 = new BufferedWriter(new FileWriter(
							"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\bankingproject\\Data.txt"));
					bw2.write(newText);
					bw2.close();
				}
				catch(IOException e){
					l.error(e);
					System.out.println(e);
				}
			}
			//if employee declines, delete account from database
			else if(!accountApproval){
				String newText = oldText.replaceAll(accountType + ":" + tempCustomerId + ":" + "false" + ":" + currBalance + ":" + employeeId, 
						"");
				try{
					BufferedWriter bw2 = new BufferedWriter(new FileWriter(
							"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Websterb_BankingProject\\src\\com\\revature\\bankingproject\\Data.txt"));
					bw2.write(newText);
					bw2.close();
				}
				catch(IOException e){
					l.error(e);
					System.out.println(e);
				}
			}
			
		
		return result;
	}
	
	/**
	 * Takes in string array, presents the account and asks employee for approve or decline
	 * @param account String array representing the account
	 * @param sc input scanner
	 * @return true or false depending on approve or decline
	 */
	public boolean approveAccountApplications(String[] account, BufferedReader sc){
		int response = 0;
		boolean approve = false;

		System.out.println(account[0] + " for " + "customer id: " + account[1]);
		System.out.println("------------");
		System.out.println("Approve: 1");
		System.out.println("Decline: 2");
		
		try{
			response = Integer.parseInt(sc.readLine());
		}
		catch(IOException e){
			l.error(e);
			System.out.println(e);
		}
		
		//input approve or decline by employee
		switch(response){
		case 1: 
			approve = true;
			break;
		case 2:
			approve = false;
			break;
		default: 
			System.out.println("Not a valid option");
			break;
		}
		return approve;
	}

	public void calculator(BufferedReader sc){
		
		System.out.println("   Tiny Calculator " );
		System.out.println("  -----------------");
		System.out.println("|   7   8   9   /  |");
		System.out.println("|   4   5   6   x  |");
		System.out.println("|   1   2   3   -  |");
		System.out.println("|   0   .   +   =  |");
		System.out.println("  -----------------\n");
		System.out.println("Type your operations (Parentheses are okay)");
		
		boolean calculateMore = true;
		while(calculateMore){
			
			String calculateString = "5 * 50 3 / 3 1*10";
			findMultiplications( calculateString );
			
			calculateMore = false;
		}
	}
	
	
    public void findMultiplications( String calculateString ) {

        Pattern multiplicationPattern = Pattern.compile( "\\d+\\s*\\|/|*|-|+|\\s*\\d+" );

        Matcher multiplicationMather = multiplicationPattern.matcher( calculateString );

        while ( multiplicationMather.find() ) {
        	if(multiplicationMather.group().length() != 0){
        		multiplicationMather.group().trim();
                int int1 = multiplicationMather.start();
                int int2 = multiplicationMather.end();
                
                System.out.println(int1 +" " + int2);
        	}


        }
    }
    
    static int add(int int1, int int2) {
        return int1 + int2;
    }
    static int subtract(int int1, int int2) {
        return int1 - int2;
    }
    static int multiply(int int1, int int2) {
        return int1 * int2;
    }
    static int divide(int int1, int int2) {
        return int1 / int2;
    }


}

package banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
public class CommandParser {
	private BankMember currentAcc;
	private DatabaseConnect dc;
	private BankDatabase db;
	private File file;
	static final  Logger l = Logger.getRootLogger();
	//private boolean canLogin = true;
	public CommandParser(File file, BankDatabase db){
		this.file = file;
		this.db = db;
		this.dc = new DatabaseConnect();
		currentAcc = null;
		//l.trace("this is working");
		//logSystem.out.println(db.checkUserNameAvailability("Bo$$"));
	}
	
	/*
	 * This function will get the commands from the command line and execute them based on the permissions of the user
	 * 
	 */
	public void getCommands(boolean fromFile){
		Scanner scan = null;
		if(fromFile){
			File fileOut = new File("test.txt");
			try {
				scan = new Scanner(fileOut);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			scan = new Scanner(System.in);
		}
		//Scanner scan = new Scanner(System.in);
		
		//keep getting the input
		this.readDataBaseFromFile();
		boolean continueRunning = true;
		while(continueRunning){ //run
			if (currentAcc == null){ //we need to log in
				System.out.println("*********************************************\n"
									+ "Welcome to the Reva-Banking App Version 2.0\n*********************************************"
						+ "\nType login register or exit");
//				System.out.println("Welcome to the Revature Banking app!\nNew users type register to set up Username/Password. "
//						+ "Returning users type login to continue to sign in screen, type exit to end session");
				String str = scan.nextLine();				
				//check what is inputed
				switch(str){ 
				case "register":		//we need to register a user		
					System.out.println("Enter UserName:");
					str = scan.nextLine();				
					if(!dc.isUNameAvalible(str)){ // if user name is taken exit
						System.out.println("ERROR USER NAME IS UNAVALABLE");
						break;
					}
					//else make a new user and add him
					BankMember newMember = new BankMember(null, Type.CUSTOMER);
					newMember.setUserName(str);					
					
					//sets the pass word
					System.out.println("Enter password:");
					str = scan.nextLine();	
					newMember.setPassword(str);
					
					
					//get the customers name information
					System.out.println("What is your name?");
					str = scan.nextLine();
					newMember.setName(str);
					//adds the newMember to the database
					db.addMember(newMember);
					dc.addUser(newMember.getName(), newMember.getUserName(), newMember.getPassword(), Type.CUSTOMER);
					currentAcc = newMember;
					dc.addLog("registered " + newMember.getUserName());
					//l.trace("registered " + newMember.getUserName());
					
					break;	
				case "login": //user is logging in
					System.out.println("Please input your username:");
					str = scan.nextLine();
					BankMember curr = null;
					
					//check the username exists
					if(!dc.isUNameAvalible(str)){ //username exists
						curr = dc.getBankMember(str);
					}
					else{
						System.out.println("Error user name doesn't exist"); //if it doesn't then tell the user 
						break;
					}
					
					System.out.println("Please input your password:");
					str = scan.nextLine();
					//check password matches
					if (curr.getPassword().equals(str)){
						currentAcc = curr;
					}
					else{
						System.out.println("ERROR WRONG PASSWORD");
						break;
					}
					dc.addLog("login- " + currentAcc.getUserName());
					//l.trace("login- " + currentAcc.getUserName());
					break;
				case "exit":
					 //makes the filestream to write
					FileWriter fw = null;
					try {			
//						file.delete();
//						file = new File("banking.txt");
						fw = new FileWriter(file.getAbsolutePath());
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
					//writes to the file every account in the data base
					BufferedWriter write = new BufferedWriter(fw);
					try {			
						
						writeToFile(write);
						write.close();
						fw.close();
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					//ends the program
					dc.addLog("exiting program");
					//l.trace("exiting program");
					scan.close();
					continueRunning = false;
					//System.exit(0);
					
					break;
				default: //Invalid command and prints out the invalid command
					//l.trace("IVALID INPUT BY " + currentAcc.getUserName());
					System.out.println("ERROR INVALID INPUT: " + str);
					break;
				}
				
			}
			else{ // we are logged in to an account and want to provide actions to manage teh account
				System.out.println("Welcome " + currentAcc.getUserName() + ", Type help for list of commands\n------------------------------------");
				//System.out.println("------------------------\nType help for list of commands\n------------------------");
				
				Type t = currentAcc.getType();
				String str = scan.nextLine();
				
				//set current user to null so a new user can log in
				if(str.equals("logout")){
					dc.addLog("logout by- " + currentAcc.getUserName());
					//l.trace("logout by- " + currentAcc.getUserName());
					System.out.println("Have a nice day!");
					currentAcc = null;
				}
				else{ // we have someone logged in
					switch(t){
					case CUSTOMER: // the user is a customer
						if(str.equals("apply checking")){ //need to apply for a checking account
							if(currentAcc.getCheckingStatus() != Status.ACTIVE){ // don't apply if they already have one 
								dc.setAccountStatus(currentAcc.getUserName(), 2, 1);
								currentAcc.setCheckingStatus(Status.APPLIED);
								dc.addLog("user " + currentAcc.getUserName() + " applied for a checking account");
								//l.trace("user " + currentAcc.getUserName() + " applied for a checking account");
								System.out.println("thank you for applying for a checking account");
							}				
							
						}
						else if (str.equals("apply savings")){ //apply for savings
							if (currentAcc.getSavingStatus() != Status.ACTIVE){ //don't apply if they have one
								currentAcc.setSavingStatus(Status.APPLIED);
								dc.setAccountStatus(currentAcc.getUserName(), 2, 2);
								dc.addLog("user " + currentAcc.getUserName() + " applied for a saving account");
								//l.trace("user " + currentAcc.getUserName() + " applied for a saving account");
								System.out.println("Thank you for applying for a savings account");
							}
							
						}
						else if (str.equals("help")){ //prints out customer commands
							System.out.println("Commands for Customers: apply checking, apply savings,"
									+ " deposit savings, deposit checking, withdraw savings, withdraw checking");
							dc.addLog("user " + currentAcc.getUserName() + " displayed help");
							//l.trace("user " + currentAcc.getUserName() + " displayed help");
						}
						else if (str.equals("deposit checking")){ //they want to deposit 
							
							if(currentAcc.getCheckingStatus() != Status.ACTIVE){ //if they don't have an account don't let them
								System.out.println("ERROR CHECKING ACCOUNT NOT APPROVED");
								dc.addLog("INVALID ACCESS TO UNAPPROVED CHECKING ACCOUNT by " + currentAcc.getUserName());
								//l.trace("INVALID ACCESS TO UNAPPROVED CHECKING ACCOUNT by " + currentAcc.getUserName());
								break;
							}
							System.out.println("Enter amount to deposit");
							str = scan.nextLine();
							
							Integer in = null;
							//check that they inputed a number
							try{
								in = new Integer(str);
							}catch(NumberFormatException e){
								System.out.println("ERROR INCORRECTLY FORMATED DEPOSIT:" + str);
								l.trace("INCORRECLTY FORMATED DEPOSIT by " + currentAcc.getUserName() + " -" + str);
								break;
							}
							//update the account total
							currentAcc.setChecking(currentAcc.getChecking() + in.intValue());
							dc.setAccountBalance(currentAcc.getUserName(), currentAcc.getChecking(), 1);
							dc.addLog("user " + currentAcc.getUserName() + " added $" + in.intValue() + " to checking account");
							//l.trace("user " + currentAcc.getUserName() + " added $" + in.intValue() + " to checking account");
							System.out.println("Current Checking amount $" + currentAcc.getChecking());
						}
						else if (str.equals("deposit savings")){ //they want to deposit to savings
							if(currentAcc.getSavingStatus() != Status.ACTIVE){ //if they don't have an account dont let them
								System.out.println("ERROR SAVINGS ACCOUNT NOT APPROVED");
								dc.addLog("INVALID ACCESS TO UNAPPROVED SAVINGS ACCOUNT by " + currentAcc.getUserName());
								//l.trace("INVALID ACCESS TO UNAPPROVED SAVINGS ACCOUNT by " + currentAcc.getUserName());
								break;
							}
							System.out.println("Enter amount to deposit");
							str = scan.nextLine();
							
							Integer in = null;
							// make sure they inputted a number
							try{
								in = new Integer(str);
							}catch(NumberFormatException e){
								System.out.println("ERROR INCORRECTLY FORMATED DEPOSIT:" + str);
								//l.trace("INCORRECLTY FORMATED DEPOSIT by " + currentAcc.getUserName() + " -" + str);
								dc.addLog("INCORRECLTY FORMATED DEPOSIT by " + currentAcc.getUserName() + " -" + str);
								break;
							}
							//update account
							currentAcc.setSavings(currentAcc.getSavings() + in.intValue());
							dc.setAccountBalance(currentAcc.getUserName(), currentAcc.getSavings(), 2);
							System.out.println("Current Savings amount $" + currentAcc.getSavings());
							dc.addLog("user " + currentAcc.getUserName() + " added $" + in.intValue() + " to savings account");
							//l.trace("user " + currentAcc.getUserName() + " added $" + in.intValue() + " to savings account");
						}
						else if (str.equals("withdraw savings")){ 
							if(currentAcc.getSavingStatus() != Status.ACTIVE){ //if they don't have an account
								System.out.println("ERROR SAVING ACCOUNT NOT APPROVED");
								dc.addLog("INVALID ACCESS TO UNAPPROVED SAVINGS ACCOUNT by " + currentAcc.getUserName());
								//l.trace("INVALID ACCESS TO UNAPPROVED SAVINGS ACCOUNT by " + currentAcc.getUserName());
								break;
							}
							System.out.println("Enter amount to withdraw");
							str = scan.nextLine();
							
							Integer in = null;
							//make sure they typed a number
							try{
								in = new Integer(str);
							}catch(NumberFormatException e){
								//l.trace("INCORRECLTY FORMATED DEPOSIT by " + currentAcc.getUserName() + " -" + str);
								dc.addLog("INCORRECLTY FORMATED DEPOSIT by " + currentAcc.getUserName() + " -" + str);
								System.out.println("ERROR INCORRECTLY FORMATED WITHDRAW:" + str);
								break;
							}
							
							//make sure they don't withdraw more than they have
							if(in > currentAcc.getSavings()){
								dc.addLog("User " + currentAcc.getUserName() + " tried to withdraw too much from savings account");
								//l.trace("User " + currentAcc.getUserName() + " tried to withdraw too much from savings account");
								System.out.println("ERROR WITHDRAW AMMOUNT EXCEEDS CURRENT TOTAL");
								break;
							}
							//update the account
							currentAcc.setSavings(currentAcc.getSavings() - in.intValue());
							dc.setAccountBalance(currentAcc.getUserName(), currentAcc.getSavings(), 2);
							//l.trace("user " + currentAcc.getUserName() + "withdrew $" + in.intValue() + " to savings account");
							dc.addLog("user " + currentAcc.getUserName() + "withdrew $" + in.intValue() + " to savings account");
							System.out.println("Current Savings amount $" + currentAcc.getSavings());
						}
						else if(str.equals("withdraw checking")){ 
							//currentAcc.setCheckingStatus(Status.ACTIVE);
							if(currentAcc.getCheckingStatus() != Status.ACTIVE){ //if they don't have an account
								//l.trace("INVALID ACCESS TO UNAPPROVED CHECKING ACCOUNT by " + currentAcc.getUserName());
								dc.addLog("INVALID ACCESS TO UNAPPROVED CHECKING ACCOUNT by " + currentAcc.getUserName());
								System.out.println("ERROR CHECKING ACCOUNT NOT APPROVED");
								break;
							}
							System.out.println("Enter amount to withdraw");
							str = scan.nextLine();
							
							Integer in = null;
							//make sure they number is a number
							try{
								in = new Integer(str);
							}catch(NumberFormatException e){
								//l.trace("INCORRECLTY FORMATED DEPOSIT by " + currentAcc.getUserName() + " -" + str);
								dc.addLog("INCORRECLTY FORMATED DEPOSIT by " + currentAcc.getUserName() + " -" + str);
								System.out.println("ERROR INCORRECTLY FORMATED WITHDRAW:" + str);
								break;
							}
							//make sure they don't withdraw more than they have
							if(in > currentAcc.getChecking()){
								System.out.println("ERROR WITHDRAW AMMOUNT EXCEEDS CURRENT TOTAL");
								dc.addLog("User " + currentAcc.getUserName() + " tried to withdraw too much from checking account");
								//l.trace("User " + currentAcc.getUserName() + " tried to withdraw too much from checking account");
								break;
							}
							//update the account
							currentAcc.setChecking(currentAcc.getChecking() - in.intValue());
							dc.setAccountBalance(currentAcc.getUserName(), currentAcc.getChecking(), 1);
							System.out.println("Current Checking amount $" + currentAcc.getChecking());
							dc.addLog("user " + currentAcc.getUserName() + "withdrew $" + in.intValue() + " from checking account");
							//l.trace("user " + currentAcc.getUserName() + "withdrew $" + in.intValue() + " from checking account");
						}
						else{ //wrong command typed
							//l.trace("user- " + currentAcc.getUserName() + "entered invalid command");
							dc.addLog("user- " + currentAcc.getUserName() + "entered invalid command");
							System.out.println("ERRROR INVAILD COMMAND: " + str);
						}
						
						break;
					case EMPLOYEE: //employee is logged in
						if(str.equals("view all")){ //view all customer accounts
							//l.trace("Employee " + currentAcc.getUserName() + " viewed all customers");
							dc.addLog("Employee " + currentAcc.getUserName() + " viewed all customers");
							dc.printCustomers();
						}
						else if(str.equals("view waiting")){ //view all customer accounts with an application for an account
							dc.addLog("Employee " + currentAcc.getUserName() + " viewed all customer applications");
							//l.trace("Employee " + currentAcc.getUserName() + " viewed all customer applications");
							dc.printApplied();
						}
						else if (str.equals("approve")){ //lets the employee approve accounts
							System.out.println("enter the username of the account to approve");
							//type the userName to edit
							str = scan.nextLine();
							BankMember curr;
							//checks the username exists
							if(!dc.isUNameAvalible(str)){  
								curr = dc.getBankMember(str);
							}
							else{
								System.out.println("Error user name doesn't exist");
								break;
							}
							
							System.out.println("approve checking or savings?");
							str = scan.nextLine();
							//choose to approve a savings or checking account
							if(str.equals("savings")){ 
								curr.setSavingStatus(Status.ACTIVE);
								dc.setAccountStatus(curr.getUserName(), 3, 2);
								System.out.println("approved savings account");
								dc.addLog("Employee " + currentAcc.getUserName() + " approved a customers savings account");
								//l.trace("Employee " + currentAcc.getUserName() + " approved a customers savings account");
							} 
							else if(str.equals("checking")) {
								curr.setCheckingStatus(Status.ACTIVE);
								dc.setAccountStatus(curr.getUserName(), 3, 1);
								System.out.println("approved checking account");
								dc.addLog("Employee " + currentAcc.getUserName() + " approved a customers checking account");
								//l.trace("Employee " + currentAcc.getUserName() + " approved a customers checking account");
								}
							else {
								System.out.println("ERROR: " + str);
								}
							
							
							
						}
						else if(str.equals("help")){ //prints out valid commands
							l.trace("Employee " + currentAcc.getUserName() + " viewed help");
							dc.addLog("Employee " + currentAcc.getUserName() + " viewed help");
							System.out.println("commands for employee's are view all, view waiting, approve");
						}
						else{ //prints error if a command is typed that isn't valid
							//l.trace("Employee " + currentAcc.getUserName() + " used an invalid command");
							dc.addLog("Employee " + currentAcc.getUserName() + " used an invalid command");
							System.out.println("ERRROR INVALID COMMAND " + str);
						}
						break;
					case ADMIN: //if the user is the admin
						if (str.equals("view all")){ //views every account
							//l.trace("Admin " + currentAcc.getUserName() + " viewed all accounts");
							dc.addLog("Admin " + currentAcc.getUserName() + " viewed all accounts");
							dc.printAll();
						}
						else if(str.equals("add")){ //adds an employee to the database
							System.out.println("type Employee user name");
							str = scan.nextLine();
							BankMember curr;
							if(dc.isUNameAvalible(str)){ //check the username is avaliable
								
								curr = new BankMember(null, Type.EMPLOYEE);
								curr.setUserName(str);
							}
							else{ //error the user name is taken
								System.out.println("Error user name taken");
								break;
							}
							System.out.println("type their password");
							str = scan.nextLine();
							curr.setPassword(str);
							
							System.out.println("type their name");
							str = scan.nextLine();
							curr.setName(str);
							db.addMember(curr);
							dc.addUser(curr.getName(), curr.getUserName(), curr.getPassword(), Type.EMPLOYEE);
							dc.addLog("Admin " + currentAcc.getUserName() + " added new Employee " + curr.getUserName());
							//l.trace("Admin " + currentAcc.getUserName() + " added new Employee " + curr.getUserName());
							
							
						}
						else if(str.equals("edit")){ //edit userName
							BankMember curr;
							System.out.println("type userName of account you want to edit");
							str = scan.nextLine();
							//checks the the account to edit is real
							if(!dc.isUNameAvalible(str)){
								curr = dc.getBankMember(str);
								if(curr.getType() == Type.CUSTOMER){
								System.out.println("here is the account\n" + curr.getUserName() +" " + curr.getPassword() +  " checking status: " + curr.getCheckingStatus() +
										", checking amount: $" + curr.getChecking() + " saving status: " + curr.getSavingStatus() + 
										" saving amount: $" + curr.getSavings());
								}
								else{
									System.out.println("here is the User:\n" + curr.getUserName() +" " + curr.getPassword() + " " + curr.getName() );
								}
								//input field to edit
								System.out.println("enter field to edit: choose- checking, savings, userName, or password:");
								str = scan.nextLine();
								
								if(str.equals("checking")){ //change checking
									if(curr.getType() != Type.CUSTOMER){
										System.out.println("ERROR only Customers have checking accounts");
										break;
									}
									System.out.println(" input new checking amount");
									
									str = scan.nextLine();
									
									Integer in = null;
									//make sure its a number
									try{
										in = new Integer(str);
									}catch(NumberFormatException e){
										System.out.println("ERROR INCORRECTLY FORMATED NUMBER:" + str);
										break;
									}
									//set the new number
									curr.setChecking(in);
									dc.setAccountBalance(curr.getUserName(), curr.getChecking(), 1);
									System.out.println("changed checking");
								}
								else if(str.equals("savings")){ //change the savings amount
									if(curr.getType() != Type.CUSTOMER){
										System.out.println("ERROR only Customers have savings accounts");
										break;
									}
									System.out.println(" input new savings amount");
									
									str = scan.nextLine();
									//check the input is a number
									Integer in = null;
									try{
										in = new Integer(str);
									}catch(NumberFormatException e){
										System.out.println("ERROR INCORRECTLY FORMATED NUMBER:" + str);
										break;
									}
									//set the savings
									curr.setSavings(in);
									dc.setAccountBalance(curr.getUserName(), curr.getSavings(), 2);
									System.out.println("changed savings");
									
									
								}
								else if (str.equals("userName")){//changes the user name
									System.out.println(" input new userName");
								
									str = scan.nextLine();
									dc.updateUName(curr.getUserName(), str);
									//curr.setUserName(str);
									
									System.out.println("changed userName");
								}
								else if(str.equals("password")){ //changes a password
									System.out.println(" input new password");
									str = scan.nextLine();
									dc.updatePass(curr.getUserName(), str);
									//curr.setPassword(str);
									System.out.println("changed password");
								}
								else{
									System.out.println("ERROR INVAILD COMMAND " + str);
								}
								dc.addLog("Admin " + currentAcc.getUserName() + " edited the account of " + curr.getUserName());
								//l.trace("Admin " + currentAcc.getUserName() + " edited the account of " + curr.getUserName());
							}
							else{
								System.out.println("Error user name doesn't exist");
								break;
							}
							
						}
						else if(str.equals("help")){
							//l.trace("Admin" + currentAcc.getUserName() + " viewed help");
							dc.addLog("Admin" + currentAcc.getUserName() + " viewed help");
							System.out.println("commands for admin: view all, add, edit");
						}
						else{
							dc.addLog("Admin " + currentAcc.getUserName() + " used an invalid command");
							//l.trace("Admin " + currentAcc.getUserName() + " used an invalid command");
							System.out.println("ERRROR INVALID COMMAND " + str);
						}
						break;
					}
				}
			}
			
			
		}
		
	}
	
	
	/*
	 * this method reads from the .txt file and creats the data base
	 */
	public void readDataBaseFromFile(){
		
		FileReader fs;
		BufferedReader br = null;
		//set up the reader
		try {
			fs = new FileReader(file.getAbsolutePath());
			br = new BufferedReader(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {			
			String str = br.readLine();
			//read through the file
			while(str != null){
				//split the line into what we need
				String[] s =  str.split(":");
				Type t = null;
				//convert string to Type
				if(s[3].equals("CUSTOMER")) t = Type.CUSTOMER;
				if(s[3].equals("ADMIN")) t = Type.ADMIN;
				if(s[3].equals("EMPLOYEE")) t = Type.EMPLOYEE;
				
				Status checkS = null, savingS = null;
				//convert string to Status
				if(s[4].equals("ACTIVE")) checkS = Status.ACTIVE;
				if(s[4].equals("APPLIED")) checkS = Status.APPLIED;
				if(s[4].equals("NONE")) checkS = Status.NONE;
				//convert string to Status
				if(s[5].equals("ACTIVE")) savingS = Status.ACTIVE;
				if(s[5].equals("APPLIED")) savingS = Status.APPLIED;
				if(s[5].equals("NONE")) savingS = Status.NONE;
				
				//convert string to Integer
				Integer check = new Integer(s[6]);
				Integer save = new Integer(s[7]);
				//adds a new database member
				db.addMember(new BankMember(s[1], t, s[0], s[2], checkS, savingS, check.intValue(), save.intValue()));
				
				
				str = br.readLine();
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
				
		
	}
	
	/*
	 * writes the contents of the database into a file
	 */
	public void writeToFile(BufferedWriter w){
		//writes every member of the database to an array 
		for(BankMember b : db.getArray()){
			try {
				w.write(b.getUserName() + ":" + b.getName() + ":" + b.getPassword()+ ":" + b.getType() + ":" 
						+ b.getCheckingStatus() + ":" + b.getSavingStatus() + ":" + b.getChecking() + ":" + b.getSavings() + "\n");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * displays every customer to the command line
	 */
	public void employeeViewCustomers(){
		for (BankMember b : db.getArray()){ //access every element in the database
			if(b.getType() == Type.CUSTOMER){ //only print customers
				System.out.println(b.getUserName() + " checking status: " + b.getCheckingStatus() +
						", checking amount: $" + b.getChecking() + " saving status: " + b.getSavingStatus() + 
						" saving amount: $" + b.getSavings());
			}
		}
	}
	
	/*
	 * prints every customer with a pending application
	 */
	public void employeeViewApplications(){
		for (BankMember b : db.getArray()){
			if(b.getType() == Type.CUSTOMER && 
					(b.getCheckingStatus() == Status.APPLIED || b.getSavingStatus() == Status.APPLIED)){ //only print customer with applications
				System.out.println(b.getUserName() + " checking status: " +
					b.getCheckingStatus() + " savings status: " +  b.getSavingStatus());
			}
		}
	}
	
	/*
	 * prints every member in the database to the command line
	 */
	public void adminView(){
		for (BankMember b : db.getArray()){
			
			System.out.println(b.getUserName() + " checking status: " + b.getCheckingStatus() +
					", checking amount: $" + b.getChecking() + " saving status: " + b.getSavingStatus() + 
					" saving amount: $" + b.getSavings());
			
		}
		
	}
	
	
	

}

package banking;

import java.io.File;
import java.io.IOException;


/**
 * This is the main function to run the banking app. type "help" to show commands while logged in and not executing 
 * another command
 * 
 * The Admin is the only person in a new database and can add employees
 * The Admin username - "Bo$$" password- "123"
 * 
 * while logged in "help" can be typed to list the commands you can use
 * 
 * @author Zachary
 *
 */

public class Main {

	/*
	 * runs the program
	 */
	public static void main(String[] args) {
		//make files
		boolean fromFile = false;
		if(args.length == 1){
			fromFile = true;
		}
		File file = new File("banking.txt");
		BankDatabase db = new BankDatabase();
		
		//if the file doesn't exist then create a new file
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//if the file is new add an Admin 
			BankMember admin = new BankMember("Boss", Type.ADMIN);
			admin.setUserName("Bo$$");
			admin.setPassword("123");
			db.addMember(admin);			
		}
		
		
		
		
		
		//read lines from the command line
		CommandParser pars = new CommandParser(file, db);
		pars.getCommands(fromFile);

	}

}

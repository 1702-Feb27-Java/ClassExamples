package com.revature.banking2.app;

import com.revature.banking2.pojo.User;
import com.revature.banking2.ui.*;

public class Main {

	public static void main(String[] args) {
		
		// Get cli input to determine which menu to bring up.
		String cli = null;
		if (args.length > 0) { // if there was input when invoking the app then args will be non-zero.
			cli = args[0];
		}
		
		// The menu to be brought up.
		Menu menu = null;
		
		// Invoke customer menu.
		if (cli == null || (args.length > 0 ? cli.equals("customer") : false)) {
			menu = new CustomerMenu();
		}
		// Invoke employee menu.
		else if (cli.equalsIgnoreCase("employee") || cli.equalsIgnoreCase("bank")) {
			menu = new EmployeeMenu();
		}
		// Invoke admin menu.
		else if (cli.equalsIgnoreCase("admin")) {
			menu = new AdminMenu();
		}
		// Invoke help menu.
		else if (cli.equalsIgnoreCase("help") || cli.equalsIgnoreCase("man")){
			menu = new HelpMenu();
		}
		else {
			menu = new HelpMenu();
		}
		
		// Open the menu.
		menu.open();
		
	}

}

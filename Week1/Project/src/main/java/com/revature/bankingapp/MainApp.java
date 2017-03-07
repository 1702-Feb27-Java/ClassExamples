package com.revature.bankingapp;
import java.util.Scanner;

import com.revature.bankingapp.menu.IMenu;
import com.revature.bankingapp.menu.LoginMenu;
import com.revature.bankingapp.model.Database;

public class MainApp {
	public static void main(String[] args){
		
			Scanner scan = new Scanner(System.in);
						
			IMenu menu = new LoginMenu();
	
			/*open the menu for the user to operate with,
			 * it will return the next menu to go to. (if applicable)
			 * it will save any time a menu changes  
			 */
			do{
				menu = menu.openMenu(scan);	
				Database.getDatabase().save();			
			} while(menu != null);
		
	}

}

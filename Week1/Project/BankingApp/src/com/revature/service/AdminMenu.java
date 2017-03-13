package com.revature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.revature.dao.DAOAccountImp;
import com.revature.dao.DAOUserImp;
import com.revature.pojo.AccountClass;
import com.revature.pojo.UserClass;

// everything in an admin menu

public class AdminMenu {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static DAOAccountImp daoAccount = new DAOAccountImp();
	static DAOUserImp daoUser = new DAOUserImp();
	// this makes the menu functional and take inputs from the user
	public static void functionality() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			int aMenuInput = Integer.parseInt(br.readLine());
			
			// menu options selected by user and return to admin menu
			switch (aMenuInput) {
			case 1:
				// view pending accounts
				viewPending();
				AdminMenu.functionality();
				break;
			case 2:
				// view all accounts
				viewAll();
				AdminMenu.functionality();
				break;
			case 3:
				// edit accounts
				editAccount();
				AdminMenu.functionality();
				break;
			case 4:
				MenuClass.showMainMenu();
				MainMenu.functionality();
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	// edit account method, allows admin to edit customer info
	public static void editAccount() {

		try {
			ArrayList<UserClass> allUsers = new ArrayList<UserClass>();
			allUsers = daoUser.getAllUsers();

			// print all customer accounts
			System.out.println("These are all the customer accounts.");
			allUsers.forEach(System.out::println);
			System.out.println("Please enter the id of the customer you wish to edit");

			int id = Integer.parseInt(br.readLine());
			// store this id

			UserClass currentUser = new UserClass();
			currentUser = daoUser.getUserByID(id);
			// calls what to edit method
			whatToEdit(currentUser, id);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	public static void whatToEdit(UserClass uc, int id) {

		try {			
			// shows what admin can edit
			MenuClass.showEditMenu();
			int input = Integer.parseInt(br.readLine());
			int input2;

			// based on customer input, edit one of four fields
			// first name, last name, username, password
			switch (input) {
			case 1: // first name
				System.out.println("What would you like to replace the first name with?");
				String firstName = br.readLine();
				
				// calls method to edit
				daoUser.updateFirstName(uc, firstName);
				
				System.out.println("Success, you have edited the customer's first name.");
				System.out.println("Would you like to edit something else? 1 for YES and 2 for NO");
				input2 = Integer.parseInt(br.readLine());
				switch(input2){
				case 1: // yes
					whatToEdit(uc, id);
					break;
				case 2: // no
					System.out.println("Returning you to the admin menu.");
					MenuClass.showAdminMenu();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}
				break;
			case 2: // last name
				System.out.println("What would you like to replace the last name with?");
				String lastName = br.readLine();

				daoUser.updateLastName(uc, lastName);
				System.out.println("Success, you have edited the customer's last name.");
				System.out.println("Would you like to edit something else? 1 for YES and 2 for NO");
				input2 = Integer.parseInt(br.readLine());
				switch(input2){
				case 1: // yes
					whatToEdit(uc, id);
					break;
				case 2: // no
					System.out.println("Returning you to the admin menu.");
					MenuClass.showAdminMenu();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}
				break;
			case 3: // username
				System.out.println("What would you like to replace the username with?");
				String username = br.readLine();

				daoUser.updateUsername(uc, username);
				System.out.println("Success, you have edited the customer's username.");
				System.out.println("Would you like to edit something else? 1 for YES and 2 for NO");
				input2 = Integer.parseInt(br.readLine());
				switch(input2){
				case 1: // yes
					whatToEdit(uc, id);
					break;
				case 2: // no
					System.out.println("Returning you to the admin menu.");
					MenuClass.showAdminMenu();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}
				// String username = br.readLine();
				break;
			case 4: // password
				System.out.println("What would you like to replace the password with?");
				String password = br.readLine();
				
				daoUser.updatePassword(uc, password);
				System.out.println("Success, you have edited the customer's password.");
				System.out.println("Would you like to edit something else? 1 for YES and 2 for NO");
				input2 = Integer.parseInt(br.readLine());
				switch(input2){
				case 1: // yes
					whatToEdit(uc, id);
					break;
				case 2: // no
					System.out.println("Returning you to the admin menu.");
					MenuClass.showAdminMenu();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}
				// String password = br.readLine();
				break;
			case 5: // exit to previous menu
				MenuClass.showAdminMenu();
				AdminMenu.functionality();
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	// view all pending accounts
	public static void viewPending() {

		try {
			ArrayList<AccountClass> pendingAcc = new ArrayList<AccountClass>();
			pendingAcc = daoAccount.getAccountsByStatus(1);
			
			if (pendingAcc.size() != 0) {
				System.out.println("There are the current accounts pending for approval.");
				pendingAcc.forEach(System.out::println);
				System.out.println("Would you like to approve the new accounts? Press 1 for YES and 2 for NO");
				int yesOrNo = Integer.parseInt(br.readLine());

				switch (yesOrNo) {

				case 1: // yes we will approve
					approve();
					break;
				case 2: // no and it will exit to admin menu
					MenuClass.showAdminMenu();
					AdminMenu.functionality();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}

			} else {
				System.out.println("There are no new accounts pending for approval.");
				MenuClass.showAdminMenu();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	// method to view all accounts, pending or not
	public static void viewAll() {

		try {
			ArrayList<AccountClass> allAcc = new ArrayList<AccountClass>();
			allAcc = daoAccount.getAllAccounts();
			
			System.out.println("These are all the customer accounts.");
			allAcc.forEach(System.out::println);
			
			System.out.println("---------------------------");
			System.out.println("Press 1 to return to the previous menu.");
			
			int confirm = Integer.parseInt(br.readLine());
			
			switch (confirm) {
			case 1: // yes
				MenuClass.showAdminMenu();
				AdminMenu.functionality();
				break;
			default:
				System.out.println("You cannot make that selection. Try again.");
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	//method to approve new accounts
	public static void approve() {

		try {			
			// self-explanatory
			System.out.println("---------------------------");
			System.out.println("Please enter the id of the customer you want to approve: ");

			// set the id (must include the n) to value
			int id = Integer.parseInt(br.readLine());
			
			ArrayList<AccountClass> pendingAcc = new ArrayList<AccountClass>();
			
			pendingAcc = daoAccount.getAccountsByUserID(id);

			System.out.println("Approving now...");
			
			for (AccountClass a : pendingAcc){
				daoAccount.updateStatus(a, 2, 1);
			}
			
			System.out.println("Approval complete. Returning to the admin menu.");
			MenuClass.showAdminMenu();
			AdminMenu.functionality();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
}
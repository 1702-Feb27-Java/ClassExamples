package com.revature.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DAOAccountImp;
import com.revature.pojo.AccountClass;

public class EmployeeMenu {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static DAOAccountImp daoAccount = new DAOAccountImp();
	// prints menu for employee
	// takes user input and implement functionality
	public static void functionality() {

		try {
			int emMenuInput = Integer.parseInt(br.readLine());

			switch (emMenuInput) {
			case 1:
				// view pending accounts
				viewPending();
				EmployeeMenu.functionality();
				break;
			case 2:
				// view all accounts
				viewAll();
				EmployeeMenu.functionality();
				break;
			case 3: // go back to the root menu
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

	public static void viewPending() {

		try {
			
			// calls the DAO method to get all accounts that are pending
			ArrayList<AccountClass> pendingAcc = new ArrayList<AccountClass>();
			pendingAcc = daoAccount.getAccountsByStatus(1);
			
			// if the list size is not empty, then there are accounts to be approved
			if (pendingAcc.size() != 0) {
				System.out.println("There are the current accounts pending for approval.");
				pendingAcc.forEach(System.out::println);
				System.out.println("Would you like to approve the new accounts? Press 1 for YES and 2 for NO");
				int yesOrNo = Integer.parseInt(br.readLine());

				switch (yesOrNo) {

				case 1: // yes we will approve
					approve();
					break;
				case 2: // no and it will exit to employee menu
					MenuClass.showEmployeeMenu();
					EmployeeMenu.functionality();
					break;
				default:
					System.out.println("You cannot make that selection. Try again.");
					break;
				}

			} else {
				System.out.println("There are no new accounts pending for approval.");
				MenuClass.showEmployeeMenu();
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
			// calls the DAO method to get all accounts and store into an arraylist
			ArrayList<AccountClass> allAcc = new ArrayList<AccountClass>();
			allAcc = daoAccount.getAllAccounts();
			
			System.out.println("These are all the customer accounts.");
			allAcc.forEach(System.out::println);
			
			System.out.println("---------------------------");
			System.out.println("Press 1 to return to the previous menu.");
			
			int confirm = Integer.parseInt(br.readLine());
			
			switch (confirm) {
			case 1: // yes
				MenuClass.showEmployeeMenu();
				EmployeeMenu.functionality();
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
			System.out.println("Please enter the id of the account you want to approve: ");

			// set the id 
			int id = Integer.parseInt(br.readLine());
		
			// use the DAO method to get all accounts under a user
//			ArrayList<AccountClass> pendingAcc = new ArrayList<AccountClass>();
//			pendingAcc = daoAccount.getAccountsByUserID(id);
//			System.out.println(pendingAcc);

			System.out.println("Approving now...");
			
			//for (AccountClass a : pendingAcc){
				// update each account through a loop
				// 2 (status to pending), and 2 (resolver id, aka an employee)
				daoAccount.updateStatus(id, 2, 2);
				
				
			//}
			
			System.out.println("Approval complete. Returning to the employee menu.");
			MenuClass.showEmployeeMenu();
			EmployeeMenu.functionality();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}
}

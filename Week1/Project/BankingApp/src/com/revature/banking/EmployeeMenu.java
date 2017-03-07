package com.revature.banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class EmployeeMenu {

	public static void functionality() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
			case 3:
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
			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			String thisLine;

			// this array stores all the
			String[] lineArr = null;

			// this array stores the id of all new/pending customer accounts
			// to approve an account, the first char ('n') of the id string will
			// be removed
			List<String> pendingAcc = new ArrayList();

			while ((thisLine = br.readLine()) != null) {
				lineArr = thisLine.split(":");
				if (lineArr[0].charAt(0) == 'n') { // if the id has an n as its
													// first char
					pendingAcc.add(thisLine); // we store it in the pending
												// accounts array
				}
			}

			if (pendingAcc.size() != 0) {
				System.out.println("There are the current accounts pending for approval.");
				System.out.println(pendingAcc);
				System.out.println("Would you like to approve the new accounts? Press 1 for YES and 2 for NO");
				int yesOrNo = Integer.parseInt(b.readLine());

				// dflsdfs
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

	public static void viewAll() {

		try {
			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			String thisLine;
			String[] lineArr = null;

			// this array stores all accounts
			List<String> allAcc = new ArrayList();

			while ((thisLine = br.readLine()) != null) {
				allAcc.add(thisLine);
			}

			System.out.println("These are all the customer accounts.");
			System.out.println(allAcc);
			
			System.out.println("---------------------------");
			System.out.println("Press 1 to return to the previous menu.");
			
			int confirm = Integer.parseInt(b.readLine());
			
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

	public static void approve() {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader data = new BufferedReader(new FileReader("customeraccounts.txt"));

			System.out.println("---------------------------");
			System.out.println("Please enter the id of the customer you want to approve: ");

			// set the id (must include the n) to value
			String ID = br.readLine();

			System.out.println("Approving now...");
			
			String thisLine;
			
			ReplaceClass.editID(ID);

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

package com.revature.main;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.banking.Account;
import com.revature.banking.User;
import com.revature.dao.DAOImplement;

public class MainApp {
	
	static final Logger l = Logger.getRootLogger();

	/**
	 * Starts app and displays menus
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		l.info("IN MAIN");
		appStart();
	}

	private static void appStart() {
		l.info("IN APP START");

		// run database.getInstance to get instance of database singleton
		l.warn("POSSIBLE DATABASE NOT FOUND ERROR");
		DAOImplement dao = new DAOImplement();
		// scanner persists
		Scanner s = new Scanner(System.in);
		
		l.info("MAIN MENU CALLED, SCANNER AND DATABASE PASSED AS PARAMS");
		mainMenu(s, dao);
		System.out.println("RETURN");

	}

	private static void mainMenu(Scanner s, DAOImplement dao) {
		l.info("IN MAIN");
		boolean menuExit = false;
		l.warn("MAIN MENU MAY NEVER END");
		while (menuExit == false) {

			// Switch statement handling user selection
			System.out.println("\nMAIN MENU");
			System.out.println("SPECIFY YOUR ACCESS LEVEL:\n" + "[1] Customer\n" + "[2] Employee\n" + "[3] Admin\n"
					+ "[0] Exit Application\n");
			l.warn("SWITCH POTENTIALLY DOESN'T READ NEXT INT");
			switch (s.nextInt()) {
			case (1):
				l.warn("CUSTOMER MENU MAY NOT BE PASSED SCANNER AND DATABASE");
				customerMenu(s, dao);
				break;
			case (2):
				l.warn("EMPLOYEE MENU MAY NOT BE PASSED SCANNER AND DATABASE");
				employeeMenu(s, dao);
				break;
			case (3):
				l.warn("ADMIN MENU MAY NOT BE PASSED SCANNER AND DATABASE");
				adminMenu(s, dao);
				break;
			default:
				l.info("ANY INPUT OTHER THAN VALID NUMBERS EXIT MENU");
				menuExit = true;
				break;
			}
		}
	}

	private static void customerMenu(Scanner s, DAOImplement dao) {
		l.info("IN CUSTOMER MENU");
		System.out.println("CUSTOMER MENU");
		System.out.println("[1] Log In\n" + "[2] Sign Up\n" + "[0] Return to Menu\n");
		
		l.warn("SWITCH POTENTIALLY DOESN'T READ NEXT INT");
		switch (s.nextInt()) {
		case (1):
			loginMenu: {
				boolean validInput = false;
				l.warn("LOOP CONTINUES IF NOT VALID INPUT FOR USERNAME/PASS");
				while (validInput == false) {
					// customer inputs username and password
					System.out.println("Username:");
					String username = s.next();

					System.out.println("Password:");
					String password = s.next();

					// test to see if keys exist in hashmap
					l.warn("CUSTOMERLOGINMENU MAY NEVER RUN");
					////
					if (dao.login(username, password) > 0) {
						System.out.println(username + password);
						User u = dao.getUser(dao.getUserId(username, password));
						customerLogInMenu(s, dao, u);
						validInput = true;
						break;
					} else {
						System.out.println("login didn't get anything");
						l.error("INPUT NOT MATCHED IN DATABASE");
						validInput = false;

						// allow user to decide whether to login again or return
						// to menu
						System.out.println(
								"NO MATCHING USERNAME/PASSWORD\n[1] Re-enter Credentials\n[0] Return to Menu\n");
						switch (s.nextInt()) {
						case (1):
							break;
						default:
							l.warn("BREAK MIGHT NOT BREAK TO LOGIN MENU AS INTENDED");
							l.info("ANY INPUT OTHER THAN VALID NUMBERS EXIT MENU");
							break loginMenu;
						}
					}
				}
			}
			break;
		case (2):
			boolean taken = true;
			String firstname = null;
			String lastname = null;
			String username = null;
			String password = null;
			// allow user to make username
			l.warn("VALID USERNAME PROMPT MAY NEVER END");
			while (taken) {
				System.out.println("ENTER FIRST NAME:");
				firstname = s.next();
				System.out.println("ENTER LAST NAME:");
				lastname = s.next();					
				System.out.println("ENTER VALID USERNAME:");
				username = s.next();
				System.out.println("ENTER PASSWORD:");
				password = s.next();
				
				if (dao.addCustomer(firstname, lastname, username, password) == 0) {
					System.out.println("can't add customer");
					taken = false;
				}
				else {
					// add last ID method to track last read in customer id
					// in
					// order to instantiate new one (instantiates customer
					// with no accounts)
					User u = new User(firstname, lastname, username, password);
					u.setId(dao.getUserId(username, password));

					// redirects customer to account menu
					l.warn("SCANNER, CUSTOMER, DATABASE MAY NOT BE PROPERLY PASSED INTO ACCOUNT MENU");
					accountMenu(s, u, dao);
					break;
				
				}
				break;
			}
		}
	}

	private static void customerLogInMenu(Scanner s, DAOImplement dao, User u) {
		// display menu
		System.out.println("CUSTOMER LOG IN MENU");
		System.out.println("[1] View Accounts\n" + "[2] Make Deposit\n" + "[3] Make Withdrawal\n" + "[4] Create a New Account\n" + "[0] Exit Menu\n");
		
		// load in customer's account ids
		u.setAccountIds(dao.getAccountIds(u.getId())); 
		ArrayList<Integer> accountIds = u.getAccountIds();
		
		// populate aList with customer's linked accounts
		l.warn("ARRAY LIST MAY NOT BE INSTANTIATED PROPERLY");

		// empty vars for balance etc.
		double accountBalance = 0.0;
		double newBalance;
		switch (s.nextInt()) {
		case (1):

			// Print each account with Account # Type, Balance:
			for (int aId : accountIds) {
				System.out.println(dao.getAccount(aId));
			}
			break;
		case (2):
			System.out.println("Which account would you like to deposit to?");
			for (int i : dao.getAccountIds(u.getId())) {
				System.out.println("[" + i + "] " + dao.getAccount(i));
			}
			int depositChoice = s.nextInt();
			Account depositAccount = dao.getAccount(depositChoice);
			accountBalance = dao.getBalance(depositChoice);
			System.out.println("Current Balance: " + accountBalance);
			
			if(depositAccount.getStatusId() != 2) {
				System.out.println("ACCOUNT NOT APPROVED");
				break;
			}
				
			// allow user to determine how much to deposit
			System.out.println("How much would you like to deposit?");
			newBalance = (accountBalance + s.nextDouble());
			dao.updateBalance(depositAccount.getAccountId(), newBalance);
			depositAccount.setBalance(newBalance);
			System.out.print("New Balance: " + depositAccount.getBalance());
			break;
		case (3):

			// allow user to select which account to withdraw from
			System.out.println("Which account would you like to withdraw from?");
			for (int i = 0; i < accountIds.size(); i++) {
				System.out.println("[" + (i+1) + "]" + dao.getAccount(accountIds.get(i)).toString());
			}
			int withdrawChoice = s.nextInt();
			Account withdrawAccount = dao.getAccount(accountIds.get(withdrawChoice));
			accountBalance = dao.getBalance(withdrawAccount.getAccountId());
			System.out.println("Current Balance: " + accountBalance);
			
			if(withdrawAccount.getStatusId() != 2) {
				System.out.println("ACCOUNT NOT APPROVED");
				break;
			}
			
			// allow user to determine how much to deposit
			System.out.println("How much would you like to withdraw?");
			newBalance = (accountBalance - s.nextDouble());
			dao.updateBalance(withdrawAccount.getAccountId(), newBalance);
			withdrawAccount.setBalance(newBalance);
			System.out.print("New Balance: " + withdrawAccount.getBalance());
			break;
		case (4):
			accountMenu(s, u, dao);
			break;
		default:
			l.info("ANY INPUT OTHER THAN VALID NUMBERS EXIT MENU");
			break;
		}
	}

	private static void accountMenu(Scanner s, User u, DAOImplement dao) {
		// display menu
		accountMenu: {
			System.out.println("ACCOUNT MENU");
			System.out.println("[1] Create Checkings Account\n" + "[2] Create Savings Account\n" + "[0] Exit Menu");
			
			l.warn("SWITCH POTENTIALLY DOESN'T READ NEXT INT");
			switch (s.nextInt()) {
			case (1):

				// make a checkings account
				// acct_id, checkings, pending by default
				dao.addAccount(u.getId(), 1, 1);
				break;
			case (2):

				// make a savings account
				// instantiate using last id account db method, savings
				// acct_id, savings, pending by default
				dao.addAccount(u.getId(), 2, 1);
				break;
			default:
				l.info("ANY INPUT OTHER THAN VALID NUMBERS EXIT MENU");
				break accountMenu;
			}
		}
	}

	private static void employeeMenu(Scanner s, DAOImplement dao) {
		System.out.println("EMPLOYEE MENU");
		System.out.println("[1] Log In\n" + "[2] Sign Up\n" + "[0] Return to Menu\n");
		l.warn("SWITCH POTENTIALLY DOESN'T READ NEXT INT");
		switch (s.nextInt()) {
		case (1):
			loginMenu: {
				boolean validInput = false;
				while (!validInput) {
					// customer inputs username and password
					System.out.println("Username:");
					String username = s.next();

					System.out.println("Password:");
					String password = s.next();

					// test to see if keys exist in hashmap
					if (dao.login(username, password) > 0) {
						System.out.println(username + password);
						User u = dao.getUser(dao.getUserId(username, password));
						employeeLogInMenu(s, dao, u);
						validInput = true;
						break;
					} else {
						System.out.println("login didn't get anything");
						l.error("INPUT NOT MATCHED IN DATABASE");
						validInput = false;

						// allow user to decide whether to login again or return
						// to menu
						System.out.println(
								"NO MATCHING USERNAME/PASSWORD\n[1] Re-enter Credentials\n[0] Return to Menu\n");
						switch (s.nextInt()) {
						case (1):
							break;
						default:
							l.warn("BREAK MIGHT NOT BREAK TO LOGIN MENU AS INTENDED");
							l.info("ANY INPUT OTHER THAN VALID NUMBERS EXIT MENU");
							break loginMenu;
						}
					}
				}
			}
			break;
		case (2):
			boolean taken = true;
			String firstname = null;
			String lastname = null;
			String username = null;
			String password = null;
			// allow user to make username
			l.warn("VALID USERNAME PROMPT MAY NEVER END");
			while (taken) {
				System.out.println("ENTER FIRST NAME:");
				firstname = s.next();
				System.out.println("ENTER LAST NAME:");
				lastname = s.next();					
				System.out.println("ENTER VALID USERNAME:");
				username = s.next();
				System.out.println("ENTER PASSWORD:");
				password = s.next();
				
				if (dao.addEmployee(firstname, lastname, username, password) == 0) {
					taken = false;
				}
				else {
					// add last ID method to track last read in customer id
					// in
					// order to instantiate new one (instantiates customer
					// with no accounts)
					User u = new User(firstname, lastname, username, password);
					u.setId(dao.getUserId(username, password));

					// redirects employee to employeeLogInMenu
					l.warn("SCANNER, EMPLOYEE, DATABASE MAY NOT BE PROPERLY PASSED INTO ACCOUNT MENU");
					employeeLogInMenu(s, dao, u);						
				
				}
			}
			break;
		default:
			l.info("ANY INPUT OTHER THAN VALID NUMBERS EXIT MENU");
			break;
		}

	}

	private static void employeeLogInMenu(Scanner s, DAOImplement dao, User e) {

		// display menu
		System.out.println("EMPLOYEE LOG IN MENU");
		System.out.println("[1] View Unapproved Accounts\n" + "[2] Approve Customer Account\n" + "[3] Decline Customer Account\n" + "[0] Exit Menu\n");

		ArrayList<Integer> unapprovedAccountIds = dao.getAllPendingAccountIds();
		Account a = new Account();
		switch (s.nextInt()) {
		case (1):
			for (int aId : unapprovedAccountIds) {
				System.out.println(dao.getAccount(aId));
			}
			
			break;
		case (2):
			if (unapprovedAccountIds.size() == 0) {
				System.out.println("No customers to approve");
				break;
			}
			// allow user to select which customer to approve
			System.out.println("Which customer would you like to approve?");
			for (int i = 1; i <= unapprovedAccountIds.size(); i++) {
				a = dao.getAccount(unapprovedAccountIds.get(i - 1));
				System.out.println("[" + (i) + "] " + a.toString());
			}
			
			// TODO Approve an account
			int approveIdChoice = s.nextInt();
			a = dao.getAccount(unapprovedAccountIds.get(approveIdChoice - 1));
			dao.approve(e.getId(), a.getAccountId());
			System.out.println("\nCustomer Accounts Approved!");
			break;
		case (3):
			if (unapprovedAccountIds.size() == 0) {
				System.out.println("No customers to decline");
				break;
			}
			// allow user to select which customer to approve
			System.out.println("Which customer would you like to decline?");
			for (int i = 1; i <= unapprovedAccountIds.size(); i++) {
				a = dao.getAccount(unapprovedAccountIds.get(i - 1));
				System.out.println("[" + (i) + "] " + a.toString());
			}
			
			// TODO Approve an account
			int declineIdChoice = s.nextInt();
			a = dao.getAccount(unapprovedAccountIds.get(declineIdChoice - 1));
			dao.decline(e.getId(), a.getAccountId());
			System.out.println("\nCustomer Accounts Declined!");
			break;
		default:
			break;
		}
	}

	private static void adminMenu(Scanner s, DAOImplement dao) {
		// if admin login matches credentials
		adminMenu: {
			System.out.println("ADMIN MENU:\n");
			
			System.out.println("ADMIN USERNAME");
			String username = s.next();
			
			System.out.println("ADMIN PASSWORD");
			String password = s.next();

			System.out.println("username " + username);
			System.out.println("password " + password);
			// admin credentials must be admin:1234
			l.info("INPUT admin AS USERNAME AND 1234 AS PASSWORD");
			if (dao.login(username, password) == 0) {
				System.out.println("INVALID CREDENTIALS, RETURNING TO MAIN MENU");
				break adminMenu;

			} else {
				// run admin menu
				System.out.println("ADMIN LOGIN MENU");
				System.out.println("ACCESS LEVEL:\n" + "[1] CUSTOMER LEVEL\n" + "[2] EMPLOYEE LEVEL\n"
						+ "[0] EXIT ADMIN MENU\n");
				

				switch (s.nextInt()) {
				case (1):
					// print out list of CUSTOMERS
					for(User u1 : dao.getAllCustomers()) {
						System.out.println("[" + u1.getId() + "]\n" + u1);
					}
					System.out.println("Enter User ID you wish to edit:");
					User u1 = dao.getUser(s.nextInt());
					customerLogInMenu(s, dao, u1);
					break;
				case (2):
					// print out list of EMPLOYEES
					for(User u2 : dao.getAllEmployees()) {
						System.out.println("[" + u2.getId() + "]\n" + u2);
					}
					System.out.println("Enter User ID you wish to edit:");
					User u2 = dao.getUser(s.nextInt());
					employeeLogInMenu(s, dao, u2);
					break;
				default:
					break;
				}
			}
		}

	}

}

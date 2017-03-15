package com.revature.banking2.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.revature.banking2.app.State;
import com.revature.banking2.dao.UserDao;
import com.revature.banking2.dao.UserDaoImpl;
import com.revature.banking2.logging.Log;
import com.revature.banking2.logging.LogDao;
import com.revature.banking2.logging.LogDaoImpl;
import com.revature.banking2.logging.*;
import com.revature.banking2.pojo.Account;
import com.revature.banking2.pojo.User;
import com.revature.banking2.service.AccountService;
import com.revature.banking2.service.AccountServiceImpl;
import com.revature.banking2.service.UserService;
import com.revature.banking2.service.UserServiceImpl;

public class AdminMenu implements Menu {
	
	private LogService logger = LogServiceImpl.getLogService();
	private UserService userService = UserServiceImpl.getUserService();
	private AccountService accountService = AccountServiceImpl.getAccountService();

	/**
	 * Runs the menu that displays when the app has started.
	 * 
	 */
	public void displayMainMenu() {
		boolean run = true;
		do {
			System.out.println("Admin Main Menu:");
			System.out.println("(1) Sign in\n(2) Create account\n(3) Exit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
			if (option.equals("1")) {
				handleLogin();
				displayHomeMenu();
			}
			else if (option.equals("2")) {
				handleCreateAccount();
			}
			else if (option.equals("3")) {
				handleExit();
				run = false;
			}
		}
		while (run);
		System.out.println("bye");
	}
	
	/**
	 * Runs the menu that displays while the user is logged in.
	 * 
	 */
	public void displayHomeMenu() {
		System.out.println("Welcome " + State.getState().getUser());
		boolean login = true;
		do {
			System.out.println("(1) View Customer Accounts\n(2) View Employee Accounts\n(3) View Admin Accounts\n(4) Logout");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4"));
			if (option.equals("1")) {
				displayCustomersMenu();
			}
			else if (option.equals("2")) {
				displayEmployeesMenu();
			}
			else if (option.equals("3")) {
				displayAdminsMenu();
			}
			else if (option.equals("4")) {
				handleLogout();
				login = false;
			}
		}
		while (login);
	}
	
	public void displayCustomersMenu() {
		userService.getCustomers();
		
		System.out.println("Viewing customers:");
		boolean customers = true;
		do {
			for (int i = 0; i < State.getState().getUsers().size(); i++) {
				System.out.println("(" + (i+1) + ") Edit " + State.getState().getUsers().get(i).getUsername());
			}
			System.out.println("(Q) Quit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.toLowerCase().equals("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= State.getState().getUsers().size());
			
			if (option.equalsIgnoreCase("q")) {
				customers = false;
			}
			else {
				State.getState().setWorkingUserIndex(Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0);
				State.getState().setWorkingUser(new User(State.getState().getUsers().get(State.getState().getWorkingUserIndex())));
				System.out.println("Editing " + State.getState().getUsers().get(State.getState().getWorkingUserIndex()));
				displayCustomerMenu();
			}
		}
		while (customers);
	}
	
	public void displayCustomerMenu() {
		String option = "";
		boolean customer = true;
		do {
			System.out.println("(1) Edit Bank Accounts\n(2) Edit username\n(3) Edit password\n(4) Edit first name\n(5) Edit last name\n(6) Save changes and Exit\n(7) Discard changes and Exit");
			System.out.println("Select an option:");
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5") && !option.equals("6") && !option.equals("7"));
			
			if (option.equals("1")) {
				displayBankAccountsMenu();
			}
			else if (option.equals("2")) {
				displayChangeUsernameMenu();
			}
			else if (option.equals("3")) {
				displayChangePasswordMenu();
			}
			else if (option.equals("4")) {
				displayChangeFirstNameMenu();
			}
			else if (option.equals("5")) {
				displayChangeLastNameMenu();
			}
			else if (option.equals("6")) {
				handleCommitChanges();
				customer = false;
			}
			else if (option.equals("7")) {
				handleDiscardChanges();
				customer = false;
			}
		}
		while (customer);
	}
	
	public void displayEmployeesMenu() {
		userService.getEmployees();
		
		boolean employees = true;
		do {
			for (int i = 0; i < State.getState().getUsers().size(); i++) {
				System.out.println("(" + (i+1) + ") Edit " + State.getState().getUsers().get(i).getUsername());
			}
			System.out.println("(Q) Quit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.toLowerCase().equals("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= State.getState().getUsers().size());
			
			if (option.equalsIgnoreCase("q")) {
				employees = false;
			}
			else {
				State.getState().setWorkingUserIndex(Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0);
				State.getState().setWorkingUser(new User(State.getState().getUsers().get(State.getState().getWorkingUserIndex())));
				System.out.println(State.getState().getUsers().get(State.getState().getWorkingUserIndex()));
				System.out.println("Editing " + State.getState().getUsers().get(State.getState().getWorkingUserIndex()));
				displayEmployeeMenu();
			}
		}
		while (employees);
	}
	
	public void displayEmployeeMenu() {
		String option = "";
		boolean employee = true;
		do {
			System.out.println("(1) Edit username\n(2) Edit password\n(3) Edit first name\n(4) Edit last name\n(5) Save changes and Exit\n(6) Discard changes and Exit");
			System.out.println("Select an option:");
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5") && !option.equals("6"));
			
			if (option.equals("1")) {
				displayChangeUsernameMenu();
			}
			else if (option.equals("2")) {
				displayChangePasswordMenu();
			}
			else if (option.equals("3")) {
				displayChangeFirstNameMenu();
			}
			else if (option.equals("4")) {
				displayChangeLastNameMenu();
			}
			else if (option.equals("5")) {
				handleCommitChanges();
				employee = false;
			}
			else if (option.equals("6")) {
				handleDiscardChanges();
				employee = false;
			}
		}
		while (employee);
	}
	
	public void displayAdminsMenu() {
		userService.getAdmins();
		
		System.out.println("Viewing admins:");
		boolean admins = true;
		do {
			for (int i = 0; i < State.getState().getUsers().size(); i++) {
				System.out.println("(" + (i+1) + ") Edit " + State.getState().getUsers().get(i).getUsername());
			}
			System.out.println("(Q) Quit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equalsIgnoreCase("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= State.getState().getUsers().size());
			
			if (option.equals("q")) {
				admins = false;
			}
			else {
				State.getState().setWorkingUserIndex(Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0);
				State.getState().setWorkingUser(new User(State.getState().getUsers().get(State.getState().getWorkingUserIndex())));
				System.out.println(State.getState().getUsers().get(State.getState().getWorkingUserIndex()));
				displayAdminMenu();
			}
		}
		while (admins);
	}
	
	public void displayAdminMenu() {
		boolean admin = true;
		do {
			System.out.println("(1) Edit username\n(2) Edit password\n(3) Edit first name\n(4) Edit last name\n(5) Edit role\n(6) Commit changes and Exit\n(7) Discard changes and Exit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4") && !option.equals("5") && !option.equals("6") && !option.equals("7"));
			if (option.equals("1")) {
				displayChangeUsernameMenu();
			}
			else if (option.equals("2")) {
				displayChangePasswordMenu();
			}
			else if (option.equals("3")) {
				displayChangeFirstNameMenu();
			}
			else if (option.equals("4")) {
				displayChangeLastNameMenu();
			}
			else if (option.equals("5")) {
				displayChangeRoleMenu();
			}
			else if (option.equals("6")) {
				handleCommitChanges();
				admin = false;
			}
			else if (option.equals("7")) {
				handleDiscardChanges();
				admin = false;
			}
		}
		while (admin);
	}
	
	public void displayBankAccountsMenu() {
		accountService.getAccounts(State.getState().getWorkingUser());
		System.out.println(State.getState().getWorkingUser());
		System.out.println("Editing bank accounts:");
		
		boolean accounts = true;
		do {
			if (State.getState().getAccounts() != null) {
				for (int i = 0; i < State.getState().getAccounts().size(); i++) {
					System.out.println(State.getState().getAccounts().get(i));
					System.out.println("(" + (i+1) + ") Edit bank account#" + (i+1));
				}
			}
			System.out.println("(A) Add bank account");
			System.out.println("(Q) Quit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equalsIgnoreCase("a") && !option.equalsIgnoreCase("q") && !option.matches("[0-9]*") && (Integer.parseInt(option)-1) >= State.getState().getAccounts().size());
			
			if (option.equals("q")) {
				accounts = false;
			}
			else if (option.equals("a")) {
				do {
					System.out.println("The kind of account (checking | savings):");
					option = Controller.getInput();
				}
				while (!option.equals(Account.Type.checking.toString()) && !option.equals(Account.Type.savings.toString()));
				handleCreateBankAccount(Account.Type.getType(option));
			}
			else {
				State.getState().setWorkingAccountIndex(Integer.parseInt(option) > 0 ? Integer.parseInt(option)-1 : 0);
				displayBankAccountMenu();
			}
		}
		while (accounts);
	}

	public void displayBankAccountMenu() {
		System.out.println("Editing account#" + State.getState().getWorkingAccountIndex() + " (" + State.getState().getAccounts().get(State.getState().getWorkingAccountIndex())+ "):");
		//System.out.println(State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()));
		boolean account = true;
		do {
			System.out.println("(1) Adjust balance\n(2) Adjust approval status\n(3) Exit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
			
			if (option.equals("1")) {
				displayBalanceMenu();
			}
			else if (option.equals("2")) {
				displayApprovalMenu();
			}
			else if (option.equals("3")) {
				System.out.println("Exiting...");
				// SAVE BANK ACCOUNT
				account = false;
			}
		}
		while (account);
	}
	
	private void displayApprovalMenu() {
		System.out.println("Current status: " + State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).getStatus());
		boolean approval = true;
		do {
			System.out.println("(1) Set to pending\n(2) Set to approved\n(3) Set to denied\n(4) Exit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4"));
			
			if (option.equals("1")) {
				handleSetApproval(Account.Status.pending);
			}
			else if (option.equals("2")) {
				handleSetApproval(Account.Status.approved);
			}
			else if (option.equals("3")) {
				handleSetApproval(Account.Status.denied);
			}
			else if (option.equals("4")) {
				System.out.println("Exiting...");
				approval = false;
			}
		}
		while (approval);
	}

	private void displayBalanceMenu() {
		boolean balance = true;
		do {
			System.out.println("Current balance: " + State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).getBalance());
			System.out.println("(1) Deposit\n(2) Withdraw\n(3) Exit");
			System.out.println("Select an option:");
			String option = "";
			do {
				option = Controller.getInput();
			}
			while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
			if (option.equals("3")) {
				System.out.println("Exiting...");
				balance = false;
			}
			else {
				String operation = option;
				double amount = 0;
				boolean validAmount = false;
				do {
					System.out.println("Enter an amount:");
					option = Controller.getInput();
					try {
						amount = Double.parseDouble(option);
						validAmount = true;
					}
					catch (NumberFormatException e) {
						System.out.println("Invalid amount...");
						//e.printStackTrace();
					}
				}
				while (!validAmount);
				if (operation.equals("1")) {
					handleDeposit(amount);
				}
				else if (operation.equals("2")) {
					handleWithdraw(amount);
				}
			}
		}
		while (balance);
	}
	
	private void handleCreateBankAccount(Account.Type type) {
		System.out.println("Creating " + type + " account...");
		accountService.createAccount(State.getState().getWorkingUser(), type);
		System.out.println("Created");
	}
	
	private void handleWithdraw(double amount) {
		System.out.println("Withdrawing " + amount + "...");
		State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).setBalance(
				State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).getBalance() - amount);
		accountService.updateAccount(State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()));
		System.out.println("Withdrew " + amount);
		
		logger.log("withdrew " + amount + " from " + State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()));
	}

	private void handleDeposit(double amount) {
		System.out.println("Depositing " + amount + "...");
		State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).setBalance(
				State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).getBalance() + amount);
		accountService.updateAccount(State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()));
		System.out.println("Deposited " + amount);
		
		logger.log("deposited " + amount + " into " + State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()));
	}

	private void handleSetApproval(Account.Status status) {
		System.out.println("Setting status to " + status + "...");
		State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).setStatus(status);
		State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()).setResolverId(State.getState().getUser().getId());
		accountService.updateAccount(State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()));
		System.out.println("Set status to " + status);
		
		logger.log("set " + status + " status to " + State.getState().getAccounts().get(State.getState().getWorkingAccountIndex()));
	}

	private void handleDiscardChanges() {
		System.out.println("Discarding changes to " + State.getState().getWorkingUser() + "...");
		State.getState().setWorkingUser(null);
		System.out.println("Discarded");
	}

	private void handleCommitChanges() {
		System.out.println("Saving changes to " + State.getState().getWorkingUser() + "...");
		State.getState().getUsers().get(State.getState().getWorkingUserIndex()).copyUser(State.getState().getWorkingUser());
		switch (State.getState().getWorkingUser().getRole()) {
		case admin:
			userService.updateAdmin(State.getState().getUsers().get(State.getState().getWorkingUserIndex()));
			break;
		case employee:
			userService.updateEmployee(State.getState().getUsers().get(State.getState().getWorkingUserIndex()));
			break;
		case customer:
			userService.updateCustomer(State.getState().getUsers().get(State.getState().getWorkingUserIndex()));
			break;
		}
		System.out.println("Saved");
		
		logger.log("saved changes to " + State.getState().getUsers().get(State.getState().getWorkingAccountIndex()));
	}

	private void displayChangeRoleMenu() {
		String role = "", option="";
		System.out.println("Enter new role for " + State.getState().getUsers().get(State.getState().getWorkingUserIndex())  + " (admin | employee | customer): )");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidRole(option));
		System.out.println("New role entered");
		role = option;
		
		State.getState().getWorkingUser().setRole(User.Role.getRole(role));
		
		System.out.println("Role set");
	}

	private void displayChangeLastNameMenu() {
		String lastName = "", option="";
		System.out.println("Enter new last name for " + State.getState().getUsers().get(State.getState().getWorkingUserIndex())  + " (alphanumeric characters and apostrophes are permitted: )");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidName(option));
		System.out.println("New last name entered");
		lastName = option;
		
		State.getState().getWorkingUser().setLastName(lastName);
		
		System.out.println("Last name set");
	}

	private void displayChangeFirstNameMenu() {
		String firstName = "", option="";
		System.out.println("Enter new first name for " + State.getState().getUsers().get(State.getState().getWorkingUserIndex())  + " (alphanumeric characters and apostrophes are permitted: )");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidName(option));
		System.out.println("New first name entered");
		firstName = option;
		
		State.getState().getWorkingUser().setFirstName(firstName);
		
		System.out.println("First name set");
	}

	private void displayChangePasswordMenu() {
		String password = "", option="";
		System.out.println("Enter new password for " + State.getState().getUsers().get(State.getState().getWorkingUserIndex()) + " (alphanumeric characters, _, -, and special characters are permitted): ");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidPassword(option));
		System.out.println("User password entered");
		password = option;
		
		State.getState().getWorkingUser().setPassword(password);
		System.out.println(State.getState().getWorkingUser().getPassword());
		
		System.out.println("Password set");
	}
	
	private void displayChangeUsernameMenu() {
		String username = "", option="";
		System.out.println("Enter new username for " + State.getState().getUsers().get(State.getState().getWorkingUserIndex())  + " (alphanumeric characters and _ are permitted): ");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidUsername(option));
		System.out.println("New username entered");
		username = option;
		
		State.getState().getWorkingUser().setUsername(username);
		
		System.out.println("Username set");
	}

	private void handleLogin() {
		userService.getAdmins();
		
		boolean login = false;
		String user = "", pass="", option="";
		do {
			System.out.println("Enter admin account name:");
			option = Controller.getInput();
			boolean foundUser = false;
			int adminIndex = -1;
			for (int i = 0; i < State.getState().getUsers().size(); i++) {
				if (State.getState().getUsers().get(i).getUsername().equals(option)) {
					foundUser= true;
					adminIndex = i;
					break;
				}
			}
			if (foundUser) {
				do {
					System.out.println("Enter password:");
					option = Controller.getInput();
				}
				while (!option.equals(State.getState().getUsers().get(adminIndex).getPassword()));
				user = option;
				login = true;
				State.getState().setUser(State.getState().getUsers().get(adminIndex));
				System.out.println("Logged in");
				logger.log(State.getState().getUsers().get(adminIndex).getUsername() + " logged in. Role: " + State.getState().getUsers().get(adminIndex).getRole());
			}
			else {
				System.out.println("Did not find user");
			}
		}
		while (!login);
	}
	
	private void handleLogout() {
		logger.log(State.getState().getUser().getUsername() + " logged out. Role: " + State.getState().getUser().getRole());
		//TODO: clear state...
		System.out.println("Logged out");
	}
	
	private void handleCreateAccount() { 
		String username = "", password = "", firstName = "", lastName = "", option = "";
		
		// get username.
		System.out.println("Enter admin account username (alphanumeric characters and _ permitted):");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidUsername(option));
		System.out.println("Admin username entered: " + option);
		username = option;
		
		// get password.
		System.out.println("Enter password for " + username + " (alphanumeric characters, _, -, and special characters are permitted): ");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidPassword(option));
		System.out.println("Admin password entered");
		password = option;
		
		// get first name.
		System.out.println("Enter first name for " + username + " (alphanumeric characters and apostrophes are permitted):");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidName(option));
		System.out.println("Admin first name entered: " + option);
		firstName = option;
		
		// get last name.
		System.out.println("Enter last name for " + username + " (alphanumeric characters and apostrophes are permitted):");
		do {
			option = Controller.getInput();
		}
		while (!User.isValidName(option));
		System.out.println("Admin last name entered: " + option);
		lastName = option;
		
		System.out.println("Creating account...");
		userService.addAdmin(username, password, firstName, lastName);
		System.out.println("Created account");
			
		logger.log("Created admin: User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + "]");
	}
	
	private void handleExit() {
		System.out.println("Exiting...");
	}
	
	@Override
	public void open() {
		System.out.println("Revature Banking App");
		
		displayMainMenu();
	}

}

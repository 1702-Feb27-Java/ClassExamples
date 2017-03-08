package com.revature.bankapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserAccountMethods {

	private static Scanner k;

	public static void requestOptions(UserAccount ua) {

		k = new Scanner(System.in);
		if (ua.getChecking() == null) {
			if (!ua.getCheckReqStat()) {
				System.out.println("ENTER 1: REQUEST A CHECKING ACCOUNT");
			} else {
				System.out.println("YOUR CHECKING ACCOUNT IS PENDING");
			}
		}
		if (ua.getSaving() == null) {
			if (!ua.getSavReqStat()) {
				System.out.println("ENTER 2: REQUEST A SAVING ACCOUNT");
			} else {
				System.out.println("YOUR SAVING ACCOUNT IS PENDING");
			}
		}
		System.out.println("ENTER 0: TO EXIT");
		String option = k.nextLine();

		if (!ua.getCheckReqStat() && !ua.getSavReqStat())
			while (!option.equals("1") && !option.equals("2") && !option.equals("0")) {
				System.out.println("INCORRECT INPUT CHOICE");
				System.out.println(option);
				option = k.nextLine();
			}

		else if (!ua.getSavReqStat())
			while (!option.equals("2") && !option.equals("0")) {
				System.out.println("INCORRECT INPUT CHOICE");
				option = k.nextLine();
			}

		else if (!ua.getCheckReqStat())
			while (!option.equals("1") && !option.equals("0")) {
				System.out.println("INCORRECT INPUT CHOICE");
				option = k.nextLine();
			}
		else if (ua.getCheckReqStat() && ua.getSavReqStat())
			while (!option.equals("0")) {
				System.out.println("INCORRECT INPUT CHOICE");
				option = k.nextLine();
			}

		switch (option) {
		case "1":
			if (!ua.getCheckReqStat())
				ua.setCheckReq(true);
			;
			System.out.println("You requested checking account");
			Main.l.info("Checking account requestd for **" + ua.getUsername()+"**");
			break;
		case "2":
			if (!ua.getSavReqStat())
				ua.setSavReq(true);
			System.out.println("you requested saving account");
			Main.l.info("Saving account requestd for **" + ua.getUsername()+"**");
			break;
		default:

		}

	}

	public static void viewAccounts(ArrayList<UserAccount> userArray) {
		for (int i = 0; i < userArray.size(); i++) {
			System.out.println("Username: " + userArray.get(i).getUsername() + " Password: "
					+ userArray.get(i).getPassword() + " Checking Balance: " + userArray.get(i).getChecking()
					+ " Savaing Balance: " + userArray.get(i).getSaving());
		}
		Main.l.info("Viewing accounts");
	}

	public static void approveRequests(ArrayList<UserAccount> userArray) {
		ArrayList<Integer> allRequests = new ArrayList<Integer>();
		for (int i = 0; i < userArray.size(); i++) {
			if ((userArray.get(i).getAccessLevel() == 0)) {
				// checks if either user requested account AND account is empty
				// to create account
				if ((userArray.get(i).getCheckReqStat() && (userArray.get(i).getChecking() == null))
						|| (userArray.get(i).getSavReqStat() && (userArray.get(i).getSaving() == null))) {
					System.out.println("Index " + i + " " + userArray.get(i));
					allRequests.add(i);
				}
			}
		}

		if (allRequests.size() > 0) {
			System.out.println("Choose the index of the user you would like to approve. ");
			k = new Scanner(System.in);
			int index = k.nextInt();

			while (!allRequests.contains(index)) {
				System.out.println("Incorrect index. Try again");
				index = k.nextInt();
			}

			if (userArray.get(index).getCheckReqStat() && (userArray.get(index).getChecking() == null)) {
				System.out.println("Enter 1: Approve Checking");
			}
			if (userArray.get(index).getSavReqStat() && (userArray.get(index).getSaving() == null)) {
				System.out.println("Enter 2: Approve Saving");
			}

			String option = k.nextLine();
			if ((userArray.get(index).getCheckReqStat() && (userArray.get(index).getChecking() == null))
					|| (userArray.get(index).getSavReqStat() && (userArray.get(index).getSaving() == null))) {
				option = k.nextLine();
				while (!option.equals("1") && !option.equals("2")) {
					System.out.println("Incorrect input.");
					option = k.nextLine();
				}
			} else if (userArray.get(index).getCheckReqStat() && (userArray.get(index).getChecking() == null)) {
				option = k.nextLine();
				while (!option.equals("1")) {
					System.out.println("Incorrect input.");
					option = k.nextLine();
				}
			} else if (userArray.get(index).getSavReqStat() && (userArray.get(index).getSaving() == null)) {
				option = k.nextLine();
				while (!option.equals("2")) {
					System.out.println("Incorrect input.");
					option = k.nextLine();
				}
			}

			switch (option) {
			case "1":
				System.out.println("Enter balance.");
				double balance = k.nextDouble();
				userArray.get(index).createCheckings(new CheckingAccount(balance));
				System.out.println("Checking account created. ");
				Main.l.info("Created checking account for **" + userArray.get(index).getUsername()+ "** with balance " + balance);
				break;
			case "2":
				System.out.println("Enter balance.");
				balance = k.nextDouble();
				userArray.get(index).createSavings(new SavingAccount(balance));
				System.out.println("Saving account created. ");
				Main.l.info("Created saving account for **" + userArray.get(index).getUsername() + "** with balance " + balance);
				break;
				
			}
		} else
			System.out.println("No pending approvals.");

	}

	public static void bankOptions(UserAccount ua) {

		k = new Scanner(System.in);
		System.out.println("ENTER 1: CHECK BALANCE");
		System.out.println("ENTER 2: DEPOSIT");
		System.out.println("ENTER 3: WITHDRAW");

		String option = k.nextLine();

		switch (option) {
		case "1":
			checkBalances(ua);
			break;
		case "2":
			if ((ua.getChecking() != null) && (ua.getSaving() != null)) {
				System.out.println("Enter 1: Deposit Checking");
				System.out.println("Enter 2: Deposit Savings");
				String option1 = k.nextLine();
				while (!option1.equals("1") && !option1.equals("2")) {
					System.out.println("INCORRECT FORMAT");
					option1 = k.nextLine();
				}
				if (option1.equals("1")) {
					System.out.println("Enter balance to deposit: ");
					double balance = k.nextDouble();
					ua.getChecking().deposit(balance);
					Main.l.info("Deposited " + balance + "to Checkings of **" + ua.getUsername()+"**");
				} else {
					System.out.println("Enter balance to deposit: ");
					double balance = k.nextDouble();
					ua.getSaving().deposit(balance);
					Main.l.info("Deposited " + balance + "to Savings of **" + ua.getUsername()+"**");
				}
			} else if ((ua.getChecking() != null)) {
				System.out.println("Enter amount to deposit: ");
				double amount = k.nextDouble();
				ua.getChecking().deposit(amount);
				Main.l.info("Deposited " + amount + "to Checkings of **" + ua.getUsername()+"**");

			} else if ((ua.getSaving() != null)) {
				System.out.println("Enter amount to deposit: ");
				double amount = k.nextDouble();
				ua.getSaving().deposit(amount);
				Main.l.info("Deposited " + amount + "to Savings of **" + ua.getUsername()+"**");


			}

			break;
		case "3":
			if ((ua.getChecking() != null) && (ua.getSaving() != null)) {
				System.out.println("Enter 1: Withdraw Checking");
				System.out.println("Enter 2: Withdraw Savings");
				String option2 = k.nextLine();
				while (!option2.equals("1") && !option2.equals("2")) {
					System.out.println("INCORRECT FORMAT");
					option2 = k.nextLine();
				}
				if (option2.equals("1")) {
					System.out.println("Enter amount to withdraw: ");
					double amount = k.nextDouble();
					ua.getChecking().withdraw(amount);
					Main.l.info("Withdrew " + amount + "from Checking of **" + ua.getUsername()+"**");

				} else {
					System.out.println("Enter amount to withdraw: ");
					double amount = k.nextDouble();
					ua.getSaving().withdraw(amount);
					Main.l.info("Withdrew " + amount + "from Savings of **" + ua.getUsername()+"**");

				}
			} else if ((ua.getChecking() != null)) {
				System.out.println("Enter amount to withdraw: ");
				double amount = k.nextDouble();
				ua.getChecking().withdraw(amount);
				Main.l.info("Withdrew " + amount + "from Checking of **" + ua.getUsername()+"**");


			} else if ((ua.getSaving() != null)) {
				System.out.println("Enter amount to withdraw: ");
				double amount = k.nextDouble();
				ua.getSaving().withdraw(amount);
				Main.l.info("Withdrew " + amount + "from Savings of **" + ua.getUsername()+"**");

			}
			break;

		}

	}

	public static void editAccounts(ArrayList<UserAccount> userArray) {

		for (int i = 0; i < userArray.size(); i++) {
			System.out.println("User Index Number: " + i + " Username: " + userArray.get(i).getUsername());
		}

		System.out.println("Enter the index of the use you want IN DEPTH view and EDIT: ");
		k = new Scanner(System.in);
		int index = k.nextInt();
		while (index >= userArray.size() || index < 0) {
			System.out.println("Invalid index: ");
			index = k.nextInt();
		}

		System.out.println("User Index Nmber: " + index);
		System.out.println("1. Username: " + userArray.get(index).getUsername());
		System.out.println("2. Password: " + userArray.get(index).getPassword());
		System.out.println("3. First Name: " + userArray.get(index).getPerson().getFname());
		System.out.println("4. Last Name: " + userArray.get(index).getPerson().getLname());
		System.out.println("5. Phone Number: " + userArray.get(index).getPerson().getPnum());
		System.out.println("6. E-mail Address: " + userArray.get(index).getPerson().getEmail());
		if ((userArray.get(index).getChecking() != null))
			System.out.println("7. Checking Balance: " + userArray.get(index).getChecking());
		if ((userArray.get(index).getSaving() != null))
			System.out.println("8. Saving Balance: " + userArray.get(index).getSaving());
		if(userArray.get(index).getAccessLevel() !=2)
			System.out.println("9. Delete account");
		System.out.println("0. Exit");

		System.out.println("----------------------------------------------------");
		System.out.println("Enter option index to change:");

		String option = "0";
		option = k.next();

		while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")
				&& !option.equals("5") && !option.equals("6") && !option.equals("7") && !option.equals("8")
				&& !option.equals("9") && !option.equals("0")) {
			System.out.println("Incorrect option. ");
			option = k.next();

		}
		switch (option) {
		case "1":
			System.out.println("Enter new username: ");
			String username = k.next();
			userArray.get(index).setUsername(username);
			Main.l.info("User at index" + index + " username now **" + username+"**");
			break;
		case "2":
			System.out.println("Enter new password: ");
			String password = k.next();
			userArray.get(index).setUsername(password);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** password now " + password);
			break;
		case "3":
			System.out.println("Enter new first name: ");
			String fname = k.next();
			userArray.get(index).getPerson().setFname(fname);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** first name now " + fname);
			break;
		case "4":
			System.out.println("Enter new last name: ");
			String lname = k.next();
			userArray.get(index).getPerson().setLname(lname);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** last name now " + lname);
			break;
		case "5":
			System.out.println("Enter new phone number: ");
			String pnum = k.next();
			userArray.get(index).getPerson().setPnum(pnum);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** phone number now " + pnum);
			break;
		case "6":
			System.out.println("Enter new e-mail: ");
			String email = k.next();
			userArray.get(index).getPerson().setEmail(email);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** e-mail now " + email);
			break;
		case "7":
			System.out.println("Enter new checking balance: ");
			double balance = k.nextDouble();
			userArray.get(index).getChecking().setBalance(balance);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** checking balance now " + balance);
			break;

		case "8":
			System.out.println("Enter new saving balance: ");
			balance = k.nextDouble();
			userArray.get(index).getSaving().setBalance(balance);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** saving balance now " + balance);
			break;
		case "9":
			System.out.println("Removing index.");
			userArray.remove(index);
			Main.l.info("User **" + userArray.get(index).getUsername() + "** removed.");
			break;
		default:
			System.out.println("Exiting...");
			Main.l.info("Exiting admin methods");
			break;
		}

	}

	public static void change(UserAccount ua) {
		ua.setCheckReq(true);
	}

	public static void checkBalances(UserAccount ua) {
		if (ua.getChecking() != null) {
			System.out.println("YOUR CHECKINGS BALANCE: " + ua.getChecking().getBalance());
			Main.l.info("Balance " + ua.getChecking().getBalance() + " in Checking of **" + ua.getUsername()+"**");
		}
		if (ua.getSaving() != null) {
			System.out.println("YOUR SAVINGS BALANCE: " + ua.getSaving().getBalance());
			Main.l.info("Balance " + ua.getSaving().getBalance() + " in Savings of **" + ua.getUsername()+"**");
		}
	}

	public static void deposit(UserAccount ua) {
		if ((ua.getChecking() != null) && (ua.getSaving() != null)) {
			System.out.println("ENTER 1: Deposit into Checkings");
			System.out.println("Enter 2: Deposit into Savings");
			k = new Scanner(System.in);

			String option = k.nextLine();
			while (!option.equals("1") || !option.equals("2")) {
				System.out.println("Incorrect option. Re-Enter choice.");
				option = k.nextLine();
			}
			switch (option) {
			case "1":
				System.out.println("Enter how much you would like to deposit: ");
				double amount = k.nextDouble();
				ua.getChecking().deposit(amount);
				Main.l.info("Deposited " + amount + " into Checkings of **" + ua.getUsername()+"**");
				
				break;

			case "2":
				System.out.println("Enter how much you would like to deposit: ");
				amount = k.nextDouble();
				ua.getSaving().deposit(amount);
				Main.l.info("Deposited " + amount + " into Savings of **" + ua.getUsername()+"**");
				break;

			}
		} else if (ua.getChecking() != null) {
			System.out.println("Enter how much you would like to deposit into CHECKING: ");
			double amount = k.nextDouble();
			ua.getChecking().deposit(amount);
			Main.l.info("Deposited " + amount + " into Checkings of **" + ua.getUsername()+"**");

		} else if (ua.getSaving() != null) {
			System.out.println("Enter how much you would like to deposit into SAVING: ");
			double amount = k.nextDouble();
			ua.getSaving().deposit(amount);
			Main.l.info("Deposited " + amount + " into Savings of **" + ua.getUsername()+"**");

		}
	}

	// serializes user account for persistant use
	public static void serUserAccount(ArrayList<UserAccount> ua) {
		File serUsers = new File("SerializedUsers.ser");

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serUsers))) {
			oos.writeObject(ua);
		} catch (IOException e) {
			System.out.println(e);
		}
		Main.l.info("User list serialized");
	}

	public static ArrayList<UserAccount> deserUserAccount() {

		File serUsers = new File("SerializedUsers.ser");
		ArrayList<UserAccount> userArray = new ArrayList<UserAccount>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serUsers))) {

			return (ArrayList<UserAccount>) ois.readObject();

		} catch (IOException e1) {
			System.out.println(e1);
		} catch (ClassNotFoundException e1) {
			System.out.println(e1);
		}
		Main.l.info("User list loaded");
		return userArray;
	}

	public static int getArrayIndex(String username, ArrayList<UserAccount> userArray) {
		int index = 0;
		for (int i = 0; i < userArray.size(); i++) {
			if (userArray.get(i).getUsername().equals(username)) {
				index = i;
				return index;
			}
		}
		return index;
	}
	
	public static void createEmpAccount(ArrayList<UserAccount> userArray){
		boolean invalid = true;
		k = new Scanner(System.in);
		String user = "";
		while (invalid) {
			System.out.println("Enter employee username");
			user = k.nextLine();
			invalid = LogInMethods.checkUsername(user, userArray);
			if (invalid)
				System.out.println("USERNAME TAKEN");
		}
		System.out.println("Enter employee pass:");
		String password = k.next();
		System.out.println("Enter employee name:");
		String fname = k.next();
		System.out.println("Enter employee last name:");
		String lname = k.next();
		String pnum="";
		while (pnum.length() != 10 || pnum.matches(".*\\D.*")) {
			System.out.println("Enter employee phone number");
			pnum = k.next();
			if (pnum.length() != 10 || pnum.matches(".*\\D.*")) {
				System.out.println("INCORRECT FORMAT");
			}
		}
		System.out.println("Enter employee email:");
		String email = k.next();
		
		Person p = new Person(fname, lname, pnum, email);
		EmployeeAccount ea = new EmployeeAccount(user,password,p);	
		userArray.add(ea);
		UserAccountMethods.serUserAccount(userArray);


		System.out.println("ACCOUNT SUCCESSFULLY CREATED! THANK YOU");
		Main.l.info("New employee account created");
		
	}
}

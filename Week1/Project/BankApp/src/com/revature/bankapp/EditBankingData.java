package com.revature.bankapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EditBankingData {

	static File bankAccountList = new File("BankAccount.txt");
	File accountRequest = new File("AccountRequests.txt");

	public EditBankingData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(bankAccountList));
			BufferedWriter bw = new BufferedWriter(new FileWriter(bankAccountList));
		} catch (Exception e) {

		}
	}

	public void storeAccount(String username, String password, String accountnumber, boolean checkingAccount,
			boolean savingAccount) {

	}

	public static void createAccount(ArrayList<UserAccount> userArray) {

		// checks if username taken. asks user to enter all info for account
		boolean invalid = true;
		Scanner k = new Scanner(System.in);
		String user = "";
		while (invalid) {
			System.out.println("PLEASE ENTER YOUR DESIRED USERNAME");
			user = k.nextLine();
			invalid = LogInMethods.checkUsername(user, userArray);
			if (invalid)
				System.out.println("USERNAME TAKEN");
		}
		System.out.println("PLEASE ENTER YOUR PASSWORD");
		String pw = k.nextLine();
		System.out.println("PLEASE ENTER YOUR FIRST NAME");
		String fname = k.nextLine();
		System.out.println("PLEASE ENTER YOUR LAST NAME");
		String lname = k.nextLine();
		String pnum = "";
		// users can only enter numbers for phone number
		while (pnum.length() != 10 || pnum.matches(".*\\D.*")) {
			System.out.println("PLEASE ENTER YOUR TEN DIGIT PHONE NUMBER NO DASHES OR PARENTHESIS");
			pnum = k.nextLine();
			if (pnum.length() != 10 || pnum.matches(".*\\D.*")) {
				System.out.println("INCORRECT FORMAT");
			}
		}
		System.out.println("PLEASE ENTER YOUR E-MAIL");
		String email = k.nextLine();

		// creates person object for user account and creates user account to
		// increment static user num;
		Person p = new Person(fname, lname, pnum, email);
		UserAccount ua = new UserAccount(user, pw, p);
		userArray.add(ua);
		UserAccountMethods.serUserAccount(userArray);


		System.out.println("ACCOUNT SUCCESSFULLY CREATED! THANK YOU");

	}
/*
	// for persistant user account number creation
	public static int getLastUserNumber() {
		int lineNum = 0;
		String accNum = "";
		// finds the line number for last line
		try (BufferedReader br = new BufferedReader(new FileReader(bankAccountList))) {
			while (br.readLine() != null) {
				lineNum++;
			}

		} catch (IOException e) {
			System.out.println(e);
		}
		try (BufferedReader br = new BufferedReader(new FileReader(bankAccountList))) {
			for (int i = 0; i < lineNum - 1; i++) {
				br.readLine();
			}
			// getting last line and creating substring for account number
			// account num stored always at last part of string so no need to
			// search
			accNum = br.readLine();
			accNum = accNum.substring(accNum.length() - 16, accNum.length());
			System.out.println(accNum);

		} catch (IOException e) {
			System.out.println(e);
		}

		int accNumInt = Integer.parseInt(accNum);

		return accNumInt;
	}*/
}

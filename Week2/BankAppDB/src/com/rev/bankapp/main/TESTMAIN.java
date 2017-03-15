package com.rev.bankapp.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.rev.bankapp.dao.BankDAO;
import com.rev.bankapp.service.BankService;

public class TESTMAIN {

	public static void main(String[] args) {
		Scanner k = new Scanner(System.in);
		boolean logInTrue = false;
		int userID = 0;

		
		while (true) {
			System.out.println("---------------------------------------------------");
			System.out.println("-----Welcome to the National Bank of Revature!-----");
			System.out.println("---------------------------------------------------");
			System.out.println("Do you have an account with us? \nY/N");
			String ans = BankService.getAnswer();
			if (ans.equalsIgnoreCase("n")) {
				BankService.createAccount();
			} else {
				userID = BankService.logIn();
			}
			
			while(userID > 0){
				int roleID = BankDAO.getUserRole(userID);
				BankService.roleCheck(roleID, userID);

				System.out.println("-------Would you like another transaction?---------");

				ans = BankService.getAnswer();
				if(ans.equalsIgnoreCase("n")){
					userID = 0;
					System.out.println("---------------------END TRANS---------------------");

				}
			}

		} 

	}
}

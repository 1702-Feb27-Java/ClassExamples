package com.mory;

import java.util.Scanner;

// Bank interface
public class ATM {
	Scanner sc= new Scanner(System.in);
	//instantiate a new bank object
	Bank theBank=new Bank("Revature Bank");
	//add a user to the bank, whihc also creates a savings account
	User user= theBank.addUser("Mory", "keita",1234);
	
	// add a checking account
	Account accnt= new Account("Checking", user, theBank);
	
	
	

}

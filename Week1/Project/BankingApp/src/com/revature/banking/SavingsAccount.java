package com.revature.banking;

import java.io.IOException;

public class SavingsAccount {
	
	private double balance;
	
	public SavingsAccount()
	{
		balance = 0;
	}

	public SavingsAccount(double initialBalance)
	{
		balance = initialBalance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double amount){
		balance = balance + amount;
	}
	
	public void withdraw(double amount){
		balance = balance - amount;
	}
	
//	System.out.println("Savings account created. Would you like to deposit money now? Y/N");
//	System.out.println("1. Yes");
//	System.out.println("2. No");
//	int yesOrNo;
//	double amount = 0;
//	try {
//		yesOrNo = Integer.parseInt(br.readLine());
//
//		switch (yesOrNo) {
//		case 1:
//			System.out.println("---------------------------");
//			System.out.println("Please enter an amount: ");
//			amount = Double.parseDouble(br.readLine());
//			sav = amount;
//			sa.setBalance(amount);
//			System.out.println(
//					"Success. You currently have " + sa.getBalance() + " dollars in your savings account.");
//			break;
//		case 2:
//			System.out.println("Thanks for choosing Revature Banking.");
//			break;
//		default:
//			System.out.println("You cannot make that selection. Try again.");
//			break;
//		}
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

}

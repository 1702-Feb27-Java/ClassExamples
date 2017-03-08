package com.mory;

public class Transaction {
	// the amount of this transaction
 private double amount;
 
 //The account in which the transaction was performed
 private Account  inAccount;
 
 /**
  * create a new transaction
  * @param amount The amount the transaction
  * @param iAccount the account in which the transaction is taking place
  */
 public Transaction(double amount, Account iAccount){
	 this.amount=amount;
	 this.inAccount=inAccount;
 }

 
}
